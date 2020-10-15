package pl.landzberg.holidayinformationservice.core.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
class SystemPropertiesInitializer {

    SystemPropertiesInitializer(@Value("${api.system.property.key}") final String key,
            @Value("${api.system.property.value}") final String value) {
        System.setProperty(key, value);
    }

}
