package lpnu.resource;

import lpnu.dto.*;
import lpnu.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
public class TicketResource {
    @Autowired
    public TicketService ticketService;

    @GetMapping("/tickets")
    public List<TicketDTO> getAllTickets() {
        return ticketService.getAllTickets();
    }

    @GetMapping("/tickets/{id}")
    public TicketDTO getTicketById(@PathVariable final Long id) {
        return ticketService.getTicketById(id);
    }

    @PostMapping("/tickets")
    public TicketDTO saveTicket(@Validated @RequestBody final DepartureArrivalDTO departureArrivalDTO) {
        return ticketService.saveTicket(departureArrivalDTO);
    }

    @PutMapping("/tickets")
    public TicketDTO updateTicket(@RequestBody final TicketDTO ticketDTO) {
        return ticketService.updateTicket(ticketDTO);
    }

    @DeleteMapping("/tickets/{id}")
    public ResponseEntity deleteTicketById(@PathVariable final Long id) {
        ticketService.deleteTicketById(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/ticket-price")
    public SimpleTicketDTO getTicketPrice(@RequestBody final DepartureArrivalDTO departureArrivalDTO){
        return ticketService.getTicketPrice(departureArrivalDTO);
    }

    @PutMapping("/tickets/{ticketId}/{userId}")
    public TicketDTO addTicketToUserById(@PathVariable final Long ticketId, @PathVariable final Long userId) {
        ticketService.addTicketToUserById(ticketId, userId);
        return ticketService.getTicketById(ticketId);
    }

    @PostMapping("/tickets-test")
    public TicketDTO saveTicketTest(@RequestBody final DepartureArrivalDTO departureArrivalDTO) throws ParseException {
        return ticketService.saveTicketTest(departureArrivalDTO);
    }
}
