package attendance.service;

import attendance.domain.attendance.Attendance;
import attendance.domain.attendance.AttendanceStatus;
import attendance.domain.attendance.MonthlyAttendacneLog;
import attendance.domain.campus.CrewStatus;
import attendance.domain.crew.Crew;
import attendance.domain.crew.Crews;
import attendance.service.dto.AttendanceLogDto;
import attendance.service.dto.AttendanceRegistResponseDto;
import attendance.service.dto.ChangeLogDto;
import attendance.service.dto.CrewConditionDto;
import camp.nextstep.edu.missionutils.DateTimes;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class AttendanceService {
    private final Crews crews;

    public AttendanceService(Crews crews) {
        this.crews = crews;
    }

    public AttendanceRegistResponseDto registAttendance(String targetName, LocalDateTime targetTime) {
        final Crew targetCrew = crews.findByName(targetName);

        final Attendance attendance = targetCrew.addAttendLog(targetTime);

        return AttendanceRegistResponseDto.from(attendance);
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

        final CrewStatus status = monthlyHistory.getStatus();

        return new AttendanceLogDto(monthlyHistory.getName(), monthlyHistory.getLogs(), attendCount, lateCount,
                absentCount,
                status.getDescription());
    }

    public List<CrewConditionDto> checkAllCrewStatus() {
        List<String> crewNames = crews.getAllCrewName();
        // 이름 리스트를 순회하면서 MonthlyAttendance를 생성
        final List<Crew> list = crewNames.stream()
                .map(name -> crews.findByName(name))
                .toList();

        final List<MonthlyAttendacneLog> list1 = list.stream()
                .map(crew -> crew.getMonthlyHistory(DateTimes.now()))
                .toList(); // 모든 크루들의 전날까지 월간 기록

        final List<CrewConditionDto> list2 = list1.stream()
                .map(monthlyLog -> {
                    final String name = monthlyLog.getName();
                    final int absentCount = monthlyLog.countAttendanceStatus(AttendanceStatus.ABSENT);
                    final int lateCount = monthlyLog.countAttendanceStatus(AttendanceStatus.LATE);
                    final CrewStatus status = monthlyLog.getStatus();

                    return new CrewConditionDto(name, absentCount, lateCount, status.getDescription());
                })
                .toList();

        return list2.stream()
                .filter(dto -> !dto.status().equals("정상"))
                .sorted(
                        Comparator.comparingInt(CrewConditionDto::getPenaltyScore).reversed()
                                .thenComparing(CrewConditionDto::name)
                )
                .toList();
    }

    public void checkCrewExist(String nickName) {
        final Crew byName = crews.findByName(nickName);
    }

    public void isCorrectDate(LocalDateTime now) {
        final DayOfWeek dow = now.getDayOfWeek();
        if (dow == DayOfWeek.SATURDAY || dow == DayOfWeek.SUNDAY) {
            final String format = now.format(DateTimeFormatter.ofPattern("MM월 dd일 EEEE", Locale.KOREA));
            throw new IllegalArgumentException(String.format("[ERROR] %s은 등교일이 아닙니다.", format));
        }
    }

    public void checkOperationTime(LocalDateTime time) {
        final int hour = time.getHour();
        if (hour < 8 || hour >= 23) {
            throw new IllegalArgumentException("[ERROR] 캠퍼스 운영 시간에만 출석이 가능합니다.");
        }
    }
}
