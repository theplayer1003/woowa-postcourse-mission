package racingcar.global.exception;

public interface ErrorCode {
    String name();

    String getMessage();

    String getMessage(Object... args);
}
