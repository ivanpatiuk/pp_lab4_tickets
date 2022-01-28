package lpnu.service;

import lpnu.dto.CityDTO;
import lpnu.entity.City;

import java.util.List;
import java.util.Optional;

public interface CityService {
    CityDTO saveCity(final CityDTO city);
    List<CityDTO> getAllCities();
    CityDTO getCityById(final Long id);
    CityDTO updateCity(final CityDTO city);
    void deleteCityById(final Long id);
}
