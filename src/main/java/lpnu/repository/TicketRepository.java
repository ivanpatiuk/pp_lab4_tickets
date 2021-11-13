package lpnu.repository;

import lpnu.entity.Ticket;
import lpnu.exception.ServiceException;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;


@Repository
public class TicketRepository {

    private List<Ticket> tickets;
    private long id = 1;

    @PostConstruct
    public void init() {
        tickets = new ArrayList<>();
    }

    public List<Ticket> getAllTickets() {
        return new ArrayList<Ticket>(tickets);
    }

    public void deleteTicketById(final Long id) {
        for (Ticket ticket : tickets) {
            if (ticket.getId().equals(id)) {
                tickets.remove(ticket);
                break;
            }
        }
    }

    public Ticket updateTicket(final Ticket ticket) {
        final Ticket savedTicket = getTicketById(ticket.getId());

        savedTicket.setDepartureCountry(ticket.getDepartureCountry());
        savedTicket.setDepartureCity(ticket.getDepartureCity());
        savedTicket.setArrivalCity(ticket.getArrivalCity());
        savedTicket.setArrivalCity(ticket.getArrivalCity());
        savedTicket.setDistance(ticket.getDistance());
        savedTicket.setFlightTime(ticket.getFlightTime());
        savedTicket.setPrice(ticket.getPrice());

        return savedTicket;
    }

    public Ticket saveTicket(final Ticket ticket) {
        ticket.setId(id);
        ++id;
        tickets.add(ticket);
        return ticket;
    }

    public Ticket getTicketById(final Long id) {
        return tickets.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ServiceException(400, "ticket with id '"+id+"' not found"));
    }

}

