package lpnu.resource;

import lpnu.dto.CityDTO;
import lpnu.entity.City;
import lpnu.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1")
@RestController
public class CityController {
    @Autowired
    private CityRepository cityRepository;

    @GetMapping("/cities")
    public @ResponseBody Iterable<City> getAllCities() {
        return cityRepository.findAll();
    }
    @PostMapping("/cities")
    public @ResponseBody City saveCity(@Validated @RequestBody final CityDTO cityDTO) {
        City city = new City();
        city.setCountry(cityDTO.getCountry());
        city.setCityName(cityDTO.getName());
        city.setLongitude(cityDTO.getLongitude());
        city.setLatitude(cityDTO.getLatitude());
        cityRepository.save(city);
        return city;

    }

}

