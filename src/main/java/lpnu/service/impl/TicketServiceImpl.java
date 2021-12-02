package lpnu.service.impl;

import lpnu.dto.*;
import lpnu.entity.Ticket;
import lpnu.entity.User;
import lpnu.exception.ServiceException;
import lpnu.mapper.CityToCityDTOMapper;
import lpnu.mapper.TicketToTicketDTOMapper;
import lpnu.mapper.UserToUserDTOMapper;
import lpnu.repository.CityRepository;
import lpnu.repository.TicketRepository;
import lpnu.repository.UserRepository;
import lpnu.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketServiceImpl implements TicketService {
    private static final double DEGREES = 180;
    private static final double EARTH_RADIUS = 6372.795;
    private static final double pricePerKm = 6;
    private static final double avgFlightSpeed = 600;
    private static final double minFlightPrice = 3000;

    @Autowired
    private CityToCityDTOMapper cityMapper;
    @Autowired
    private TicketToTicketDTOMapper ticketMapper;

    @Autowired
    private UserToUserDTOMapper userMapper;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<TicketDTO> getAllTickets() {
        return ticketRepository.getAllTickets().stream()
                .map(ticketMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TicketDTO getTicketById(final Long id) {
        return ticketMapper.toDTO(ticketRepository.getTicketById(id));
    }

    @Override
    public TicketDTO saveTicket(final DepartureArrivalDTO departureArrivalDTO) {
        final Ticket ticket = new Ticket();
        final CityDTO departureCity = cityMapper.toDTO(cityRepository.getCityById(departureArrivalDTO.getDepartureCityId()));
        final CityDTO arrivalCity = cityMapper.toDTO(cityRepository.getCityById(departureArrivalDTO.getArrivalCityId()));

        final double cityDistance = cityDistance(departureCity, arrivalCity);
        if (cityDistance <= 200)
            throw new ServiceException(400, "distance between cities is less than 200");

        ticket.setDepartureCountry(departureCity.getCountry());
        ticket.setDepartureCity(departureCity.getName());
        ticket.setArrivalCountry(arrivalCity.getCountry());
        ticket.setArrivalCity(arrivalCity.getName());
        ticket.setDistance(cityDistance);
        ticket.setFlightTime(flightTime(departureCity, arrivalCity));
        ticket.setPrice(flightPrice(departureCity, arrivalCity));
        ticket.setArrivalTime(departureArrivalDTO.getDepartureTime());
        ticket.setDepartureTime(departureArrivalDTO
                .getDepartureTime()
                .plusMinutes((int) (flightTime(departureCity, arrivalCity)) + 20));

        if (ticketRepository.getAllTickets().stream().map(e -> e.equals(ticket)).findAny().isPresent())
            throw new ServiceException(400, "ticket is already saved");

        ticketRepository.saveTicket(ticket);

        return ticketMapper.toDTO(ticket);
    }

    @Override
    public TicketDTO updateTicket(final TicketDTO ticketDTO) {
        ticketRepository.getTicketById(ticketDTO.getId());
        return ticketMapper.toDTO(
                ticketRepository.updateTicket(
                        ticketMapper.toEntity(
                                ticketDTO)));
    }

    @Override
    public TicketDTO addTicketToUserById(final Long ticketId, final Long userId) {
        final Ticket ticket = ticketRepository.getTicketById(ticketId);
        final User user = userRepository.getUserById(userId);
        user.getTicketDTOList().add(ticketMapper.toDTO(ticket));
        return ticketMapper.toDTO(ticket);
    }

    @Override
    public void deleteTicketById(final Long id) {
        removeTicketFromUserByTicketId(id);
        ticketRepository.deleteTicketById(id);
    }

    @Override
    public void removeTicketFromUserByTicketId(final Long id) {
        ticketRepository.getTicketById(id);
        ticketRepository.removeTicketFromUserByTicketId(id);
    }

    @Override
    public SimpleTicketDTO getTicketPrice(final DepartureArrivalDTO departureArrivalDTO) {
        if (departureArrivalDTO.getDepartureCityId() < 1 || departureArrivalDTO.getArrivalCityId() < 1
                || departureArrivalDTO.getArrivalCityId().equals(departureArrivalDTO.getDepartureCityId()))
            throw new ServiceException(400, "wrong arguments");
        final CityDTO cityDTO1 = cityMapper.toDTO(cityRepository.getCityById(departureArrivalDTO.getDepartureCityId()));
        final CityDTO cityDTO2 = cityMapper.toDTO(cityRepository.getCityById(departureArrivalDTO.getArrivalCityId()));

        final double cityDistance = cityDistance(cityDTO1, cityDTO2);
        final double flightTime = flightTime(cityDTO1, cityDTO2);

        final LocalDateTime departureLocalDateTime = departureArrivalDTO.getDepartureTime();
        final LocalDateTime arrivalLocalDateTime = departureLocalDateTime.plusMinutes((int) (flightTime) + 20);

        final SimpleTicketDTO simpleTicketDTO = new SimpleTicketDTO(
                cityDTO1.getCountry(),
                cityDTO1.getName(),
                cityDTO2.getCountry(),
                cityDTO2.getName(),
                cityDistance,
                flightTime,
                Math.max(3000, pricePerKm * cityDistance),
                departureLocalDateTime,
                arrivalLocalDateTime);
        return simpleTicketDTO;
    }

    public double flightTime(final CityDTO cityDTO1, final CityDTO cityDTO2) {
        return (cityDistance(cityDTO1, cityDTO2) / avgFlightSpeed) * 60;
    }

    public double cityDistance(final CityDTO city1, final CityDTO city2) {

        // transfer coordinates into radians

        double latitude1 = Math.PI * city1.getLatitude() / DEGREES;
        double latitude2 = Math.PI * city2.getLatitude() / DEGREES;
        double longitude1 = Math.PI * city1.getLongitude() / DEGREES;
        double longitude2 = Math.PI * city2.getLongitude() / DEGREES;

        // cosines and sines of the latitude difference of longitudes
        double cosLat1 = Math.cos(latitude1);
        double cosLat2 = Math.cos(latitude2);
        double sinLat1 = Math.cos(latitude1);
        double sinLat2 = Math.cos(latitude2);
        double delta = longitude2 - longitude1;
        double cosDelta = Math.cos(delta);
        double sinDelta = Math.sin(delta);

        // calculation of the length of a large circle
        double y = Math.sqrt(Math.pow(cosLat2 * sinDelta, 2) +
                Math.pow(cosLat1 * sinLat2 - sinLat1 * cosLat2 * cosDelta, 2));
        double x = sinLat1 * sinLat2 + cosLat1 * cosLat2 * cosDelta;

        double ad = Math.atan2(y, x);
        double dist = ad * EARTH_RADIUS;

        return dist;
    }

    public double flightPrice(CityDTO cityDTO1, CityDTO cityDTO2) {
        return Math.max(minFlightPrice, pricePerKm * cityDistance(cityDTO1, cityDTO2));
    }
}
