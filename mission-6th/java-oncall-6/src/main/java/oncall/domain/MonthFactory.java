package oncall.domain;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import oncall.domain.exception.OncallErrorCode;
import oncall.global.exception.BusinessException;

public class MonthFactory {

    private static final Map<Integer, Integer> MONTH_MAX_DAYS = Map.ofEntries(
            Map.entry(1, 31), Map.entry(2, 28),
            Map.entry(3, 31), Map.entry(4, 30),
            Map.entry(5, 31), Map.entry(6, 30),
            Map.entry(7, 31), Map.entry(8, 31),
            Map.entry(9, 30), Map.entry(10, 31),
            Map.entry(11, 30), Map.entry(12, 31)
    );

    private MonthFactory() {
    }

    public static Month of(String month, String dayOfWeek) {
        final int monthNumber = parseMonthNubmer(month);
        DayOfWeek currentDayOfWeek = parseDayOfWeek(dayOfWeek);
        final Integer monthMaxDay = MONTH_MAX_DAYS.get(monthNumber);

        final List<Day> days = getDays(monthNumber, currentDayOfWeek, monthMaxDay);

        return new Month(monthNumber, monthMaxDay ,days);
    }

    private static List<Day> getDays(int monthNumber, DayOfWeek currentDayOfWeek, Integer monthMaxDay) {
        List<Day> days = new ArrayList<>();

        for (int dayNumber = 1; dayNumber <= monthMaxDay; dayNumber++) {
            LegalHoliday legalHoliday = LegalHoliday.findByMonthAndDay(monthNumber, dayNumber);
            final Day day = new Day(dayNumber, currentDayOfWeek, legalHoliday);
            days.add(day);
            currentDayOfWeek = currentDayOfWeek.plus(1);
        }
        return days;
    }

    private static DayOfWeek parseDayOfWeek(String dayOfWeek) {
        if (dayOfWeek.equals("월")) {
            return DayOfWeek.MONDAY;
        }
        if (dayOfWeek.equals("화")) {
            return DayOfWeek.TUESDAY;
        }
        if (dayOfWeek.equals("수")) {
            return DayOfWeek.WEDNESDAY;
        }
        if (dayOfWeek.equals("목")) {
            return DayOfWeek.THURSDAY;
        }
        if (dayOfWeek.equals("금")) {
            return DayOfWeek.FRIDAY;
        }
        if (dayOfWeek.equals("토")) {
            return DayOfWeek.SATURDAY;
        }
        if (dayOfWeek.equals("일")) {
            return DayOfWeek.SUNDAY;
        }

        throw new BusinessException(OncallErrorCode.MONTHFACTORY_DAYOFWEEK_INVALID, dayOfWeek);
    }

    private static int parseMonthNubmer(String month) {
        int monthNumber;

        try {
            monthNumber = Integer.parseInt(month);
        } catch (NumberFormatException e) {
            throw new BusinessException(OncallErrorCode.MONTHFACTORY_MONTH_INVALID, month);
        }

        return monthNumber;
    }
}
