package christmas.global.util;

import christmas.global.exception.BusinessException;
import christmas.global.exception.GlobalErrorCode;

public class Validator {

    public static <T> T requireNonNull(T target, String subject) {
        if (target == null) {
            throw new BusinessException(GlobalErrorCode.PARAMETER_REQUIRED_NOT_NULL, subject);
        }

        return target;
    }

    public static String requireNonNullOrBlank(String target, String subject) {
        if (target == null || target.isBlank()) {
            throw new BusinessException(GlobalErrorCode.PARAMETER_REQUIRED_NOT_BLANK, subject);
        }

        return target;
    }
}
