package pl.landzberg.holidayinformationservice.core.exception;

import pl.landzberg.holidayinformationservice.common.HolidaySearchCriteria;

import java.time.format.DateTimeFormatter;

public final class HolidayNotFoundException extends RuntimeException {

    public HolidayNotFoundException(final HolidaySearchCriteria searchCriteria) {
        super("Could not find any common holiday for " + searchCriteria.getCountryCode1().name()
                + " and " + searchCriteria.getCountryCode2().name()
                + " after " + searchCriteria.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }

}
