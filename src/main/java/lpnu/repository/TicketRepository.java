package lpnu.repository;

import lpnu.dto.TicketDTO;
import lpnu.entity.Ticket;
import lpnu.entity.User;
import lpnu.exception.ServiceException;
import lpnu.mapper.CityToCityDTOMapper;
import lpnu.mapper.TicketToTicketDTOMapper;
import lpnu.mapper.UserToUserDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;


@Repository
public class TicketRepository {

    private List<Ticket> tickets;
    private long id = 1;

    @Autowired
    private UserToUserDTOMapper userMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TicketToTicketDTOMapper ticketMapper;

    @PostConstruct
    public void init() {
        tickets = new ArrayList<>();
    }

    public List<Ticket> getAllTickets() {
        return new ArrayList<>(tickets);
    }

    public void deleteTicketById(final Long id) {
        for (final Ticket ticket : tickets) {
            if (ticket.getId().equals(id)) {
                tickets.remove(ticket);
                break;
            }
        }
    }

    public Ticket updateTicket(final Ticket ticket) {
        final Ticket savedTicket = getTicketById(ticket.getId());

        savedTicket.setDepartureCity(ticket.getDepartureCity());
        savedTicket.setArrivalCity(ticket.getArrivalCity());
        savedTicket.setDistance(ticket.getDistance());
        savedTicket.setFlightTime(ticket.getFlightTime());
        savedTicket.setPrice(ticket.getPrice());
        savedTicket.setDepartureTime(ticket.getDepartureTime());
        savedTicket.setArrivalTime(ticket.getArrivalTime());

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
                .orElseThrow(() -> new ServiceException(400, "ticket with id '" + id + "' not found"));
    }

    public void removeTicketFromUserByTicketId(final Long id) {
        TicketDTO ticketDTO = ticketMapper.toDTO(getTicketById(id));
        for (User user : userRepository.getAllUsers()) {
            if (user.getTicketDTOList().contains(ticketDTO))
                user.getTicketDTOList().remove(ticketDTO);
        }
    }
}

