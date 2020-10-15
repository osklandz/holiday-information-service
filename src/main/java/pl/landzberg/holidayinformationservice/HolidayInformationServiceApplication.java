package pl.landzberg.holidayinformationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class HolidayInformationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(HolidayInformationServiceApplication.class, args);
    }

}
