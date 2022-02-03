package resoursce;

import lpnu.Application;
import lpnu.dto.CityDTO;
import lpnu.entity.City;
import lpnu.repository.CityRepository;
import lpnu.util.JacksonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = Application.class)
@AutoConfigureMockMvc
public class CityTests {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private CityRepository cityRepository;

//    @Test
//    public void saveCity_thenStatus200() throws Exception {
//        final CityDTO newCity = new CityDTO(null, "Ukraine", "Lviv", 49.8397, 24.0297);
//        mvc.perform(post("/api/v1/cities").contentType(MediaType.APPLICATION_JSON)
//                        .content(JacksonUtil.serialize(newCity)))
//                .andExpect(status().isOk())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.country", is("Ukraine")));
//    }
//    @Test
//    public void getAllCities_thenStatus200() throws Exception {
//        final City newCity = new City(null, "Ukraine", "Lviv", 49.8397, 24.0297);
//        oldCityRepository.saveCity(newCity);
//        mvc.perform(get("/api/v1/cities").contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$[0].latitude", is(49.8397)));
//    }
//    @Test
//    public void getCityById_thenStatus200() throws Exception {
//        final City сity = new City(null, "Ukraine", "Lviv", 49.8397, 24.0297, null, null);
//        cityRepository.save(сity);
//        mvc.perform(get("/api/v1/cities/"+сity.getCityId()).contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.country", is("Ukraine")));
//    }
//    @Test
//    public void getCityById_thenStatus400() throws Exception {
//        final City newCity = new City(null, "Ukraine", "Lviv", 49.8397, 24.0297);
//        oldCityRepository.saveCity(newCity);
//        mvc.perform(get("/api/v1/cities/100").contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isBadRequest());
//    }
//    @Test
//    public void updateCity_thenStatus200() throws Exception {
//        final City city = new City(null, "Ukraine", "Lviv", 49.8397, 24.0297);
//        oldCityRepository.saveCity(city);
//        final City city2 = new City(1L, "Ukraine", "New-Lviv", 49.8397, 24.0297);
//        mvc.perform(put("/api/v1/cities").contentType(MediaType.APPLICATION_JSON)
//                        .content(JacksonUtil.serialize(city2)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.name", is("New-Lviv")));
//    }
//    @Test
//    public void deleteCityById_thenStatus200() throws Exception {
//        final City city = new City(null, "Ukraine", "Lviv", 49.8397, 24.0297);
//        final City city2 = new City(null, "Ukraine", "Kyiv", 50.4501, 30.5234);
//        oldCityRepository.saveCity(city);
//        oldCityRepository.saveCity(city2);
//
//        mvc.perform(delete("/api/v1/cities/" + city.getId()).contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//
//        assertEquals(oldCityRepository.getAllCities().size(),1);
//    }
}
