package pl.landzberg.holidayinformationservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.landzberg.holidayinformationservice.common.HolidayInformationServiceDto;
import pl.landzberg.holidayinformationservice.common.HolidaySearchCriteria;
import pl.landzberg.holidayinformationservice.core.web.UpperCase;
import pl.landzberg.holidayinformationservice.service.HolidayInformationService;
import pl.landzberg.holidayinformationservice.core.exception.HolidayNotFoundException;

import java.time.LocalDate;

@RestController
class HolidayInformationController {

    private final HolidayInformationService service;

    @Autowired
    HolidayInformationController(final HolidayInformationService service) {
        this.service = service;
    }

    @GetMapping("/common/holiday")
    HolidayInformationServiceDto get(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate date,
            @RequestParam("countryCode1") @UpperCase final String countryCode1,
            @RequestParam("countryCode2") @UpperCase final String countryCode2) {

        final HolidaySearchCriteria searchCriteria = new HolidaySearchCriteria(date, countryCode1, countryCode2);

        return service.findFirstCommonHoliday(searchCriteria)
                .orElseThrow(() -> new HolidayNotFoundException(searchCriteria));
    }

}
