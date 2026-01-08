package attendance.service.dto;

import attendance.domain.attendance.DailyAttendanceLog;
import java.util.List;

public record AttendanceLogDto(
        String name,
        List<DailyAttendanceLog> logs,
        int attendanceCount,
        int lateCount,
        int absentCount,
        String crewStatus
) {
}