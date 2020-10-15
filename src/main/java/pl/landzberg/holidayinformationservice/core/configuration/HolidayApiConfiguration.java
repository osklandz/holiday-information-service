package pl.landzberg.holidayinformationservice.core.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.client.RestTemplate;
import pl.landzberg.holidayinformationservice.api.HolidayApiConsumer;
import pl.landzberg.holidayinformationservice.core.HolidayApiProperties;

@Configuration
@EnableConfigurationProperties(HolidayApiProperties.class)
class HolidayApiConfiguration {

    private final HolidayApiProperties properties;

    @Autowired
    HolidayApiConfiguration(final HolidayApiProperties properties) {
        this.properties = properties;
    }

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    @DependsOn("systemPropertiesInitializer")
    HolidayApiConsumer holidayApiConsumer() {
        return new HolidayApiConsumer(properties, restTemplate());
    }

}
