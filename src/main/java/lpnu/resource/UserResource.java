package lpnu.resource;

import lpnu.dto.TicketDTO;
import lpnu.dto.UserDTO;
import lpnu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

@RestController
public class UserResource {
    @Autowired
    public UserService userService;

    @GetMapping("/users")
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public UserDTO getUserById(@PathVariable final Long id) {
        return userService.getUserById(id);
    }

    @PostMapping("/users")
    public UserDTO saveUser(@Validated @RequestBody final UserDTO userDTO) {
        return userService.saveUser(userDTO);
    }

    @PutMapping("/users")
    public UserDTO updateUser(@RequestBody final UserDTO userDTO) {
        return userService.updateUser(userDTO);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity deleteUserById(@PathVariable final Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.ok().build();
    }
}