package attendance.domain.crew;

import attendance.domain.attendance.Attendance;
import attendance.domain.attendance.AttendanceStatus;
import attendance.domain.campus.StudyTime;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

public class Crew {
    private final String nickName;
    private final List<Attendance> attendLog;

    public Crew(String nickName, List<Attendance> attendLog) {
        this.nickName = nickName;
        this.attendLog = attendLog;
    }

    public boolean findByName(String target) {
        if (nickName == target) {
            return true;
        }
        return false;
    }

    public void addAttendLog(LocalDateTime targetTime) {
        final StudyTime rule = StudyTime.findBy(targetTime);
        final int timeDiffrence = rule.calculateDifferent(LocalTime.from(targetTime));

        final AttendanceStatus targetStatus = AttendanceStatus.from(timeDiffrence);
        final Attendance attendance = new Attendance(targetStatus, LocalDate.from(targetTime),
                LocalTime.from(targetTime));

        attendLog.add(attendance);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Crew crew)) {
            return false;
        }
        return Objects.equals(nickName, crew.nickName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nickName);
    }
}
