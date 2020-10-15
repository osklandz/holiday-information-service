package pl.landzberg.holidayinformationservice.core.exception;

import org.apache.commons.lang3.StringUtils;

public final class HolidayApiException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "Something went wrong with HolidayApi, please visit https://holidayapi.com/docs";

    public HolidayApiException(final String message) {
        super(StringUtils.isNotBlank(message) ? message : DEFAULT_MESSAGE);
    }

}
