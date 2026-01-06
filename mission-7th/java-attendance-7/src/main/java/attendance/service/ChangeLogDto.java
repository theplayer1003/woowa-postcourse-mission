package attendance.service;

import attendance.domain.attendance.Attendance;
import attendance.domain.attendance.AttendanceStatus;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public record ChangeLogDto(String text) {
    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("MM월 dd일 EEEE", Locale.KOREA);
    private static final DateTimeFormatter TIME_FORMATTER =
            DateTimeFormatter.ofPattern("HH:mm", Locale.KOREA);

    public static ChangeLogDto from(Attendance before, Attendance after) {
        // 12월 03일 화요일 10:07 (지각) -> 09:58 (출석) 수정 완료!
        final LocalDate beforeDate = before.getDate();
        final LocalTime beforeTime = before.getTime();
        final AttendanceStatus beforeStatus = before.getStatus();

        final LocalTime afterTime = after.getTime();
        final AttendanceStatus afterStatus = after.getStatus();

        StringBuilder sb = new StringBuilder();
        sb.append(beforeDate.format(DATE_FORMATTER));
        sb.append(" " + beforeTime.format(TIME_FORMATTER));
        sb.append(" (" + beforeStatus.getDescription() + ")");
        sb.append(" ->");
        sb.append(" " + afterTime.format(TIME_FORMATTER));
        sb.append(" (" + afterStatus.getDescription() + ")");
        sb.append(" 수정 완료!");

        return new ChangeLogDto(sb.toString());
    }
}
// 데이터만 실어보내고 리팩토링
// 출력을 위해 새로운 데이터(객체)를 만들어야할때도 있다.