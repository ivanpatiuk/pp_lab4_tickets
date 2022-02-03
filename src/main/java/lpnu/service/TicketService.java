package lpnu.service;

import lpnu.dto.DepartureArrivalDTO;
import lpnu.dto.TicketDTO;

import java.util.List;

public interface TicketService {
    List<TicketDTO> getAllTickets();

    TicketDTO getTicketById(final Long id);

    TicketDTO saveTicket(final DepartureArrivalDTO departureArrivalDTO);

//    TicketDTO updateTicket(final TicketDTO ticketDTO);
//
//    TicketDTO addTicketToUserById(final Long ticketId, final Long userId);
//
//    void deleteTicketById(final Long id);
//
//    void removeTicketFromUserByTicketId(final Long id);
}
