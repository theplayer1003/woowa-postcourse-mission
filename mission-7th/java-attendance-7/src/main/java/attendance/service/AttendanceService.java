package attendance.service;

import attendance.domain.crew.Crew;
import attendance.domain.crew.Crews;
import java.time.LocalDateTime;

public class AttendanceService {
    private final Crews crews;

    public AttendanceService(Crews crews) {
        this.crews = crews;
    }

    public void registAttendance(String targetName, LocalDateTime targetTime) {
        final Crew targetCrew = crews.findByName(targetName);

        targetCrew.addAttendLog(targetTime);
    }
}
