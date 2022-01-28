package lpnu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CityDTO {
    @EqualsAndHashCode.Exclude
    private Long id;

    @NotBlank(message="Country name is mandatory")
    @Pattern(regexp="([A-Z][a-z]+[\\s-]?)*[A-Z][a-z]+", message="Invalid country name")
    private String country;
    @NotBlank(message="City name is mandatory")
    @Pattern(regexp="([A-Z][a-z]+[\\s-]?)*[A-Z][a-z]+", message="Invalid city name")
    private String cityName;

    @EqualsAndHashCode.Exclude
    @NotNull(message = "Latitude is mandatory ")
    @Min(-90)
    @Max(90)
    private Double latitude; // широта

    @EqualsAndHashCode.Exclude
    @NotNull(message = "Latitude is mandatory ")
    @Min(-90)
    @Max(90)
    private Double longitude; // довгота

    public CityDTO(final String country, final String cityName,
                   final Double latitude, final Double longitude) {
        this.country = country;
        this.cityName = cityName;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}

