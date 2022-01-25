package lpnu.dto;

import lpnu.entity.City;

import javax.validation.constraints.*;
import java.util.Objects;

public class CityDTO {
    @NotBlank(message="Country name is mandatory")
    @Pattern(regexp="([A-Z][a-z]+[\\s-]?)*[A-Z][a-z]+", message="Invalid country name")
    private String country;

    @NotBlank(message="City name is mandatory")
    @Pattern(regexp="([A-Z][a-z]+[\\s-]?)*[A-Z][a-z]+", message="Invalid city name")
    private String name;

    @NotNull(message = "Latitude is mandatory ")
    @Min(-90)
    @Max(90)
    private Double latitude; // широта

    @NotNull(message = "Latitude is mandatory ")
    @Min(-90)
    @Max(90)
    private Double longitude; // довгота


    public CityDTO() { }
    public CityDTO(final String country, final String name,
                   final Double latitude, final Double longitude) {
        this.country = country;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }


    public String getCountry() {
        return country;
    }
    public void setCountry(final String country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }
    public void setName(final String name) {
        this.name = name;
    }

    public Double getLongitude() {
        return longitude;
    }
    public void setLongitude(final Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }
    public void setLatitude(final Double latitude) {
        this.latitude = latitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CityDTO cityDTO = (CityDTO) o;
        return (Objects.equals(country, cityDTO.country) && Objects.equals(name, cityDTO.name))
                || (Objects.equals(latitude, cityDTO.latitude) && Objects.equals(longitude, cityDTO.longitude));
    }

    @Override
    public int hashCode() {
        return Objects.hash(country, name, latitude, longitude);
    }
}

