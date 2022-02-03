//package lpnu.mapper;
//
//import lpnu.dto.UserDTO;
//import lpnu.entity.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.stream.Collectors;
//
//@Component
//public class UserToUserDTOMapper {
//    @Autowired
//    private TicketToTicketDTOMapper ticketMapper;
//    public User toEntity(final UserDTO userDTO){
//        final User user = new User(userDTO.getUserId(),
//                userDTO.getName(),
//                userDTO.getSurname(),
//                userDTO.getAge(),
//                userDTO.getTicketDTOList()
//                        .stream()
//                        .map(ticketMapper::toEntity)
//                        .collect(Collectors.toList()));
//        return user;
//    }
//    public UserDTO toDTO(final User user){
//        final UserDTO userDTO = new UserDTO(
//                user.getUserId(),
//                user.getName(),
//                user.getSurname(),
//                user.getAge(),
//                user.getTicketList()
//                        .stream()
//                        .map(ticketMapper::toDTO)
//                        .collect(Collectors.toList()));
//        return userDTO;
//    }
//}
