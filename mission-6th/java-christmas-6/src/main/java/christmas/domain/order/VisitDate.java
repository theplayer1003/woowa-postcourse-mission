package christmas.domain.order;

import christmas.domain.exception.ChristmasErrorCode;
import christmas.global.exception.BusinessException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;

public class VisitDate {
    private static final int YEAR = 2023;
    private static final int MONTH = Month.DECEMBER.getValue();
    private static final List<Integer> SPECIAL_DAYS = List.of(3, 10, 17, 24, 25, 31);

    private final LocalDate visitDate;

    public VisitDate(int day) {
        validateDateRange(day);

        this.visitDate = LocalDate.of(YEAR, MONTH, day);
    }

    private void validateDateRange(int day) {
        if (day < 1 || day > 31) {
            throw new BusinessException(ChristmasErrorCode.DATE_RANGE_INVALID, day);
        }
    }

    public boolean isDdayPeriod() {
        final int day = visitDate.getDayOfMonth();
        return day >= 1 && day <= 25;
    }

    public int calculateDaysSinceEventStart() {
        final LocalDate eventStartDate = LocalDate.of(YEAR, MONTH, 1);

        return (int) ChronoUnit.DAYS.between(eventStartDate, visitDate);
    }

    public boolean isWeekdaysPeriod() {
        final DayOfWeek day = visitDate.getDayOfWeek();
        return day != DayOfWeek.FRIDAY && day != DayOfWeek.SATURDAY;
    }

    public boolean isWeekendPeriod() {
        final DayOfWeek day = visitDate.getDayOfWeek();
        return day == DayOfWeek.FRIDAY || day == DayOfWeek.SATURDAY;
    }

    public boolean isSpecialdayPeriod() {
        final int day = visitDate.getDayOfMonth();
        return SPECIAL_DAYS.contains(day);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof VisitDate visitDate1)) {
            return false;
        }
        return Objects.equals(visitDate, visitDate1.visitDate);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(visitDate);
    }

    public LocalDate getVisitDate() {
        return visitDate;
    }
}
