package calculator.domain;

import java.util.Objects;

public class PositiveNumber {
    private final int value;

    private PositiveNumber(int value) {
        validatePositiveNumber(value);
        this.value = value;
    }

    public static PositiveNumber from(String text) {
        int parsedValue = parseToInt(text);

        return new PositiveNumber(parsedValue);
    }

    private static int parseToInt(String text) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 숫자가 아닙니다.");
        }
    }

    private static void validatePositiveNumber(int parsedValue) {
        if (parsedValue <= 0) {
            throw new IllegalArgumentException("[ERROR] 양수가 아닙니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof PositiveNumber that)) {
            return false;
        }
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    public int getValue() {
        return value;
    }
}
