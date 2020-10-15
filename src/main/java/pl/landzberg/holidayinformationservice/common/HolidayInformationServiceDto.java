package pl.landzberg.holidayinformationservice.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@ToString
@AllArgsConstructor
public class HolidayInformationServiceDto {

    private final LocalDate date;
    private final String name1;
    private final String name2;

}
