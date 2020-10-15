package pl.landzberg.holidayinformationservice.core.web;

import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class UpperCaseAttributeConverter implements GenericConverter {

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {

        final ConvertiblePair[] pairs = new ConvertiblePair[] {
                new ConvertiblePair(String.class, String.class)
        };

        return Stream.of(pairs).collect(Collectors.toUnmodifiableSet());
    }

    @Override
    public Object convert(final Object source, final TypeDescriptor sourceType, final TypeDescriptor targetType) {
        if (targetType.hasAnnotation(UpperCase.class)) {
            return ((String) source).toUpperCase();
        }

        return source;
    }

}
