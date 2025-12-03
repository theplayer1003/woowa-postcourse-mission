package calculator.domain;

import java.util.Objects;

public class Delimiter {
    private final String value;

    private Delimiter(String value) {
        Objects.requireNonNull(value, "[ERROR] 구분자는 null 일 수 없습니다.");

        validateSingleCharacter(value);
        validateNotNumber(value);
        this.value = value;
    }

    public static Delimiter from(String text) {
        return new Delimiter(text);
    }

    private void validateSingleCharacter(String value) {
        if (value.length() != 1) {
            throw new IllegalArgumentException("[ERROR] 구분자는 하나의 문자여야 합니다.");
        }
    }

    private void validateNotNumber(String value) {
        if (Character.isDigit(value.charAt(0))) {
            throw new IllegalArgumentException("[ERROR] 숫자는 구분자가 될 수 없습니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Delimiter delimiter)) {
            return false;
        }
        return Objects.equals(value, delimiter.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    public String getValue() {
        return value;
    }
}
