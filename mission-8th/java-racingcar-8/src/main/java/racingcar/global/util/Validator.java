package racingcar.global.util;

import racingcar.global.exception.BusinessException;
import racingcar.exception.RacingCarErrorCode;

public class Validator {

    public static <T> T requireNonNull(T target, String message) {
        if (target == null) {
            throw new IllegalArgumentException(message);
        }

        return target;
    }

    public static <T> T requireNonNull(T target) {
        if (target == null) {
            throw new BusinessException(RacingCarErrorCode.CAR_NAME_IS_NULL);
        }

        return target;
    }
}
