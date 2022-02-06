package lpnu.service.impl;

import lpnu.dto.CityDTO;
import lpnu.entity.City;
import lpnu.entity.mapper.DTOConvertor;
import lpnu.exception.ServiceException;
import lpnu.repository.CityRepository;
import lpnu.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    private final DTOConvertor dtoConvertor;

    @Autowired
    public CityServiceImpl(CityRepository cityRepository, DTOConvertor dtoConvertor) {
        this.cityRepository = cityRepository;
        this.dtoConvertor = dtoConvertor;
    }

    @Override
    public List<CityDTO> getAllCities() {
        List<City> cityList = cityRepository.findAll();
        List<CityDTO> cityDTOList = new ArrayList<>();
        cityList.forEach(city -> {
            cityDTOList.add(dtoConvertor.convertToDto(city, CityDTO.class));
        });
        return cityDTOList;
    }

    @Override
    public CityDTO getCityById(final Long id) {
        return dtoConvertor.convertToDto(cityRepository.findById(id).get(), CityDTO.class);
    }

    @Override
    public CityDTO saveCity(final CityDTO cityDTO) {
        City city = new City();
        city.setCountry(cityDTO.getCountry());
        city.setCityName(cityDTO.getCityName());
        city.setLongitude(cityDTO.getLongitude());
        city.setLatitude(cityDTO.getLatitude());
        if (cityRepository.findByCountryAndCityName(
                cityDTO.getCountry(),
                cityDTO.getCityName()) != null)
            throw new ServiceException(400, "The city us already saved");
        cityRepository.save(city);
        return dtoConvertor.convertToDto(city, CityDTO.class);
    }

    @Override
    public CityDTO updateCity(final CityDTO cityDTO) {
        City cityFromDB = cityRepository.findById(cityDTO.getCityId()).get();
        cityFromDB.setCountry(cityDTO.getCountry());
        cityFromDB.setCityName(cityDTO.getCityName());
        cityFromDB.setLatitude(cityDTO.getLatitude());
        cityFromDB.setLongitude(cityDTO.getLongitude());
        cityRepository.save(cityFromDB);
        return dtoConvertor.convertToDto(cityFromDB, CityDTO.class);
    }

    @Override
    public void deleteCityById(final Long id) {
        cityRepository.deleteById(id);
    }

}