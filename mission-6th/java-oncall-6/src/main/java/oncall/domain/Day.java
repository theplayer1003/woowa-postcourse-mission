package oncall.domain;

import java.time.DayOfWeek;
import java.util.Objects;
import oncall.domain.exception.OncallErrorCode;
import oncall.global.exception.BusinessException;
import oncall.global.util.Validator;

public class Day {
    private final int dayNumber;
    private final DayOfWeek dayOfWeek;
    private final LegalHoliday legalHoliday;

    public Day(int dayNumber, DayOfWeek dayOfWeek, LegalHoliday legalHoliday) {
        Validator.requireNonNull(dayOfWeek, "dayofweek");
        Validator.requireNonNull(legalHoliday, "legalholiday");
        validateDayRange(dayNumber);

        this.dayNumber = dayNumber;
        this.dayOfWeek = dayOfWeek;
        this.legalHoliday = legalHoliday;
    }

    private void validateDayRange(int dayNumber) {
        if (dayNumber < 1 || dayNumber > 31) {
            throw new BusinessException(OncallErrorCode.DAY_NUMBER_OUT_OF_RANGE, dayNumber);
        }
    }

    public boolean isWeekDay() {
        if (dayOfWeek != DayOfWeek.SATURDAY
                && dayOfWeek != DayOfWeek.SUNDAY
                && legalHoliday == LegalHoliday.NONE) {
            return true;
        }
        return false;
    }

    public boolean isWeekendOrHoliday() {
        if (dayOfWeek == DayOfWeek.SATURDAY
                || dayOfWeek == DayOfWeek.SUNDAY
                || legalHoliday != LegalHoliday.NONE) {
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Day day)) {
            return false;
        }
        return dayNumber == day.dayNumber && dayOfWeek == day.dayOfWeek && legalHoliday == day.legalHoliday;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dayNumber, dayOfWeek, legalHoliday);
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public LegalHoliday getLegalHoliday() {
        return legalHoliday;
    }
}
