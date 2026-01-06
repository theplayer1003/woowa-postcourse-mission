package attendance.domain.crew;

import attendance.domain.attendance.Attendance;
import attendance.domain.attendance.AttendanceStatus;
import attendance.domain.attendance.DailyAttendanceLog;
import attendance.domain.attendance.MonthlyAttendacneLog;
import attendance.domain.campus.CrewStatus;
import attendance.domain.campus.StudyTime;
import attendance.service.AttendanceLogDto;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public Attendance addAttendLog(LocalDateTime targetTime) {
        if (isAlreadyExist(targetTime)) {
            throw new IllegalArgumentException("이미 출석을 확인하였습니다. 필요한 경우 수정 기능을 이용해주세요.");
        }

        final StudyTime rule = StudyTime.findBy(targetTime);
        final int timeDiffrence = rule.calculateDifferent(LocalTime.from(targetTime));

        final AttendanceStatus targetStatus = AttendanceStatus.from(timeDiffrence);
        final Attendance attendance = new Attendance(targetStatus, LocalDate.from(targetTime),
                LocalTime.from(targetTime));

        attendLog.add(attendance);

        return attendance;
    }

    private boolean isAlreadyExist(LocalDateTime target) {
        return attendLog.stream()
                .anyMatch(attendance -> attendance.isSameDate(LocalDate.from(target)));
    }

    public Attendance findAndCopyAttendanceBy(LocalDateTime targetTime) {
        return findAttendanceByDate(targetTime);
    }

    public Attendance changeLog(LocalDateTime targetTime) {
        final Attendance targetAttendacne = findAttendanceByDate(targetTime);

        final boolean remove = attendLog.remove(targetAttendacne);

        return this.addAttendLog(targetTime);
    }

    private Attendance findAttendanceByDate(LocalDateTime targetTime) {
        return attendLog.stream()
                .filter(attendance -> attendance.isSameDate(LocalDate.from(targetTime)))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 형식을 입력하였습니다."));
    }

    public MonthlyAttendacneLog getMonthlyHistory(LocalDateTime now) {
        final Map<LocalDate, Attendance> logMap = attendLog.stream()
                .collect(Collectors.toMap(Attendance::getDate, a -> a));

        List<DailyAttendanceLog> history = new ArrayList<>();
        final LocalDate nowDate = LocalDate.from(now);
        final LocalDate firstDayOfMonth = nowDate.withDayOfMonth(1);

        final List<DailyAttendanceLog> collect = firstDayOfMonth.datesUntil(nowDate)
                .filter(date -> StudyTime.isSchoolDay(date))
                .map(date -> mapToDailyLog(date, logMap))
                .collect(Collectors.toList());

        return new MonthlyAttendacneLog(collect);
    }

    private DailyAttendanceLog mapToDailyLog(LocalDate date, Map<LocalDate, Attendance> logMap) {
        if (logMap.containsKey(date)) {
            final Attendance attendance = logMap.get(date);
            return new DailyAttendanceLog(
                    date,
                    attendance.getTime(),
                    attendance.getStatus()
            );
        }

        return new DailyAttendanceLog(
                date,
                null,
                AttendanceStatus.ABSENT
        );
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

    public String getNickName() {
        return nickName;
    }

    public List<Attendance> getAttendLog() {
        return attendLog;
    }
}
