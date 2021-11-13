package lpnu.mapper;

import lpnu.dto.UserDTO;
import lpnu.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserToUserDTOMapper {
    public User toEntity(final UserDTO userDTO){
        final User user = new User(userDTO.getId(),
                userDTO.getName(),
                userDTO.getSurname(),
                userDTO.getAge(),
                userDTO.getTicketDTOList());
        return user;
    }
    public UserDTO toDTO(final User user){
        final UserDTO userDTO = new UserDTO(
                user.getId(),
                user.getName(),
                user.getSurname(),
                user.getAge(),
                user.getTicketDTOList());
        return userDTO;
    }
}
