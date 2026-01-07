package attendance.service;

import attendance.domain.attendance.Attendance;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record AttendanceRegistResponseDto(LocalDate date, LocalTime time, String status) {
    public static AttendanceRegistResponseDto from(Attendance attendance) {
        return new AttendanceRegistResponseDto(
                attendance.getDate(),
                attendance.getTime(),
                attendance.getStatus().getDescription());
    }
}
