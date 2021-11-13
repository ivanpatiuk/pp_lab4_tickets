package lpnu.service.impl;

import lpnu.dto.CityDTO;
import lpnu.entity.City;
import lpnu.mapper.CityToCityDTOMapper;
import lpnu.repository.CityRepository;
import lpnu.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityServiceImpl implements CityService {
//    public boolean validate(CityDTO cityDTO){
//            if (cityDTO.getCountry() == null || cityDTO.getName() == null || cityDTO.getLatitude() < -90 ||
//                    cityDTO.getLatitude() > 90 || cityDTO.getLongitude() < -90 || cityDTO.getLongitude() > 90)
//                return false;
//            String nameRegex = "([A-Z][a-z]+[\s-]?)*[A-Z][a-z]+"; // (велика буква{1}малі букви{>1}- або пробіл{1}){>=0}
//                                                                  // (велика буква{1}малі букви{>1}){>=1}
//            if (!cityDTO.getCountry().matches(nameRegex) ||
//                    !cityDTO.getName().matches(nameRegex))
//                return false;
//            else
//                return true;
//
//    }
    @Autowired
    private CityToCityDTOMapper cityMapper;

    @Autowired
    private CityRepository cityRepository;

    @Override
    public List<CityDTO> getAllCities() {
        return cityRepository.getAllCities().stream()
                .map(cityMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CityDTO getCityById(final Long id) {
        if(cityRepository.getAllCities().stream().anyMatch(e->e.getId().equals(id)))
            return cityMapper.toDTO(cityRepository.getCityById(id));
        else
            return null;
    }
    @Override
    public CityDTO saveCity(CityDTO cityDTO) {
        if (cityRepository.getAllCities().stream()
                .anyMatch(e -> cityMapper.toDTO(e).equals(cityDTO) || e.getId().equals(cityDTO.getId())))
            return null;
        else {
            final City city = cityMapper.toEntity(cityDTO);
            cityRepository.saveCity(city);
            return cityMapper.toDTO(city);
        }
    }
    @Override
    public CityDTO updateCity(CityDTO cityDTO) {
        if(cityRepository.getAllCities().stream().noneMatch(e->e.getId().equals(cityDTO.getId())))
            return null;
        else
            return cityMapper.toDTO(
                    cityRepository.updateCity(
                            cityMapper.toEntity(
                                    cityDTO)));
    }

    @Override
    public void deleteCityById(final Long id) {
        if (cityRepository.getAllCities().stream().anyMatch(e -> e.getId().equals(id)))
            cityRepository.deleteCityById(id);
    }



}