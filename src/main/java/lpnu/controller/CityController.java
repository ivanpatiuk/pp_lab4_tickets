package lpnu.controller;

import lpnu.dto.CityDTO;
import lpnu.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1")
@RestController
public class CityController {
    @Autowired
    private CityService cityService;

    @GetMapping("/cities")
    public @ResponseBody
    Iterable<CityDTO> getAllCities() {
        return cityService.getAllCities();
    }

    @GetMapping("/cities/{id}")
    public CityDTO getCityById(@PathVariable final Long id) {
        return cityService.getCityById(id);
    }

    @PostMapping("/cities")
    public @ResponseBody
    CityDTO saveCity(@Validated @RequestBody final CityDTO cityDTO) {
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

