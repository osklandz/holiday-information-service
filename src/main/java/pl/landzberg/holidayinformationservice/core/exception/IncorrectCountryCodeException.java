package pl.landzberg.holidayinformationservice.core.exception;

public final class IncorrectCountryCodeException extends RuntimeException {

    public IncorrectCountryCodeException(final String countryCode) {
        super("Incorrect country code: " + countryCode);
    }

}
