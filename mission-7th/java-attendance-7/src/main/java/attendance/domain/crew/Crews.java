package attendance.domain.crew;

import attendance.domain.attendance.Attendance;
import attendance.domain.attendance.AttendanceStatus;
import attendance.domain.campus.StudyTime;
import attendance.service.dto.CrewRequestDto;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Crews {
    private final List<Crew> crews;

    public Crews(List<Crew> crews) {
        this.crews = crews;
    }

    public static Crews from(List<CrewRequestDto> dtos) {
        Map<String, List<LocalDateTime>> groupedMap = new HashMap<>();

        for (CrewRequestDto dto : dtos) {
            groupedMap.computeIfAbsent(dto.nickname(), k -> new ArrayList<>())
                    .add(dto.time());
        }

        List<Crew> crewList = new ArrayList<>();
        for (String name : groupedMap.keySet()) {
            final List<LocalDateTime> times = groupedMap.get(name);

            List<Attendance> log = new ArrayList<>();
            for (LocalDateTime time : times) {
                final StudyTime studyTime = StudyTime.findBy(time);
                final int different = studyTime.calculateDifferent(LocalTime.from(time));

                final Attendance attendance = new Attendance(AttendanceStatus.from(different), LocalDate.from(time),
                        LocalTime.from(time));
                log.add(attendance);
            }
            final Crew crew = new Crew(name, log);
            crewList.add(crew);
        }

        return new Crews(crewList);
    }

    public Crew findByName(String target) {
        return crews.stream()
                .filter(crew -> crew.findByName(target))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 등록되지 않은 닉네임입니다."));
    }

    public List<String> getAllCrewName() {
        return crews.stream()
                .map(crew -> crew.getNickName())
                .toList();
    }
}
