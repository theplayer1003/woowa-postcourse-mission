package oncall.global.exception;

import oncall.global.util.MessageUtils;

public enum GlobalErrorCode implements ErrorCode{
    PARAMETER_REQUIRED_NOT_NULL("error.common.input.null"),
    PARAMETER_REQUIRED_NOT_NULL_IN_LIST("error.common.input.nullinlist"),
    PARAMETER_REQUIRED_NOT_BLANK("error.common.input.empty");

    private final String messageKey;

    GlobalErrorCode(String messageKey) {
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
