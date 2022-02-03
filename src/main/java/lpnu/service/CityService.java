package lpnu.service;

import lpnu.dto.CityDTO;
import java.util.List;

public interface CityService {
    CityDTO saveCity(final CityDTO city);
    List<CityDTO> getAllCities();
    CityDTO getCityById(final Long id);
    CityDTO updateCity(final CityDTO city);
    void deleteCityById(final Long id);
}
