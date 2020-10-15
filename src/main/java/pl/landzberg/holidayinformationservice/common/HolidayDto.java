package pl.landzberg.holidayinformationservice.common;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@ToString
@JsonPropertyOrder({"name", "date", "country"})
@JsonIgnoreProperties(ignoreUnknown = true)
public class HolidayDto {

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public HolidayDto(@JsonProperty("name") final String name, @JsonProperty("date") final String date,
            @JsonProperty("country") final String country) {
        this.name = name;
        this.date = LocalDate.parse(date);
        this.country = CountryCode.parse(country);
    }

    private final String name;
    private final LocalDate date;
    private final CountryCode country;

}
