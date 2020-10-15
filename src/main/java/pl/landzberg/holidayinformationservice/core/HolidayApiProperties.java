package pl.landzberg.holidayinformationservice.core;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@ToString
@AllArgsConstructor
@ConstructorBinding
@ConfigurationProperties(prefix = "api")
public class HolidayApiProperties {

    private final String baseUrl;
    private final String apiKey;

}
