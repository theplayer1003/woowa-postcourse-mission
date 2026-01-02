package oncall.domain;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.time.DayOfWeek;
import org.junit.jupiter.api.Test;

class DayTest {

    @Test
    void day_CreateSuccess(){
        final Day holiday = new Day(1, DayOfWeek.MONDAY, LegalHoliday.MARCHFIRST);

        assertThat(holiday).isNotNull();

        assertThat(holiday.isWeekDay()).isFalse();
        assertThat(holiday.isWeekendOrHoliday()).isTrue();
    }

    @Test
    void isWeekDay(){
        final Day weekday = new Day(1, DayOfWeek.MONDAY, LegalHoliday.NONE);
        final Day weekend = new Day(1, DayOfWeek.SATURDAY, LegalHoliday.NONE);
        final Day holiday = new Day(1, DayOfWeek.MONDAY, LegalHoliday.MARCHFIRST);

        assertThat(weekday.isWeekDay()).isTrue();
        assertThat(weekend.isWeekDay()).isFalse();
        assertThat(holiday.isWeekDay()).isFalse();
    }
    
    @Test
    void isWeekendOrHoliday(){
        final Day weekday = new Day(1, DayOfWeek.MONDAY, LegalHoliday.NONE);
        final Day weekend = new Day(1, DayOfWeek.SATURDAY, LegalHoliday.NONE);
        final Day holiday = new Day(1, DayOfWeek.MONDAY, LegalHoliday.MARCHFIRST);
        
        assertThat(weekday.isWeekendOrHoliday()).isFalse();
        assertThat(weekend.isWeekendOrHoliday()).isTrue();
        assertThat(holiday.isWeekendOrHoliday()).isTrue();
    }
}