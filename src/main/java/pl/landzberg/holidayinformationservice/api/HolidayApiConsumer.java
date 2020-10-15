package pl.landzberg.holidayinformationservice.api;

import org.springframework.web.client.RestTemplate;
import pl.landzberg.holidayinformationservice.common.CountryCode;
import pl.landzberg.holidayinformationservice.common.HolidayDto;
import pl.landzberg.holidayinformationservice.core.HolidayApiProperties;
import pl.landzberg.holidayinformationservice.core.exception.HolidayApiException;

import java.util.Collections;
import java.util.List;

public class HolidayApiConsumer {

    private final HolidayApiProperties properties;
    private final RestTemplate restTemplate;

    public HolidayApiConsumer(final HolidayApiProperties properties, final RestTemplate restTemplate) {
        this.properties = properties;
        this.restTemplate = restTemplate;
    }

    public List<HolidayDto> getHolidays(final CountryCode countryCode, final int year) {

        final String url = HolidayApiUrlBuilder.buildUrl(properties, countryCode, year);

        final HolidayApiResponseDto response;

        try {
            response = restTemplate.getForObject(url, HolidayApiResponseDto.class);
        } catch (final Exception ex) {
            throw new HolidayApiException(ex.getMessage());
        }

        return response != null ? response.getHolidays() : Collections.emptyList();
    }

}
