package pl.landzberg.holidayinformationservice.common;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@ToString
public class HolidaySearchCriteria {

    public HolidaySearchCriteria(LocalDate date, String countryCode1, String countryCode2) {
        this.date = date;
        this.countryCode1 = CountryCode.parse(countryCode1);
        this.countryCode2 = CountryCode.parse(countryCode2);
    }

    private final LocalDate date;
    private final CountryCode countryCode1;
    private final CountryCode countryCode2;

}
