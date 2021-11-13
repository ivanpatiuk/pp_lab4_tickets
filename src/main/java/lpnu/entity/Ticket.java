package lpnu.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.Future;
import java.time.LocalDateTime;

public class Ticket {
    private Long id;

    private String departureCountry;
    private String departureCity;

    private String arrivalCountry;
    private String arrivalCity;

    private double distance;
    private double flightTime;
    private double price;

    @Future
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime departureTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime arrivalTime;

    public Ticket() {
    }

    public Ticket(final Long id, final String departureCountry, final String departureCity, final String arrivalCountry,
                  final String arrivalCity, final double distance, final double flightTime, final double price,
                  final LocalDateTime arrivalTime) {
        this.id = id;
        this.departureCountry = departureCountry;
        this.departureCity = departureCity;
        this.arrivalCountry = arrivalCountry;
        this.arrivalCity = arrivalCity;
        this.distance = distance;
        this.flightTime = flightTime;
        this.price = price;
        this.arrivalTime = arrivalTime;
        this.departureTime = arrivalTime.plusMinutes((int)(flightTime)+20);
    }

    public Ticket(final Long id, final String departureCountry, final String departureCity, final String arrivalCountry,
                  final String arrivalCity, final double distance, final double flightTime, final double price,
                  final LocalDateTime arrivalTime, final LocalDateTime departureTime) {
        this.id = id;
        this.departureCountry = departureCountry;
        this.departureCity = departureCity;
        this.arrivalCountry = arrivalCountry;
        this.arrivalCity = arrivalCity;
        this.distance = distance;
        this.flightTime = flightTime;
        this.price = price;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepartureCountry() {
        return departureCountry;
    }

    public void setDepartureCountry(final String departureCountry) {
        this.departureCountry = departureCountry;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(final String departureCity) {
        this.departureCity = departureCity;
    }

    public String getArrivalCountry() {
        return arrivalCountry;
    }

    public void setArrivalCountry(final String arrivalCountry) {
        this.arrivalCountry = arrivalCountry;
    }

    public String getArrivalCity() {
        return arrivalCity;
    }

    public void setArrivalCity(final String arrivalCity) {
        this.arrivalCity = arrivalCity;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(final double distance) {
        this.distance = distance;
    }

    public double getFlightTime() {
        return flightTime;
    }

    public void setFlightTime(final double flightTime) {
        this.flightTime = flightTime;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(final double price) {
        this.price = price;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(final LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(final LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }
}
