package lpnu.resource;

import lpnu.dto.CityDTO;
import lpnu.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RequestMapping("/api/v1")
@RestController
public class CityResource {
    @Autowired
    public CityService cityService;

    @GetMapping("/cities")
    public List<CityDTO> getAllCities() {
        return cityService.getAllCities();
    }

    @GetMapping("/cities/{id}")
    public CityDTO getCityById(@PathVariable final Long id) {
        return cityService.getCityById(id);
    }

    @PostMapping("/cities")
    public CityDTO saveCity(@Validated @RequestBody final CityDTO cityDTO) {
        return cityService.saveCity(cityDTO);
    }

    @PutMapping("/cities")
    public CityDTO updateCity(@Validated @RequestBody final CityDTO cityDTO) {
        return cityService.updateCity(cityDTO);
    }

    @DeleteMapping("/cities/{id}")
    public ResponseEntity deleteCityById(@PathVariable final Long id) {
        cityService.deleteCityById(id);
        return ResponseEntity.ok().build();
    }
}
