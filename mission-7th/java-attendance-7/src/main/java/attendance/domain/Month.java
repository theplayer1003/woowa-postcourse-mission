package attendance.domain;

import java.time.DayOfWeek;
import java.util.List;

public class Month {
    private final int dayNumber;
    private final List<Day> days;

    public Month(int dayNumber, List<Day> days) {
        this.dayNumber = dayNumber;
        this.days = days;
    }

    public int getDayNumber() {
        return dayNumber;
    }

    public List<Day> getDays() {
        return days;
    }
}
