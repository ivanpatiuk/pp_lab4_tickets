package lpnu.service;

import lpnu.dto.DepartureArrivalDTO;
import lpnu.dto.SimpleTicketDTO;
import lpnu.dto.TicketDTO;

import java.text.ParseException;
import java.util.List;

public interface TicketService {
    List<TicketDTO> getAllTickets();
    TicketDTO getTicketById(final Long id);
    TicketDTO saveTicket(final DepartureArrivalDTO departureArrivalDTO);
    TicketDTO updateTicket(final TicketDTO ticketDTO);
    TicketDTO addTicketToUserById(final Long ticketId, final Long userId);

    SimpleTicketDTO getTicketPrice(DepartureArrivalDTO departureArrivalDTO);

    void deleteTicketById(final Long id);
    void removeTicketFromUserByTicketId(final Long id);
}
