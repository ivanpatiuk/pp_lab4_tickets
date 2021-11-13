package lpnu.mapper;

import lpnu.dto.TicketDTO;
import lpnu.entity.Ticket;
import org.springframework.stereotype.Component;

@Component
public class TicketToTicketDTOMapper {
    public Ticket toEntity(final TicketDTO ticketDTO){
        final Ticket ticket = new Ticket(ticketDTO.getId(),
                ticketDTO.getDepartureCountry(),
                ticketDTO.getDepartureCity(),
                ticketDTO.getArrivalCountry(),
                ticketDTO.getArrivalCity(),
                ticketDTO.getDistance(),
                ticketDTO.getFlightTime(),
                ticketDTO.getPrice(),
                ticketDTO.getDepartureTime(),
                ticketDTO.getArrivalTime());
        return ticket;
    }
    public TicketDTO toDTO(final Ticket ticket){
        final TicketDTO ticketDTO = new TicketDTO(ticket.getId(),
                ticket.getDepartureCountry(),
                ticket.getDepartureCity(),
                ticket.getArrivalCountry(),
                ticket.getArrivalCity(),
                ticket.getDistance(),
                ticket.getFlightTime(),
                ticket.getPrice(),
                ticket.getDepartureTime(),
                ticket.getArrivalTime());
        return ticketDTO;
    }
}
