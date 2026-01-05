package attendance.domain;

import java.time.DayOfWeek;
import java.util.Objects;

public class Day {
    private final int dayNumber;
    private final DayOfWeek dof;

    public Day(int dayNumber, DayOfWeek dof) {
        this.dayNumber = dayNumber;
        this.dof = dof;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Day day)) {
            return false;
        }
        return dayNumber == day.dayNumber && dof == day.dof;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dayNumber, dof);
    }

    public int getDayNumber() {
        return dayNumber;
    }

    public DayOfWeek getDof() {
        return dof;
    }
}
