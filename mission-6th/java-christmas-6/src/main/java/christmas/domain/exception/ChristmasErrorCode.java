package christmas.domain.exception;

import christmas.global.exception.ErrorCode;
import christmas.global.util.MessageUtils;

public enum ChristmasErrorCode implements ErrorCode {
    PRICE_CANNOT_NEGATIVE("error.menu.menuprice.negative"),
    DATE_RANGE_INVALID("error.visitdate.date.range"),
    DUMMY("dummy");

    private final String messageKey;

    ChristmasErrorCode(String messageKey) {
        this.messageKey = messageKey;
    }

    @Override
    public String getMessage() {
        return MessageUtils.getMessage(messageKey);
    }

    @Override
    public String getMessage(Object... args) {
        return MessageUtils.getMessage(messageKey, args);
    }
}
