package oncall.domain.exception;

import oncall.global.exception.ErrorCode;
import oncall.global.util.MessageUtils;

public enum OncallErrorCode implements ErrorCode {
    STAFFNAME_VALIDATE_MAXLENGTH("error.staffname.name.lengthlimit");

    private final String messageKey;

    OncallErrorCode(String messageKey) {
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
