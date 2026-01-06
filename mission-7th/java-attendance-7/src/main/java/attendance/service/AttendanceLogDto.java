package attendance.service;

import attendance.domain.attendance.Attendance;
import attendance.domain.attendance.AttendanceStatus;
import attendance.domain.attendance.DailyAttendanceLog;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public record AttendanceLogDto(
        String nickname,
        List<DailyAttendanceLog> logs,
        int attendanceCount,
        int lateCount,
        int absentCount,
        String crewStatus

) {
}