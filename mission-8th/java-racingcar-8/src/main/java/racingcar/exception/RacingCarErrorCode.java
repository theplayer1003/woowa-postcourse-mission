package racingcar.exception;

import racingcar.global.exception.ErrorCode;
import racingcar.global.util.MessageUtils;

public enum RacingCarErrorCode implements ErrorCode {
    CAR_NAME_IS_NULL("error.car.name.null"),
    CAR_NAME_IS_BLANK("error.car.name.blank"),
    CAR_NAME_LENGTH_INVALID("error.car.name.length");

    private final String messageKey;

    RacingCarErrorCode(String messageKey) {
        this.messageKey = messageKey;
    }

    @Override
    public String getMessage() {
        return MessageUtils.getMessage(messageKey);
    }

    public String getMessage(Object... args) {
        return MessageUtils.getMessage(messageKey, args);
    }
}
