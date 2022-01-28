//package lpnu.entity;
//
//import com.fasterxml.jackson.annotation.JsonFormat;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.EqualsAndHashCode;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.validation.constraints.*;
//import java.time.LocalDateTime;
//import java.util.Objects;
//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity
//public class Ticket {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @EqualsAndHashCode.Exclude
//    private Long id;
//
//
//    private City departureCity;
//    private City arrivalCity;
//
//    private double distance;
//    private double flightTime;
//    private double price;
//
//    private LocalDateTime departureTime;
//    private LocalDateTime arrivalTime;
//
//
//    public Ticket(final Long id, final City departureCity, final City arrivalCity, final double distance,
//                  final double flightTime, final double price, final LocalDateTime arrivalTime) {
//        this.id = id;
//        this.departureCity = departureCity;
//        this.arrivalCity = arrivalCity;
//        this.distance = distance;
//        this.flightTime = flightTime;
//        this.price = price;
//        this.arrivalTime = arrivalTime;
//        this.departureTime = arrivalTime.plusMinutes((int)(flightTime)+20);
//    }
//}
