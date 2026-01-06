package attendance.domain.attendance;

import java.time.LocalDate;
import java.time.LocalTime;

public record DailyAttendanceLog(LocalDate date, LocalTime time, String status) {
}
