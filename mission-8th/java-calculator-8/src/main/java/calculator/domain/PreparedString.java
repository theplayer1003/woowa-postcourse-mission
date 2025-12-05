package calculator.domain;

import java.util.List;

public class PreparedString {
    private final String value;
    private final Delimiters delimiters;

    public PreparedString(String value, Delimiters delimiters) {
        this.value = value;
        this.delimiters = delimiters;
    }

    public static PreparedString from(String trimedUserInupt, Delimiters delimiters) {
        return new PreparedString(trimedUserInupt, delimiters);
    }

    public List<String> extractNumberString() {
        final List<String> extractNumbers = delimiters.split(value);

        return extractNumbers;
    }

    public String getValue() {
        return value;
    }
}
