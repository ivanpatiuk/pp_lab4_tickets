//package lpnu.mapper;
//
//import lpnu.dto.CityDTO;
//import lpnu.entity.City;
//import org.springframework.stereotype.Component;
//
//@Component
//public class CityToCityDTOMapper {
//    public City toEntity(final CityDTO cityDTO){
//        final City city = new City(
//                cityDTO.getId(),
//                cityDTO.getCountry(),
//                cityDTO.getCityName(),
//                cityDTO.getLatitude(),
//                cityDTO.getLongitude(),
//                null,
//                null);
//        return city;
//    }
//    public CityDTO toDTO(final City city){
//        final CityDTO cityDTO = new CityDTO(
//                city.getId(),
//                city.getCountry(),
//                city.getCityName(),
//                city.getLatitude(),
//                city.getLongitude());
//        return cityDTO;
//    }
//}
