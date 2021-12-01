package resourse_tests;

import lpnu.Application;
import lpnu.dto.TicketDTO;
import lpnu.dto.UserDTO;
import lpnu.entity.User;
import lpnu.repository.UserRepository;
import lpnu.util.JacksonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.CoreMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = Application.class)
@AutoConfigureMockMvc
public class UserTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void saveUser_thenStatus200() throws Exception {
        final UserDTO user = new UserDTO(null, "Test", "Test", 5, null);
        mvc.perform(post("/api/v1/users").contentType(MediaType.APPLICATION_JSON)
                        .content(JacksonUtil.serialize(user)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", is("Test")));
    }

    @Test
    public void getAllUsers_thenStatus200() throws Exception {
        final User user = new User(null, "Hello", "Test", 5, null);
        userRepository.saveUser(user);
        mvc.perform(get("/api/v1/users").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name", is("Hello")));
    }

    @Test
    public void getUserById_thenStatus200() throws Exception {
        final User user = new User(null, "Hello", "Test", 5, null);
        userRepository.saveUser(user);
        mvc.perform(get("/api/v1/users/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)));
    }

    @Test
    public void getUserById_thenStatus400() throws Exception {
        final User user = new User(null, "Hello", "Test", 5, null);
        userRepository.saveUser(user);
        mvc.perform(get("/api/v1/users/2").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void updateUser_thenStatus200() throws Exception {
        final User user = new User(null, "Test", "Test", 5, null);
        userRepository.saveUser(user);
        final UserDTO user2 = new UserDTO(1L, "Test", "Surname", 5, null);
        mvc.perform(put("/api/v1/users").contentType(MediaType.APPLICATION_JSON)
                        .content(JacksonUtil.serialize(user2)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.surname", is("Surname")));
    }

    @Test
    public void deleteUserById_thenStatus200() throws Exception {
        final User user = new User(null, "Ivan", "Test", 5, null);
        final User user2 = new User(null, "Oleh", "Test", 5, null);
        userRepository.saveUser(user);
        userRepository.saveUser(user2);

        mvc.perform(delete("/api/v1/users/" + user2.getId()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        assertEquals(userRepository.getAllUsers().size(),1);
    }
}
