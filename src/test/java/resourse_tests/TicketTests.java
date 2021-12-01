package resourse_tests;

import lpnu.Application;
import lpnu.dto.DepartureArrivalDTO;
import lpnu.dto.TicketDTO;
import lpnu.entity.City;
import lpnu.entity.Ticket;
import lpnu.entity.User;
import lpnu.repository.CityRepository;
import lpnu.repository.TicketRepository;
import lpnu.repository.UserRepository;
import lpnu.service.TicketService;
import lpnu.service.impl.TicketServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = Application.class)
@AutoConfigureMockMvc
public class TicketTests {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TicketService ticketService;


    TicketDTO createTicketForTests(){
        final City city = new City(null, "Ukraine", "Lviv", 49.8397, 24.0297);
        final City city2 = new City(null, "Ukraine", "Kyiv", 50.4501, 30.5234);
        cityRepository.saveCity(city);
        cityRepository.saveCity(city2);

        LocalDateTime localDateTime = LocalDateTime.of(2022, Month.JANUARY,20,13,30);

        DepartureArrivalDTO departureArrivalDTO = new DepartureArrivalDTO(1L,2L,localDateTime);
        return ticketService.saveTicket(departureArrivalDTO);
    }


    @Test
    public void saveTicket_thenStatus200() throws Exception {
        final City city = new City(null, "Ukraine", "Lviv", 49.8397, 24.0297);
        final City city2 = new City(null, "Ukraine", "Kyiv", 50.4501, 30.5234);
        cityRepository.saveCity(city);
        cityRepository.saveCity(city2);

        mvc.perform(post("/api/v1/tickets").contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "    \"departureCityId\": 1," +
                                "    \"arrivalCityId\": 2," +
                                "    \"departureTime\": \"2021-12-28 13:30\"" +
                                "}"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.arrivalCity", is("Kyiv")));
    }

    @Test
    public void getAllTickets_thenStatus200() throws Exception {
        createTicketForTests();

        mvc.perform(get("/api/v1/tickets").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].departureCity", is("Lviv")));
    }

    @Test
    public void getTicketById_thenStatus200() throws Exception {
        createTicketForTests();

        mvc.perform(get("/api/v1/tickets/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.departureCity", is("Lviv")));
    }

    @Test
    public void getTicketById_thenStatus400() throws Exception {
        createTicketForTests();

        mvc.perform(get("/api/v1/tickets/2").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void updateTicket_thenStatus200() throws Exception {
        createTicketForTests();

        mvc.perform(put("/api/v1/tickets").contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "   \"id\": 1," +
                                "    \"departureCountry\": \"Ukraine\"," +
                                "    \"departureCity\": \"New-Lviv\"," +
                                "    \"arrivalCountry\": \"Ukraine\"," +
                                "    \"arrivalCity\": \"Kyiv\"," +
                                "    \"distance\": 559.4946893312589," +
                                "    \"flightTime\": 55.94946893312589," +
                                "    \"price\": 3356.968135987553," +
                                "    \"departureTime\": \"2022-12-28 13:30\"," +
                                "    \"arrivalTime\": \"2022-12-28 14:55\"" +
                                "}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.departureCity", is("New-Lviv")));
    }

    @Test
    public void deleteTicketById_thenStatus200() throws Exception {
        createTicketForTests();

        mvc.perform(delete("/api/v1/tickets/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        assertEquals(ticketRepository.getAllTickets().size(),0);
    }
    @Test
    public void removeTicketFromUserByTicketId_thenStatus200() throws Exception {
        final User user = new User(null, "Hello", "Test", 5, new ArrayList<TicketDTO>());
        user.getTicketDTOList().add(createTicketForTests());
        userRepository.saveUser(user);

        assertEquals(user.getTicketDTOList().size(), 1);
        mvc.perform(delete("/api/v1/tickets-user/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        assertEquals(user.getTicketDTOList().size(), 0);
    }
    @Test
    public void addTicketToUserById_thenStatus200() throws Exception {
        final User user = new User(null, "Hello", "Test", 5, new ArrayList<TicketDTO>());
        userRepository.saveUser(user);
        createTicketForTests();

        assertEquals(user.getTicketDTOList().size(), 0);

        mvc.perform(put("/api/v1/tickets/1/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        assertEquals(user.getTicketDTOList().size(), 1);
    }
}
