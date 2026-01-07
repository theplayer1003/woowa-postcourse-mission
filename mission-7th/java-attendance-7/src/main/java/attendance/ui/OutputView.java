package attendance.ui;

import attendance.service.AttendanceLogDto;
import attendance.service.AttendanceRegistResponseDto;
import attendance.service.ChangeLogDto;
import attendance.service.CrewConditionDto;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public class OutputView {
    private final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("MM월 dd일 EEEE", Locale.KOREA);
    private final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm", Locale.KOREA);

    public void printAttendanceRegist(AttendanceRegistResponseDto dto) {
        StringBuilder sb = new StringBuilder();

        sb.append("\n")
                .append(dto.date().format(DATE_FORMATTER))
                .append(" ")
                .append(dto.time().format(TIME_FORMATTER))
                .append(" (")
                .append(dto.status())
                .append(")");

        System.out.println(sb.toString());
    }

    public void printAttendanceChange(ChangeLogDto dto) {
        //12월 03일 화요일 10:07 (지각) -> 09:58 (출석) 수정 완료!

        System.out.println();
        System.out.println(dto.text());
    }

    public void printAttendanceLog(AttendanceLogDto dto) {
        System.out.println("이번 달 " + dto.name() + "의 출석 기록입니다.");

        dto.logs().stream()
                .forEach(daily -> {
                    System.out.print(
                            daily.getLocalDate().format(DATE_FORMATTER) +
                                    " ");

                    if (daily.getTime() == null) {
                        System.out.print("--:-- ");
                    }

                    if (daily.getTime() != null) {
                        System.out.print(daily.getTime().format(TIME_FORMATTER) + " ");
                    }

                    System.out.println("(" + daily.getStatus().getDescription() + ")");
                });
        System.out.println();

        System.out.println("출석: " + dto.attendanceCount());
        System.out.println("지각: " + dto.lateCount());
        System.out.println("결석: " + dto.absentCount());
        System.out.println();

        System.out.println(dto.crewStatus() + "입니다.");
    }

    public void printAllCrewCondition(List<CrewConditionDto> dto) {
        System.out.println("제적 위험자 조회 결과");
        dto.stream()
                .forEach(crewCondition -> {
                    System.out.println("- " +
                            crewCondition.name() + ": " +
                            "결석 " + crewCondition.lateCount() + "회, " +
                            "지각 " + crewCondition.lateCount() + "회" +
                            " (" + crewCondition.status() + ")"
                    );
                });
    }
}
