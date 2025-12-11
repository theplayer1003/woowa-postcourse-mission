package subway.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class LineRepository {
    private static final List<Line> lines = new ArrayList<>();

    public void save(Line line) {
        lines.add(line);
    }

    public Optional<Line> findByName(String name) {
        return lines.stream()
                .filter(line -> line.getLineName().equals(name))
                .findFirst();
    }

    public List<Line> findAll() {
        return Collections.unmodifiableList(lines);
    }

    public boolean deleteByName(String name) {
        return lines.removeIf(line -> Objects.equals(line.getLineName(), name));
    }

    public boolean existByName(String targetName) {
        return lines.stream()
                .map(Line::getLineName)
                .anyMatch(name -> name.equals(targetName));
    }
}
