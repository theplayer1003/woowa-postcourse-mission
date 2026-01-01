package christmas.global.util;

import christmas.global.exception.BusinessException;
import christmas.global.exception.GlobalErrorCode;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class Validator {

    public static <T> T requireNonNull(T target, String subject) {
        if (target == null) {
            throw new BusinessException(GlobalErrorCode.PARAMETER_REQUIRED_NOT_NULL, subject);
        }

        return target;
    }

    public static <T> Collection<T> requireNonNulls(Collection<T> targets, String subject) {
        requireNonNull(targets, subject);

        final boolean hasNull = targets.stream()
                .anyMatch(Objects::isNull);

        if (hasNull) {
            throw new BusinessException(GlobalErrorCode.PARAMETER_REQUIRED_NOT_NULL, subject);
        }

        return targets;
    }

    public static String requireNonNullOrBlank(String target, String subject) {
        if (target == null || target.isBlank()) {
            throw new BusinessException(GlobalErrorCode.PARAMETER_REQUIRED_NOT_BLANK, subject);
        }

        return target;
    }
}
