package subway.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class StationRepository {
    private final List<Station> stations = new ArrayList<>();

    public void save(Station station) {
        stations.add(station);
    }

    public boolean existByName(String targetName) {
        return stations.stream()
                .map(Station::getStationName)
                .anyMatch(name -> name.equals(targetName));
    }

    public Optional<Station> findByName(String targetName) {
        return stations.stream()
                .filter(station -> station.getStationName().equals(targetName))
                .findFirst();
    }

    public List<Station> findAll() {
        return Collections.unmodifiableList(stations);
    }

    public boolean deleteByName(String targetName) {
        return stations.removeIf(station -> Objects.equals(station.getStationName(), targetName));
    }
}
