package pl.landzberg.holidayinformationservice.service;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.landzberg.holidayinformationservice.api.HolidayApiConsumer;
import pl.landzberg.holidayinformationservice.common.HolidayDto;
import pl.landzberg.holidayinformationservice.common.HolidayInformationServiceDto;
import pl.landzberg.holidayinformationservice.common.HolidaySearchCriteria;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class HolidayInformationService {

    private final HolidayApiConsumer consumer;

    @Autowired
    public HolidayInformationService(final HolidayApiConsumer consumer) {
        this.consumer = consumer;
    }

    public Optional<HolidayInformationServiceDto> findFirstCommonHoliday(final HolidaySearchCriteria searchCriteria) {

        final List<HolidayDto> list1 = consumer.getHolidays(searchCriteria.getCountryCode1(), searchCriteria.getDate().getYear());
        final List<HolidayDto> list2 = consumer.getHolidays(searchCriteria.getCountryCode2(), searchCriteria.getDate().getYear());

       return retrieveCommonHolidayFromListsAfterDate(list1, list2, searchCriteria.getDate());
    }

    private Optional<HolidayInformationServiceDto> retrieveCommonHolidayFromListsAfterDate(final List<HolidayDto> list1,
            final List<HolidayDto> list2, final LocalDate date) {

        if (CollectionUtils.isEmpty(list1) || CollectionUtils.isEmpty(list2)) {
            return Optional.empty();
        }

        final Map<LocalDate, HolidayDto> map = toMap(processList(list1, date));

        final List<HolidayDto> list = processList(list2, date);

        for (final HolidayDto holiday : list) {

            final LocalDate holidayDate = holiday.getDate();

            if (map.containsKey(holidayDate)) {
                return Optional.of(new HolidayInformationServiceDto(holidayDate, map.get(holidayDate).getName(), holiday.getName()));
            }
        }

        return Optional.empty();
    }

    private List<HolidayDto> processList(final List<HolidayDto> list, final LocalDate date) {
        return list.stream()
                .filter(h -> h.getDate().isAfter(date))
                .sorted(Comparator.comparing(HolidayDto::getDate))
                .collect(Collectors.toUnmodifiableList());
    }

    private Map<LocalDate, HolidayDto> toMap(final List<HolidayDto> list) {
        return list.stream().collect(Collectors.toUnmodifiableMap(HolidayDto::getDate, Function.identity()));
    }

}
