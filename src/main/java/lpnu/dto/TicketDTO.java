package lpnu.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.Objects;

public class TicketDTO {
    private Long id;

    @NotNull
    private Long departureCityId;
    @NotNull
    private Long arrivalCityId;

    @Min(200)
    private double distance;
    private double flightTime;
    private double price;

    @Future
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime departureTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime arrivalTime;

    public TicketDTO() {
    }

    public TicketDTO(final Long id, final Long departureCityId, final Long arrivalCityId, final double distance,
                     final double flightTime, final double price, final LocalDateTime localDateTime) {
        this.id = id;
        this.departureCityId = departureCityId;
        this.arrivalCityId = arrivalCityId;
        this.distance = distance;
        this.flightTime = flightTime;
        this.price = price;
        this.arrivalTime = localDateTime;
        this.departureTime = localDateTime.plusMinutes((int)(flightTime)+20);
    }

    public TicketDTO(final Long id, final Long departureCityId, final Long arrivalCityId, final double distance,
                     final double flightTime, final double price, final LocalDateTime arrivalTime,
                     final LocalDateTime departureTime) {
        this.id = id;
        this.departureCityId = departureCityId;
        this.arrivalCityId = arrivalCityId;
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

    public Long getDepartureCityId() {
        return departureCityId;
    }

    public void setDepartureCityId(Long departureCityId) {
        this.departureCityId = departureCityId;
    }

    public Long getArrivalCityId() {
        return arrivalCityId;
    }

    public void setArrivalCityId(Long arrivalCityId) {
        this.arrivalCityId = arrivalCityId;
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
        TicketDTO ticketDTO = (TicketDTO) o;
        return Double.compare(ticketDTO.distance, distance) == 0
                && Double.compare(ticketDTO.flightTime, flightTime) == 0
                && Double.compare(ticketDTO.price, price) == 0
                && departureCityId.equals(ticketDTO.departureCityId)
                && arrivalCityId.equals(ticketDTO.arrivalCityId)
                && departureTime.equals(ticketDTO.departureTime)
                && arrivalTime.equals(ticketDTO.arrivalTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(departureCityId, arrivalCityId, distance, flightTime, price, departureTime, arrivalTime);
    }
}
