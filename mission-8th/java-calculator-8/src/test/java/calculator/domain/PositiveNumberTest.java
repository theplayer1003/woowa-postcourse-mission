package calculator.domain;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PositiveNumberTest {

    @Test
    void from_CreateSuccess() {
        final PositiveNumber positiveNumber = PositiveNumber.from("1");

        assertThat(positiveNumber.getValue()).isEqualTo(1);
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", " ", "!", "1.5"})
    void from_CreateValidateNotNumber() {
        assertThatThrownBy(() -> PositiveNumber.from("a"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 숫자가 아닙니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "-1", "-100"})
    void from_CreateValidatePositiveNumber() {
        assertThatThrownBy(() -> PositiveNumber.from("0"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 양수가 아닙니다.");
    }
}