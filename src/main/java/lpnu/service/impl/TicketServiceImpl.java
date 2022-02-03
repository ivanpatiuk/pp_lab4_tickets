package lpnu.service.impl;

import lpnu.dto.*;
import lpnu.entity.City;
import lpnu.entity.Ticket;

import lpnu.entity.User;
import lpnu.entity.mapper.DTOConvertor;
import lpnu.exception.ServiceException;

import lpnu.repository.CityRepository;

import lpnu.repository.TicketRepository;

import lpnu.repository.UserRepository;
import lpnu.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {
    private static final double DEGREES = 180;
    private static final double EARTH_RADIUS = 6372.795;
    private static final double pricePerKm = 6;
    private static final double avgFlightSpeed = 600;
    private static final double minFlightPrice = 3000;

    private final CityRepository cityRepository;
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    private final DTOConvertor dtoConvertor;

    @Autowired
    public TicketServiceImpl(CityRepository cityRepository, TicketRepository ticketRepository, UserRepository userRepository, DTOConvertor dtoConvertor) {
        this.cityRepository = cityRepository;
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
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

        ticketRepository.save(ticket);

        return dtoConvertor.convertToDto(ticket, TicketDTO.class);
    }


    @Override
    public TicketDTO updateTicket(final TicketDTO ticketDTO) {
        Ticket ticketFromDB = ticketRepository.findById(ticketDTO.getTicketId()).get();
        ticketFromDB.setPrice(ticketDTO.getPrice());
        ticketFromDB.setArrivalCity(dtoConvertor.convertToEntity(ticketDTO.getArrivalCity(), new City()));
        ticketFromDB.setDepartureCity(dtoConvertor.convertToEntity(ticketDTO.getDepartureCity(), new City()));
        ticketFromDB.setFlightTime(ticketDTO.getFlightTime());
        ticketFromDB.setArrivalTime(ticketDTO.getArrivalTime());
        ticketFromDB.setDistance(ticketDTO.getDistance());
        ticketFromDB.setDepartureTime(ticketDTO.getDepartureTime());

        ticketRepository.save(ticketFromDB);
        return dtoConvertor.convertToDto(ticketFromDB, TicketDTO.class);

    }

    @Override
    public UserDTO addTicketToUserById(final Long ticketId, final Long userId) {
        final Ticket ticket = ticketRepository.findById(ticketId).get();
        final User user = userRepository.findById(userId).get();
        user.getTicketList().add(ticket);
        ticket.setUser(user);
        ticketRepository.save(ticket);
        userRepository.save(user);
        return dtoConvertor.convertToDto(user, UserDTO.class);
    }

    @Override
    public void deleteTicketById(final Long id) {
        ticketRepository.deleteById(id);
    }

    @Override
    public void changeTicketHost(final Long ticketId, final Long userId) {
        final Ticket ticket = ticketRepository.findById(ticketId).get();
        final User user = userRepository.findById(userId).get();
        ticket.getUser().getTicketList().remove(ticket);
        ticket.setUser(user);
        user.getTicketList().add(ticket);
        userRepository.save(user);
    }

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
