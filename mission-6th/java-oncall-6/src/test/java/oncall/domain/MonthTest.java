package oncall.domain;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.time.DayOfWeek;
import org.junit.jupiter.api.Test;

class MonthTest {

    @Test
    void Month_CreateSuccess() {
        final Month jan = MonthFactory.of("1", "월");

        final Day janFirst = jan.getDayByDayNumber(1);
        assertThat(janFirst.equals(
                new Day(1,
                        DayOfWeek.MONDAY,
                        LegalHoliday.findByMonthAndDay(1, 1))))
                .isTrue();
    }
    
    @Test
    void getDayByDayNumber(){
        final Month jan = MonthFactory.of("1", "월");

        final Day janThird = jan.getDayByDayNumber(3);

        assertThat(janThird.isWeekendOrHoliday()).isFalse();
    }
}