package pl.landzberg.holidayinformationservice.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import pl.landzberg.holidayinformationservice.common.HolidayDto;

import java.util.List;

@Getter
@ToString
@JsonPropertyOrder({"status", "error", "holidays"})
@JsonIgnoreProperties(ignoreUnknown = true)
class HolidayApiResponseDto {

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    HolidayApiResponseDto(@JsonProperty("status") final int status, @JsonProperty("error") final String error,
            @JsonProperty("holidays") final List<HolidayDto> holidays) {
        this.status = HttpStatus.valueOf(status);
        this.error = error;
        this.holidays = holidays;
    }

    private final HttpStatus status;
    private final String error;
    private final List<HolidayDto> holidays;

}
