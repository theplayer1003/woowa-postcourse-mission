package attendance.domain.attendance;

import java.util.List;

public class MonthlyAttendacneLog {
    private final List<DailyAttendanceLog> logs;

    public MonthlyAttendacneLog(List<DailyAttendanceLog> logs) {
        this.logs = logs;
    }

    public int countAttendanceStatus(AttendanceStatus target) {
        return (int) logs.stream()
                .filter(attendance -> attendance.checkAttendanceStatus(target))
                .count();
    }

    public List<DailyAttendanceLog> getLogs() {
        return logs;
    }
}
