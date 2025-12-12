package subway.ui.exception;

import subway.global.exception.ErrorCode;
import subway.global.util.MessageUtils;

public enum ConsoleErrorCode implements ErrorCode {
    USERINPUT_INVALID_BLANK("uierror.inputview.getuserinput.blank"),
    USERINPUT_NOT_NUMBER("uierror.inputview.getuserinput.notnumber");

    private final String messageKey;

    ConsoleErrorCode(String messageKey) {
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
