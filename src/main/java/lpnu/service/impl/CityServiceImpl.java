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
        return cityMapper.toDTO(cityRepository.getCityById(id));
    }

    @Override
    public CityDTO saveCity(final CityDTO cityDTO) {
        if (cityRepository.getAllCities().stream()
                .anyMatch(e -> cityMapper.toDTO(e).equals(cityDTO)))
            throw new ServiceException(400, "the city is already saved");
        else {
            final City city = cityMapper.toEntity(cityDTO);
            cityRepository.saveCity(city);
            return cityMapper.toDTO(city);
        }
    }

    @Override
    public CityDTO updateCity(final CityDTO cityDTO) {
//        cityRepository.getCityById(cityDTO.getId());
        if (cityRepository.getAllCities().stream().noneMatch(e -> e.getId().equals(cityDTO.getId())))
            throw new ServiceException(400, "city with id '"+cityDTO.getId()+"' not found");
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