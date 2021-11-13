package lpnu.service;

import lpnu.dto.TicketDTO;
import lpnu.dto.UserDTO;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

public interface UserService {
    UserDTO saveUser(final UserDTO userDTO);
    List<UserDTO> getAllUsers();
    UserDTO getUserById(final Long id);
    UserDTO updateUser(final UserDTO userDTO);
    void deleteUserById(final Long id);
}
