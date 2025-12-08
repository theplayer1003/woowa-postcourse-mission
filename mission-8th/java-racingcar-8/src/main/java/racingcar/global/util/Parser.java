package racingcar.global.util;

import racingcar.global.exception.BusinessException;
import racingcar.global.exception.ErrorCode;

public class Parser {

    public static int toInt(String input, ErrorCode errorCode) {
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            throw new BusinessException(errorCode);
        }
    }
}
