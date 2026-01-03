package oncall.domain;

import java.util.List;
import oncall.domain.exception.OncallErrorCode;
import oncall.global.exception.BusinessException;
import oncall.global.util.Validator;

public class Month {
    private final int month;
    private final int monthMaxDay;
    private final List<Day> days;

    public Month(int month, int monthMaxDay, List<Day> days) {
        Validator.requireNonNulls(days, "days");
        validateMonthRange(month);

        this.month = month;
        this.monthMaxDay = monthMaxDay;
        this.days = days;
    }

    private void validateMonthRange(int month) {
        if (month > 12 || month < 1) {
            throw new BusinessException(OncallErrorCode.MONTH_NUMBER_OUT_OF_RANGE, month);
        }
    }

    public Day getDayByDayNumber(int dayIndex) {
        return days.get(dayIndex - 1);
    }

    public int getMonth() {
        return month;
    }

    public int getMonthMaxDay() {
        return monthMaxDay;
    }

    public List<Day> getDays() {
        return days;
    }
}
