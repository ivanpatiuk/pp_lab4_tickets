package lpnu.service.impl;

import lpnu.dto.*;
import lpnu.entity.City;
import lpnu.entity.Ticket;

import lpnu.entity.mapper.DTOConvertor;
import lpnu.exception.ServiceException;

import lpnu.repository.CityRepository;

import lpnu.repository.TicketRepository;

import lpnu.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketServiceImpl implements TicketService {
    private static final double DEGREES = 180;
    private static final double EARTH_RADIUS = 6372.795;
    private static final double pricePerKm = 6;
    private static final double avgFlightSpeed = 600;
    private static final double minFlightPrice = 3000;

    private final CityRepository cityRepository;
    private final TicketRepository ticketRepository;
   // private final UserRepository userRepository;
    private final DTOConvertor dtoConvertor;

    @Autowired
    public TicketServiceImpl(CityRepository cityRepository, TicketRepository ticketRepository, DTOConvertor dtoConvertor) {
        this.cityRepository = cityRepository;
        this.ticketRepository = ticketRepository;
        this.dtoConvertor = dtoConvertor;
    }


    @Override
    public List<TicketDTO> getAllTickets() {
        List<Ticket> ticketList = ticketRepository.findAll();
        List<TicketDTO> ticketDTOList = new ArrayList<>();
        ticketList.forEach(ticket -> {
            ticketDTOList.add(dtoConvertor.convertToDto(ticket, TicketDTO.class));
        });
        return ticketDTOList;
    }

    @Override
    public TicketDTO getTicketById(final Long id) {
        return dtoConvertor.convertToDto(ticketRepository.findById(id).get(), TicketDTO.class);
    }

    @Override
    public TicketDTO saveTicket(final DepartureArrivalDTO departureArrivalDTO) {
        final Ticket ticket = new Ticket();
        final City departureCity = cityRepository
                .findById(departureArrivalDTO.getDepartureCityId())
                .get();
        final City arrivalCity = cityRepository
                .findById(departureArrivalDTO.getArrivalCityId())
                .get();

        final double cityDistance = cityDistance(departureCity, arrivalCity);
        if (cityDistance <= 200)
            throw new ServiceException(400, "distance between cities is less than 200");

        ticket.setDepartureCity(departureCity);
        ticket.setArrivalCity(arrivalCity);
        ticket.setDistance(cityDistance);
        ticket.setFlightTime(flightTime(departureCity, arrivalCity));
        ticket.setPrice(flightPrice(departureCity, arrivalCity));
        ticket.setArrivalTime(departureArrivalDTO.getDepartureTime());
        ticket.setDepartureTime(departureArrivalDTO
                .getDepartureTime()
                .plusMinutes((int) (flightTime(departureCity, arrivalCity)) + 20));


//        if (ticketRepository.findAll().stream().map(e -> e.equals(ticket)).findAny().isPresent())
//            throw new ServiceException(400, "ticket is already saved");

        ticketRepository.save(ticket);

        return dtoConvertor.convertToDto(ticket, TicketDTO.class);
    }
//
//    @Override
//    public TicketDTO updateTicket(final TicketDTO ticketDTO) {
//        ticketRepository.getTicketById(ticketDTO.getTicketId());
//        return ticketMapper.toDTO(
//                ticketRepository.updateTicket(
//                        ticketMapper.toEntity(
//                                ticketDTO)));
//    }
//
//    @Override
//    public TicketDTO addTicketToUserById(final Long ticketId, final Long userId) {
//        final Ticket ticket = ticketRepository.getTicketById(ticketId);
//        final User user = userRepository.getUserById(userId);
//        user.getTicketDTOList().add(ticketMapper.toDTO(ticket));
//        return ticketMapper.toDTO(ticket);
//    }
//
//    @Override
//    public void deleteTicketById(final Long id) {
//        removeTicketFromUserByTicketId(id);
//        ticketRepository.deleteTicketById(id);
//    }
//
//    @Override
//    public void removeTicketFromUserByTicketId(final Long id) {
//        ticketRepository.getTicketById(id);
//        ticketRepository.removeTicketFromUserByTicketId(id);
//    }
//
//    @Override
//    public SimpleTicketDTO getTicketPrice(final DepartureArrivalDTO departureArrivalDTO) {
//        if (departureArrivalDTO.getDepartureCityId() < 1 || departureArrivalDTO.getArrivalCityId() < 1
//                || departureArrivalDTO.getArrivalCityId().equals(departureArrivalDTO.getDepartureCityId()))
//            throw new ServiceException(400, "wrong arguments");
//        final City city1 = cityRepository.getCityById(departureArrivalDTO.getDepartureCityId());
//        final City city2 = cityRepository.getCityById(departureArrivalDTO.getArrivalCityId());
//
//        final double cityDistance = cityDistance(city1, city2);
//        final double flightTime = flightTime(city1, city2);
//
//        final LocalDateTime departureLocalDateTime = departureArrivalDTO.getDepartureTime();
//        final LocalDateTime arrivalLocalDateTime = departureLocalDateTime.plusMinutes((int) (flightTime) + 20);
//
//        final SimpleTicketDTO simpleTicketDTO = new SimpleTicketDTO(
//                city1.getCountry(),
//                city1.getName(),
//                city2.getCountry(),
//                city2.getName(),
//                cityDistance,
//                flightTime,
//                Math.max(3000, pricePerKm * cityDistance),
//                departureLocalDateTime,
//                arrivalLocalDateTime);
//        return simpleTicketDTO;
//    }
//
    public double flightTime(final City city1, final City city2) {
        return (cityDistance(city1, city2) / avgFlightSpeed) * 60;
    }

    public double cityDistance(final City city1, final City city2) {

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

    public double flightPrice(City city1, City city2) {
        return Math.max(minFlightPrice, pricePerKm * cityDistance(city1, city2));
    }
}
