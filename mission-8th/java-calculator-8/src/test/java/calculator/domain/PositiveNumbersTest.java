package calculator.domain;

import static calculator.domain.PositiveNumbers.from;
import static java.util.List.of;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;

class PositiveNumbersTest {

    @Test
    void from_CreateSuccess() {
        final PositiveNumbers positiveNumbers = from(of("1", "2", "3", "3"));

        assertThat(positiveNumbers.getNumbers())
                .extracting(PositiveNumber::getValue)
                .containsExactlyInAnyOrder(1, 2, 3, 3);
    }

    @Test
    void from_CreateNullCheck() {
        assertThatThrownBy(() -> PositiveNumbers.from(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 숫자 리스트는 null 일 수 없습니다.");
    }

    @Test
    void addAll_Success() {
        final PositiveNumbers positiveNumbers = from(of("10", "20", "30"));

        final SumResult sumResult = positiveNumbers.addAll();

        assertThat(sumResult.getResult()).isEqualTo(60);
    }

    @Test
    void getNumbers_Immutable() {
        final PositiveNumbers positiveNumbers = from(of("1"));
        final List<PositiveNumber> numbers = positiveNumbers.getNumbers();

        assertThatThrownBy(() -> numbers.add(PositiveNumber.from("2")))
                .isInstanceOf(UnsupportedOperationException.class);
    }
}