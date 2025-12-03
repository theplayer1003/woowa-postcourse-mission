package calculator.util;

public class Validator {

    private Validator() {
    }

    public static <T> T requireNonNull(T object, String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }

        return object;
    }
}
