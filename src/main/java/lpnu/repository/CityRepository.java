package lpnu.repository;

import lpnu.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Long> {
    City findByCountryAndCityName(String country, String cityName);
}
