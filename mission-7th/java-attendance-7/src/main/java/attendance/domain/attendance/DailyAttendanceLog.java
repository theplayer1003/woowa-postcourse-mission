package attendance.domain.attendance;

import java.time.LocalDate;
import java.time.LocalTime;

public class DailyAttendanceLog {
    private final LocalDate localDate;
    private final LocalTime time;
    private final AttendanceStatus status;

    public DailyAttendanceLog(LocalDate localDate, LocalTime time, AttendanceStatus status) {
        this.localDate = localDate;
        this.time = time;
        this.status = status;
    }

    public boolean checkAttendanceStatus(AttendanceStatus target) {
        if (status.equals(target)) {
            return true;
        }

        return false;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public LocalTime getTime() {
        return time;
    }

    public AttendanceStatus getStatus() {
        return status;
    }
}
