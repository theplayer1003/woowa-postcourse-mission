package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import racingcar.exception.BusinessException;

class CarNameTest {

    @Test
    void CarName_validateNull() {
        assertThatThrownBy(() -> new CarName(null))
                .isInstanceOf(BusinessException.class)
                .hasMessageContaining("자동차 이름은 NULL 일 수 없습니다");
    }

    @Test
    void CarName_validateBlank() {
        assertThatThrownBy(() -> new CarName(""))
                .isInstanceOf(BusinessException.class)
                .hasMessageContaining("자동차 이름은 공백 일 수 없습니다");
    }

    @ParameterizedTest
    @ValueSource(strings = {"abcdef", "abcdefg"})
    void CarName_validateLength(String value) {
        assertThatThrownBy(() -> new CarName(value))
                .isInstanceOf(BusinessException.class)
                .hasMessageContaining("자동차 이름은 " + CarName.MAXIMUM_CARNAME_LENGTH + " 글자 이하여야 합니다. 입력값: " + value.length());
    }
}