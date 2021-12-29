package lpnu.service.impl;

import lpnu.dto.CityDTO;
import lpnu.mapper.CityToCityDTOMapper;
import lpnu.repository2.CityRepository1;
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
    private CityRepository1 cityRepository;

    @Override
    public List<CityDTO> getAllCities() {
        return cityRepository.findAll().stream()
                .map(cityMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CityDTO getCityById(final Long id) {
        return cityMapper.toDTO(cityRepository.getById(id.intValue())); // findById(id.intValue()).orElseThrow());
    }

    @Override
    public CityDTO saveCity(final CityDTO cityDTO) {
        return cityMapper.toDTO(cityRepository.save(cityMapper.toEntity(cityDTO)));
//        if (cityRepository.getAllCities().stream()
//                .anyMatch(e -> cityMapper.toDTO(e).equals(cityDTO)))
//            throw new ServiceException(400, "the city is already saved");
//        final City city = cityMapper.toEntity(cityDTO);
//        cityRepository.saveCity(city);
//        return cityMapper.toDTO(city);

    }

    @Override
    public CityDTO updateCity(final CityDTO cityDTO) {
        return cityMapper.toDTO(cityRepository.save(cityMapper.toEntity(cityDTO)));
    }

    @Override
    public void deleteCityById(final Long id) {
        cityRepository.deleteById(id.intValue());;
    }

}