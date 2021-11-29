package service_tests;

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
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.CoreMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = Application.class)
@AutoConfigureMockMvc
public class Tests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private UserRepository userRepository;


    @Test
    public void saveUser_thenStatus200() throws Exception {
        final UserDTO newUser = new UserDTO(null, "TestName", "TestSurname", 5, null);
        mvc.perform(post("/api/v1/users").contentType(MediaType.APPLICATION_JSON)
                        .content(JacksonUtil.serialize(newUser)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", is("TestName")));
    }
}

//        mvc.perform(get("/api/v1/users").contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$[0].name", is("Andrii")));
