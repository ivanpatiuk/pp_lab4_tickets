package lpnu.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.util.Objects;

@Entity
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

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

    public City() { }
    public City(final Long id, final String country, final String name,
                final Double latitude, final Double longitude) {
        this.id = id;
        this.country = country;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Long getId() {
        return id;
    }
    public void setId(final Long id) {
        this.id = id;
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
    public void setLongitude(final double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }
    public void setLatitude(final double latitude) {
        this.latitude = latitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return Objects.equals(country, city.country) && Objects.equals(name, city.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(country, name);
    }
}
