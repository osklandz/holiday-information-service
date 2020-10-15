package pl.landzberg.holidayinformationservice.core.web;

import java.lang.annotation.*;

@Documented
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface UpperCase {
}
