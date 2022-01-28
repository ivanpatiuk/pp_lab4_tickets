//package lpnu.dto;
//
//import com.fasterxml.jackson.annotation.JsonFormat;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.EqualsAndHashCode;
//import lombok.NoArgsConstructor;
//
//import javax.validation.constraints.*;
//import java.time.LocalDateTime;
//import java.util.Objects;
//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//public class TicketDTO {
//    private Long id;
//
//    @NotNull
//    private CityDTO departureCity;
//    @NotNull
//    private CityDTO arrivalCity;
//
//    @Min(200)
//    private double distance;
//    @Positive
//    private double flightTime;
//    @Positive
//    private double price;
//
//    @Future
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
//    private LocalDateTime departureTime;
//    @Future
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
//    private LocalDateTime arrivalTime;
//
//
//    public TicketDTO(final Long id, final CityDTO departureCity, final CityDTO arrivalCity, final double distance,
//                     final double flightTime, final double price, final LocalDateTime localDateTime) {
//        this.id = id;
//        this.departureCity = departureCity;
//        this.arrivalCity = arrivalCity;
//        this.distance = distance;
//        this.flightTime = flightTime;
//        this.price = price;
//        this.arrivalTime = localDateTime;
//        this.departureTime = localDateTime.plusMinutes((int)(flightTime)+20);
//    }
//}
