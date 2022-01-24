package lpnu.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.Objects;

public class Ticket {
    private Long id;

    @NotNull
    private City departureCity;

    @NotNull
    private City arrivalCity;

    @Min(200)
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

    public Ticket(final Long id, final City departureCity, final City arrivalCity, final double distance,
                  final double flightTime, final double price, final LocalDateTime arrivalTime) {
        this.id = id;
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
        this.distance = distance;
        this.flightTime = flightTime;
        this.price = price;
        this.arrivalTime = arrivalTime;
        this.departureTime = arrivalTime.plusMinutes((int)(flightTime)+20);
    }

    public Ticket(final Long id, final City departureCity, final City arrivalCity, final double distance,
                  final double flightTime, final double price, final LocalDateTime arrivalTime,
                  final LocalDateTime departureTime) {
        this.id = id;
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
        this.distance = distance;
        this.flightTime = flightTime;
        this.price = price;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
    }

    public void setDepartureCity(City departureCity) {
        this.departureCity = departureCity;
    }

    public void setArrivalCity(City arrivalCity) {
        this.arrivalCity = arrivalCity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public City getDepartureCity() {
        return departureCity;
    }

    public City getArrivalCity() {
        return arrivalCity;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Double.compare(ticket.distance, distance) == 0
                && Double.compare(ticket.flightTime, flightTime) == 0
                && Double.compare(ticket.price, price) == 0
                && departureCity.equals(ticket.departureCity)
                && arrivalCity.equals(ticket.arrivalCity)
                && departureTime.equals(ticket.departureTime)
                && arrivalTime.equals(ticket.arrivalTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(departureCity, arrivalCity, distance, flightTime, price, departureTime, arrivalTime);
    }
}
