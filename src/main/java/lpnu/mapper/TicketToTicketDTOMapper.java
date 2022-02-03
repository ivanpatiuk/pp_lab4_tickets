//package lpnu.mapper;
//
//import lpnu.dto.TicketDTO;
//import lpnu.entity.Ticket;
//import lpnu.entity.User;
//import lpnu.repository.CityRepository;
//import lpnu.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//
//@Component
//public class TicketToTicketDTOMapper {
//    @Autowired
//    private CityRepository cityRepository;
//    @Autowired
//    private CityToCityDTOMapper cityMapper;
//    @Autowired
//    private UserToUserDTOMapper userMapper;
//    @Autowired
//    private UserRepository userRepository;
//
//    public Ticket toEntity(final TicketDTO ticketDTO){
//
//        final Ticket ticket = new Ticket();
//        ticket.setTicketId(ticketDTO.getTicketId());
//        ticket.setDistance(ticketDTO.getDistance());
//        ticket.setFlightTime(ticketDTO.getFlightTime());
//        ticket.setPrice(ticketDTO.getPrice());
//        ticket.setDepartureTime(ticketDTO.getDepartureTime());
//        ticket.setArrivalTime(ticketDTO.getArrivalTime());
//        ticket.setDepartureCity(cityRepository
//                .findById(ticketDTO.getDepartureCity().getId())
//                .get());
//        ticket.setArrivalCity(cityRepository
//                .findById(ticketDTO.getArrivalCity().getId())
//                .get());
//        ticket.setUser(userRepository
//                .findById(ticketDTO.getUser().getUserId())
//                .get());
//
//        @ManyToOne
//        @JoinColumn(name = "user_id")
//        private User user;
//    }
//        return ticket;
//    }
//    public TicketDTO toDTO(final Ticket ticket){
//        final TicketDTO ticketDTO = new TicketDTO();
//
//        ticketDTO.setTicketId(ticket.getTicketId());
//        ticketDTO.setPrice(ticket.getPrice());
//        ticketDTO.setDistance(ticket.getDistance());
//        ticketDTO.setFlightTime(ticket.getFlightTime());
//        ticketDTO.setArrivalTime(ticket.getArrivalTime());
//        ticketDTO.setDepartureTime(ticket.getDepartureTime());
//        ticketDTO.setDepartureCity(cityMapper.toDTO(ticket.getDepartureCity()));
//        ticketDTO.setArrivalCity(cityMapper.toDTO(ticket.getArrivalCity()));
//        ticketDTO.setUser(userMapper.toDTO(ticket.getUser()));
//
//    return ticketDTO;
//    }
//}
