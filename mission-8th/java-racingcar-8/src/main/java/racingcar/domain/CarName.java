package racingcar.domain;

import java.util.Objects;
import racingcar.exception.BusinessException;
import racingcar.exception.RacingCarErrorCode;
import racingcar.util.Validator;

public record CarName(String carName) {
    public static final int MAXIMUM_CARNAME_LENGTH = 5;

    public CarName {
        validateNull(carName);
        validateBlank(carName);
        validateLength(carName);
    }

    private void validateNull(String carName) {
        if (carName == null) {
            throw new BusinessException(RacingCarErrorCode.CAR_NAME_IS_NULL);
        }
    }

    private void validateBlank(String carName) {
        if (carName.isBlank()) {
            throw new BusinessException(RacingCarErrorCode.CAR_NAME_IS_BLANK);
        }
    }

    private void validateLength(String carName) {
        if (carName.length() > MAXIMUM_CARNAME_LENGTH) {
            throw new BusinessException(RacingCarErrorCode.CAR_NAME_LENGTH_INVALID, MAXIMUM_CARNAME_LENGTH,
                    carName.length());
        }
    }
}
