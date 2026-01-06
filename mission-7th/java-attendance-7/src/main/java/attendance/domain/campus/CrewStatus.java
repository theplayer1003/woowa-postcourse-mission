package attendance.domain.campus;

import attendance.domain.attendance.AttendanceStatus;
import attendance.domain.crew.Crew;
import java.util.Arrays;
import java.util.function.Predicate;

public enum CrewStatus {
    EXPULSION("제적 대상자", count -> count > 5),
    COUNSELING("면담 대상자", count -> count > 2),
    WARNING("경고 대상자", count -> count > 1),
    NONE("정상", count -> true);

    private final String description;
    private final Predicate<Integer> crewStatusRule;

    CrewStatus(String description, Predicate<Integer> crewStatusRule) {
        this.description = description;
        this.crewStatusRule = crewStatusRule;
    }

    public static CrewStatus from(int totalCount) {
        return Arrays.stream(CrewStatus.values())
                .filter(crewStatus -> crewStatus.crewStatusRule.test(totalCount))
                .findFirst()
                .orElse(NONE);
    }

    public String getDescription() {
        return this.description;
    }
}
