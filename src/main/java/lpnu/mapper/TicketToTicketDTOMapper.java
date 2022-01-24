package lpnu.mapper;

import lpnu.dto.TicketDTO;
import lpnu.entity.Ticket;
import lpnu.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TicketToTicketDTOMapper {
    @Autowired
    private CityRepository cityRepository;

    public Ticket toEntity(final TicketDTO ticketDTO){
        final Ticket ticket = new Ticket(ticketDTO.getId(),
                cityRepository.getCityById(ticketDTO.getDepartureCityId()),
                cityRepository.getCityById(ticketDTO.getArrivalCityId()),
                ticketDTO.getDistance(),
                ticketDTO.getFlightTime(),
                ticketDTO.getPrice(),
                ticketDTO.getDepartureTime(),
                ticketDTO.getArrivalTime());
        return ticket;
    }
    public TicketDTO toDTO(final Ticket ticket){
        final TicketDTO ticketDTO = new TicketDTO(ticket.getId(),
                ticket.getDepartureCity().getId(),
                ticket.getArrivalCity().getId(),
                ticket.getDistance(),
                ticket.getFlightTime(),
                ticket.getPrice(),
                ticket.getDepartureTime(),
                ticket.getArrivalTime());
        return ticketDTO;
    }
}
