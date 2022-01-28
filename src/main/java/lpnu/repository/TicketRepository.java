package lpnu.repository;

import lpnu.entity.City;
import lpnu.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
