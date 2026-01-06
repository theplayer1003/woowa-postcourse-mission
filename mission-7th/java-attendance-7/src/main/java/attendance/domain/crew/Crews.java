package attendance.domain.crew;

import java.util.List;

public class Crews {
    private final List<Crew> crews;

    public Crews(List<Crew> crews) {
        this.crews = crews;
    }

    public Crew findByName(String target) {
        return crews.stream()
                .filter(crew -> crew.findByName(target))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("등록되지 않은 닉네임입니다."));
    }
}
