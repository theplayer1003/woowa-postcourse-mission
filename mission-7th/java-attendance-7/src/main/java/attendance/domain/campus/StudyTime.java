package attendance.domain.campus;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

public enum StudyTime {
    MONDAY("월요일", DayOfWeek.MONDAY, LocalTime.of(13, 0)),
    TUESDAY("화요일", DayOfWeek.TUESDAY, LocalTime.of(10, 0)),
    WEDNESDAY("수요일", DayOfWeek.WEDNESDAY, LocalTime.of(10, 0)),
    THURSDAY("목요일", DayOfWeek.THURSDAY, LocalTime.of(10, 0)),
    FRIDAY("금요일", DayOfWeek.FRIDAY, LocalTime.of(10, 0));

    private static final List<LocalDate> OFFICIAL_HOLIDAYS = List.of(
            LocalDate.of(2024, 12, 25)
    );

    private final String description;
    private final DayOfWeek dow;
    private final LocalTime openLocalTime;

    StudyTime(String description, DayOfWeek dow, LocalTime openLocalTime) {
        this.description = description;
        this.dow = dow;
        this.openLocalTime = openLocalTime;
    }

    public static StudyTime findBy(LocalDateTime target) { // target 은 출석 등록하려는 시간
        return Arrays.stream(StudyTime.values())
                .filter(studyTime -> studyTime.dow == target.getDayOfWeek())
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("등교하는 날이 아닙니다"));
    }

    public int calculateDifferent(LocalTime target) {
        final long between = ChronoUnit.MINUTES.between(this.openLocalTime,
                target); // target 이 기준보다 미래면 양수 같으면 0 과거면 음수

        return (int) between;
    }

    public static boolean isSchoolDay(LocalDate date) {
        if (OFFICIAL_HOLIDAYS.contains(date)) {
            return false;
        }

        return Arrays.stream(StudyTime.values())
                .anyMatch(studyTime -> studyTime.dow == date.getDayOfWeek());
    }
}
