package oncall.domain.exception;

import oncall.global.exception.ErrorCode;
import oncall.global.util.MessageUtils;

public enum OncallErrorCode implements ErrorCode {
    STAFFNAME_VALIDATE_MAXLENGTH("error.staffname.name.lengthlimit"),
    DAY_NUMBER_OUT_OF_RANGE("error.day.daynumber.outofrange"),
    MONTH_NUMBER_OUT_OF_RANGE("error.month.month.outofrange"),
    MONTHFACTORY_DAYOFWEEK_INVALID("error.monthfacotry.dayofweek.invalidinput"),
    MONTHFACTORY_MONTH_INVALID("error.monthfactory.month.invalidinput");

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
