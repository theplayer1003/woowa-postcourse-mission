package attendance.domain.attendance;

import java.time.LocalDate;
import java.time.LocalTime;

public class Attendance {
    private final AttendanceStatus status;
    private final LocalDate date;
    private final LocalTime time;

    public Attendance(AttendanceStatus status, LocalDate date, LocalTime time) {
        this.status = status;
        this.date = date;
        this.time = time;
    }
}
