package lpnu.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.Min;
import java.time.LocalDateTime;

public class DepartureArrivalDTO {

    @Min(1)
    private Long departureCityId;

    @Min(1)
    private Long arrivalCityId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime departureTime;


    public DepartureArrivalDTO() {
    }
    public DepartureArrivalDTO(final Long departureCityId, final Long arrivalCityId) {
        this.departureCityId = departureCityId;
        this.arrivalCityId = arrivalCityId;
    }

    public DepartureArrivalDTO(Long departureCityId, Long arrivalCityId, LocalDateTime departureTime) {
        this.departureCityId = departureCityId;
        this.arrivalCityId = arrivalCityId;
        this.departureTime = departureTime;
    }

    public Long getDepartureCityId() {
        return departureCityId;
    }
    public void setDepartureCityId(final Long departureCityId) {
        this.departureCityId = departureCityId;
    }

    public Long getArrivalCityId() {
        return arrivalCityId;
    }
    public void setArrivalCityId(final Long arrivalCityId) {
        this.arrivalCityId = arrivalCityId;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }
}
