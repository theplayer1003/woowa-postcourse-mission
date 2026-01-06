package attendance.domain.attendance;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

public class Attendance {
    private final AttendanceStatus status;
    private final LocalDate date;
    private final LocalTime time;

    public Attendance(AttendanceStatus status, LocalDate date, LocalTime time) {
        this.status = status;
        this.date = date;
        this.time = time;
    }

    public static Attendance copyOf(Attendance attendance) {
        return new Attendance(
                attendance.status,
                attendance.date,
                attendance.time
        );
    }

    public boolean isSameDate(LocalDate target) {
        if (date.equals(target)) {
            return true;
        }

        return false;
    }

    public boolean checkAttendanceStatus(AttendanceStatus target) {
        if (status.equals(target)) {
            return true;
        }

        return false;
    }

    public AttendanceStatus getStatus() {
        return status;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }
}
