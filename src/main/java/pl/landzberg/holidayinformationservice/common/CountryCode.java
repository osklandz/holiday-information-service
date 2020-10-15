package pl.landzberg.holidayinformationservice.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import pl.landzberg.holidayinformationservice.core.exception.IncorrectCountryCodeException;

import java.util.stream.Stream;

@Getter
@ToString
@AllArgsConstructor
public enum CountryCode {

    ALBANIA("AL"),
    ANDORRA("AD"),
    ARMENIA("AM"),
    AUSTRIA("AT"),
    AZERBAIJAN("AZ"),
    BELARUS("BY"),
    BELGIUM("BE"),
    BOSNIA_AND_HERZEGOVINA("BA"),
    BULGARIA("BG"),
    CROATIA("HR"),
    CYPRUS("CY"),
    CZECH_REPUBLIC("CZ"),
    DENMARK("DK"),
    ESTONIA("EE"),
    FINLAND("FI"),
    FRANCE("FR"),
    GEORGIA("GE"),
    GERMANY("DE"),
    GREECE("GR"),
    HUNGARY("HU"),
    ICELAND("IE"),
    IRELAND("IE"),
    ITALY("IT"),
    KAZAKHSTAN("KZ"),
    LATVIA("LV"),
    LIECHTENSTEIN("LI"),
    LITHUANIA("LT"),
    LUXEMBOURG("LU"),
    MALTA("MT"),
    MOLDOVA("MD"),
    MONACO("MC"),
    MONTENEGRO("ME"),
    NETHERLANDS("NL"),
    MACEDONIA("MK"),
    NORWAY("NO"),
    POLAND("PL"),
    PORTUGAL("PT"),
    ROMANIA("RO"),
    RUSSIA("RU"),
    SAN_MARINO("SM"),
    SERBIA("RS"),
    SLOVAKIA("SK"),
    SLOVENIA("SI"),
    SPAIN("ES"),
    SWEDEN("SE"),
    SWITZERLAND("CH"),
    TURKEY("TR"),
    UKRAINE("UA"),
    UNITED_KINGDOM("GB");

    private final String code;

    public static CountryCode parse(final String code) {
        return Stream.of(values())
                .filter(c -> c.getCode().equals(code))
                .findFirst()
                .orElseThrow(() -> new IncorrectCountryCodeException(code));
    }

}
