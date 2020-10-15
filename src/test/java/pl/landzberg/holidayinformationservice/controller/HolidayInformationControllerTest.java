package pl.landzberg.holidayinformationservice.controller;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import pl.landzberg.holidayinformationservice.api.HolidayApiConsumer;
import pl.landzberg.holidayinformationservice.common.CountryCode;
import pl.landzberg.holidayinformationservice.common.HolidayDto;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HolidayInformationControllerTest {

    @LocalServerPort
    private int port;

    @MockBean
    private HolidayApiConsumer consumer;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;

        when(consumer.getHolidays(CountryCode.POLAND, 2019)).thenReturn(polishHolidays());
        when(consumer.getHolidays(CountryCode.FRANCE, 2019)).thenReturn(frenchHolidays());
    }

    private List<HolidayDto> polishHolidays() {
        return Arrays.asList(
                new HolidayDto("Wniebowstąpienie Maryi", "2019-08-15", "PL"),
                new HolidayDto("Dzień Wszystkich Świętych", "2019-11-01", "PL"),
                new HolidayDto("Narodowy Dzień Niepodległości", "2019-11-11", "PL"),
                new HolidayDto("Boże Narodzenie", "2019-12-25", "PL"),
                new HolidayDto("Drugi dzień świąt Bożego Narodzenia", "2019-12-26", "PL")
        );
    }

    private List<HolidayDto> frenchHolidays() {
        return Arrays.asList(
                new HolidayDto("Assomption de Marie", "2019-08-15", "FR"),
                new HolidayDto("Toussaint", "2019-11-01", "FR"),
                new HolidayDto("Jour de l'Armistice", "2019-11-11", "FR"),
                new HolidayDto("le jour de Noël", "2019-12-25", "FR")
        );
    }

    @Test
    void shouldReturnCommonHolidayWhenDateIsDayBeforeActualHolidayTest() {
        //@formatter::off
        given()
            .queryParam("date", "2019-08-14")
            .queryParam("countryCode1", "pl")
            .queryParam("countryCode2", "fr")
        .when()
            .get("/common/holiday")
        .then()
            .statusCode(HttpStatus.OK.value())
            .body("date", equalTo("2019-08-15"),
                     "name1", equalTo("Wniebowstąpienie Maryi"),
                     "name2", equalTo("Assomption de Marie"));
        //@formatter::on
    }

    @Test
    void shouldReturnNextCommonHolidayDueToDateIsSameDayAsFirstHolidayTest() {
        //@formatter::off
        given()
            .queryParam("date", "2019-08-15")
            .queryParam("countryCode1", "pl")
            .queryParam("countryCode2", "fr")
        .when()
            .get("/common/holiday")
        .then()
            .statusCode(HttpStatus.OK.value())
            .body("date", equalTo("2019-11-01"),
                    "name1", equalTo("Dzień Wszystkich Świętych"),
                    "name2", equalTo("Toussaint"));
        //@formatter::on
    }

    @Test
    void shouldReturnStatus400DueToIncorrectCountryCodeTest() {
        //@formatter::off
        final String message = given()
                    .queryParam("date", "2019-06-01")
                    .queryParam("countryCode1", "ps")
                    .queryParam("countryCode2", "fr")
                .when()
                    .get("/common/holiday")
                .then()
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                    .contentType("text/plain;charset=UTF-8")
                    .extract().body().asString();

        assertThat(message).isEqualTo("Incorrect country code: PS");
        //@formatter::on
    }

    @Test
    void shouldNotFindAnyCommonHolidayAndReturnStatus404Test() {
        //@formatter::off
        final String message = given()
                    .queryParam("date", "2019-12-25")
                    .queryParam("countryCode1", "pl")
                    .queryParam("countryCode2", "fr")
                .when()
                    .get("/common/holiday")
                .then()
                    .statusCode(HttpStatus.NOT_FOUND.value())
                    .contentType("text/plain;charset=UTF-8")
                    .extract().body().asString();

        assertThat(message).isEqualTo("Could not find any common holiday for POLAND and FRANCE after 2019-12-25");
        //@formatter::on
    }

}
