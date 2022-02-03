package lpnu.service;

import lpnu.dto.DepartureArrivalDTO;
import lpnu.dto.TicketDTO;
import lpnu.dto.UserDTO;

import java.util.List;

public interface TicketService {
    List<TicketDTO> getAllTickets();

    TicketDTO getTicketById(final Long id);

    TicketDTO saveTicket(final DepartureArrivalDTO departureArrivalDTO);

    TicketDTO updateTicket(final TicketDTO ticketDTO);

    UserDTO addTicketToUserById(final Long ticketId, final Long userId);

    void deleteTicketById(final Long id);

    void changeTicketHost(final Long ticketId, final Long userId);
}
