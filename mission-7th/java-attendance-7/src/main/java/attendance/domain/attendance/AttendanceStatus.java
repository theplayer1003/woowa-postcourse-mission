package attendance.domain.attendance;

import java.util.Arrays;
import java.util.function.Predicate;

public enum AttendanceStatus {
    ABSENT("결석", minute -> minute > 30),
    LATE("지각", minute -> minute > 5),
    ATTENDANCE("출석", minute -> true);

    private final String description;
    private final Predicate<Integer> attendanceRule;

    AttendanceStatus(String description, Predicate<Integer> attendanceRule) {
        this.description = description;
        this.attendanceRule = attendanceRule;
    }

    public static AttendanceStatus from(int minute) {
        return Arrays.stream(values())
                .filter(status -> status.attendanceRule.test(minute))
                .findFirst()
                .orElseThrow();
    }
}
