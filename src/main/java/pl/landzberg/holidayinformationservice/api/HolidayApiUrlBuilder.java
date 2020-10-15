package pl.landzberg.holidayinformationservice.api;

import pl.landzberg.holidayinformationservice.common.CountryCode;
import pl.landzberg.holidayinformationservice.core.HolidayApiProperties;

final class HolidayApiUrlBuilder {

    private HolidayApiUrlBuilder() {
        throw new UnsupportedOperationException("Do not instant me!");
    }

    static String buildUrl(final HolidayApiProperties properties, final CountryCode countryCode, final int year) {
        return properties.getBaseUrl()
                + "?key=" + properties.getApiKey()
                + "&country=" + countryCode.getCode()
                + "&year=" + year
                + "&language=" + countryCode.getCode()
                + "&pretty=true"
                + "&public=true";
    }

}
