package lpnu.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

public class SimpleTicketDTO {
    private String departureCountry;
    private String departureCity;

    private String arrivalCountry;
    private String arrivalCity;

    private double distance;
    private double flightTime;
    private double price;

    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;

    public SimpleTicketDTO() {
    }

    public SimpleTicketDTO(final String departureCountry, final String departureCity,
                     final String arrivalCountry, final String arrivalCity, final double distance,
                     final double flightTime, final double price, final LocalDateTime departureTime,
                     final LocalDateTime arrivalTime) {
        this.departureCountry = departureCountry;
        this.departureCity = departureCity;
        this.arrivalCountry = arrivalCountry;
        this.arrivalCity = arrivalCity;
        this.distance = distance;
        this.flightTime = flightTime;
        this.price = price;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
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

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
}
