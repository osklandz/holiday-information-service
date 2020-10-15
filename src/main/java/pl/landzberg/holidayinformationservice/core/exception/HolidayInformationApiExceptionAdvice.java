package pl.landzberg.holidayinformationservice.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class HolidayInformationApiExceptionAdvice {

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(HolidayNotFoundException.class)
    public String holidayNotFoundExceptionHandler(final HolidayNotFoundException exception) {
        return exception.getMessage();
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IncorrectCountryCodeException.class)
    public String incorrectCountryCodeExceptionHandler(final IncorrectCountryCodeException exception) {
        return exception.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(HolidayApiException.class)
    public String holidayApiExceptionHandler(final HolidayApiException exception) {
        return exception.getMessage();
    }

}
