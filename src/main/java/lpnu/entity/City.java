package lpnu.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import lpnu.entity.mapper.Convertable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "city")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class City implements Convertable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "city_id")
    @EqualsAndHashCode.Exclude
    private Long cityId;

    @Column(name = "country")
    private String country;

    @Column(name = "city_name")
    private String cityName;

    @Column(name = "latitude")
    private Double latitude; // широта

    @Column(name = "longitude")
    private Double longitude; // довгота

    @JsonBackReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy="departureCity")
    private Set<Ticket> departureTicketList;

    @JsonBackReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy="arrivalCity")
    private Set<Ticket> arrivalTicketList;
}
