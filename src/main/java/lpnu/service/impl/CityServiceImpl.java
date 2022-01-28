package lpnu.service.impl;

import lpnu.dto.CityDTO;
import lpnu.entity.City;
import lpnu.exception.ServiceException;
import lpnu.mapper.CityToCityDTOMapper;
import lpnu.repository.CityRepository;
import lpnu.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityServiceImpl implements CityService {
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private CityToCityDTOMapper cityMapper;

    @Override
    public List<CityDTO> getAllCities() {
        return cityRepository
                .findAll()
                .stream()
                .map(city -> cityMapper.toDTO(city))
                .collect(Collectors.toList());
    }

    @Override
    public CityDTO getCityById(final Long id) {
        return cityMapper
                .toDTO(cityRepository
                        .findById(id).get());
    }

    @Override
    public CityDTO saveCity(final CityDTO cityDTO) {
        City city = new City();
        city.setCountry(cityDTO.getCountry());
        city.setCityName(cityDTO.getCityName());
        city.setLongitude(cityDTO.getLongitude());
        city.setLatitude(cityDTO.getLatitude());
        if(cityRepository
                .findAll()
                .stream()
                .anyMatch(city1 -> city1.getCityName().equals(city.getCityName())
                        && city1.getCountry().equals(city.getCountry())))
            throw new ServiceException(400, "The city us already saved");
        cityRepository.save(city);
        return cityMapper.toDTO(city);
    }

    @Override
    public CityDTO updateCity(final CityDTO cityDTO) {
        City cityFromDB = cityRepository.findById(cityDTO.getId()).get();
        cityFromDB.setCountry(cityDTO.getCountry());
        cityFromDB.setCityName(cityDTO.getCityName());
        cityFromDB.setLatitude(cityDTO.getLatitude());
        cityFromDB.setLongitude(cityDTO.getLongitude());
        cityRepository.save(cityFromDB);
        return cityMapper.toDTO(cityFromDB);
    }

    @Override
    public void deleteCityById(final Long id) {
        cityRepository.deleteById(id);
    }

}