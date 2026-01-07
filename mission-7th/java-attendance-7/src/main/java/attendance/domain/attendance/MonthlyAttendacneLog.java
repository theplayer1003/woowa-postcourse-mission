package attendance.domain.attendance;

import attendance.domain.campus.CrewStatus;
import java.util.List;

public class MonthlyAttendacneLog {
    private final String name;
    private final List<DailyAttendanceLog> logs;

    public MonthlyAttendacneLog(String name, List<DailyAttendanceLog> logs) {
        this.name = name;
        this.logs = logs;
    }

    public int countAttendanceStatus(AttendanceStatus target) {
        return (int) logs.stream()
                .filter(attendance -> attendance.checkAttendanceStatus(target))
                .count();
    }

    public CrewStatus getStatus() {
        final int lateCount = this.countAttendanceStatus(AttendanceStatus.LATE);
        final int absentCount = this.countAttendanceStatus(AttendanceStatus.ABSENT);

        final int totalCount = (lateCount / 3) + absentCount;

        return CrewStatus.from(totalCount);
    }

    public List<DailyAttendanceLog> getLogs() {
        return logs;
    }

    public String getName() {
        return name;
    }
}
