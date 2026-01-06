package attendance.service;

import attendance.domain.attendance.Attendance;
import attendance.domain.attendance.AttendanceStatus;
import attendance.domain.attendance.DailyAttendanceLog;
import attendance.domain.attendance.MonthlyAttendacneLog;
import attendance.domain.campus.CrewStatus;
import attendance.domain.crew.Crew;
import attendance.domain.crew.Crews;
import camp.nextstep.edu.missionutils.DateTimes;
import java.time.LocalDateTime;
import java.util.List;

public class AttendanceService {
    private final Crews crews;

    public AttendanceService(Crews crews) {
        this.crews = crews;
    }

    public void registAttendance(String targetName, LocalDateTime targetTime) {
        final Crew targetCrew = crews.findByName(targetName);

        final Attendance attendance = targetCrew.addAttendLog(targetTime);
    }

    public ChangeLogDto changeAttendance(String targetName, LocalDateTime targetTime) {
        final Crew targetCrew = crews.findByName(targetName);

        final Attendance beforeChange = targetCrew.findAndCopyAttendanceBy(targetTime);
        final Attendance afterChange = targetCrew.changeLog(targetTime);

        return ChangeLogDto.from(beforeChange, afterChange);
    }

    public AttendanceLogDto findAttendanceInfoByName(String targetName) {
        final Crew targetCrew = crews.findByName(targetName);

        final MonthlyAttendacneLog monthlyHistory = targetCrew.getMonthlyHistory(DateTimes.now());

        final int attendCount = monthlyHistory.countAttendanceStatus(AttendanceStatus.ATTENDANCE);
        final int lateCount = monthlyHistory.countAttendanceStatus(AttendanceStatus.LATE);
        final int absentCount = monthlyHistory.countAttendanceStatus(AttendanceStatus.ABSENT);

        CrewStatus crewStatus = targetCrew.getStatus();

        return new AttendanceLogDto(monthlyHistory.getLogs(), attendCount, lateCount, absentCount,
                crewStatus.getDescription());
    }
}
