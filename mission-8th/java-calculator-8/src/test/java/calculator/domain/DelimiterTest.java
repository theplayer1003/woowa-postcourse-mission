package calculator.domain;

import static calculator.domain.Delimiter.from;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class DelimiterTest {

    @Test
    void from_CreateSuccess(){
        final Delimiter delimiter = from("d");

        assertThat(delimiter.getValue()).isEqualTo("d");
    }

    @ParameterizedTest
    @ValueSource(strings = {"ab", "bcd", "asdf"})
    void from_CreateValidateNotChar(String value){
        assertThatThrownBy(() -> from(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 구분자는 하나의 문자여야 합니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "2", "3"})
    void from_CreateValidateNotNumber(String value){
        assertThatThrownBy(() -> from(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 숫자는 구분자가 될 수 없습니다.");
    }

    @Test
    void from_CreateNullCheck(){
        assertThatThrownBy(() -> from(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining("[ERROR] 구분자는 null 일 수 없습니다.");
    }
}