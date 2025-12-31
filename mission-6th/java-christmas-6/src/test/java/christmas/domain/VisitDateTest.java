package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.global.exception.BusinessException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class VisitDateTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 31})
    void visitDate_CreateSuccess(int day) {
        assertThatCode(() -> new VisitDate(day))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 32})
    void visitDate_CreateFail_invalidRange(int day) {
        assertThatThrownBy(() -> new VisitDate(day))
                .isInstanceOf(BusinessException.class)
                .hasMessageContaining("날짜는 1 에서 31 사이의 숫자여야 합니다. " + day);
    }

    @ParameterizedTest
    @CsvSource(value = {"1,true", "25,true", "26,false", "31,false"})
    void isDdayPeriod(int day, boolean expected) {
        final VisitDate visitDate = new VisitDate(day);

        assertThat(visitDate.isDdayPeriod()).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"3,true", "7,true", "8,false", "9,false"})
    void isWeekdayPeriod(int day, boolean expected) {
        final VisitDate visitDate = new VisitDate(day);

        assertThat(visitDate.isWeekdaysPeriod()).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"3,false", "7,false", "8,true", "9,true"})
    void isWeekendPeriod(int day, boolean expected) {
        final VisitDate visitDate = new VisitDate(day);

        assertThat(visitDate.isWeekendPeriod()).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"3,true", "4,false", "24,true", "25,true", "26,false"})
    void isSpecialdayPeriod(int day, boolean expected) {
        final VisitDate visitDate = new VisitDate(day);

        assertThat(visitDate.isSpecialdayPeriod()).isEqualTo(expected);
    }

    @Test
    void equalsHashCode() {
        final VisitDate date1 = new VisitDate(15);
        final VisitDate date2 = new VisitDate(15);

        assertThat(date1).isEqualTo(date2);

        assertThat(date1).hasSameHashCodeAs(date2);
    }
}