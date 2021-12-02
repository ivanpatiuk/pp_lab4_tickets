package lpnu.repository;

import lpnu.entity.City;
import lpnu.exception.ServiceException;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CityRepository {

    private List<City> cities;
    private long id = 1;

    @PostConstruct
    public void init(){
        cities = new ArrayList<>();
    }

    public List<City> getAllCities() {
        return new ArrayList<>(cities);
    }
    public void deleteCityById(final Long id) {
        for(final City city : cities){
            if (city.getId().equals(id)) {
                cities.remove(city);
                break;
            }
        }
    }
    public City updateCity(final City city) {
        final City savedCity = getCityById(city.getId());

        savedCity.setCountry(city.getCountry());
        savedCity.setName(city.getName());
        savedCity.setLatitude(city.getLatitude());
        savedCity.setLongitude(city.getLongitude());

        return savedCity;
    }
    public City saveCity(final City city) {
        city.setId(id);
        ++id;
        cities.add(city);
        return city;
    }
    public City getCityById(final Long id) {
        return cities.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ServiceException(400, "city with id '"+id+"' not found"));
    }
}
