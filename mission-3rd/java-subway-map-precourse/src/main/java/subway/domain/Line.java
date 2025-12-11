package subway.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import subway.exception.SubWayErrorCode;
import subway.global.exception.BusinessException;

public class Line {
    private static final int MAXIMUM_LINE_NAME_LENGTH = 2;

    private String lineName;
    private List<Station> stations = new ArrayList<>();

    public Line(String lineName, Station upBoundTerminal, Station downBoundTerminal) {
        validateNameLength(lineName);
        this.lineName = lineName;

        initFinalStation(upBoundTerminal, downBoundTerminal);
    }

    private void validateNameLength(String lineName) {
        if (lineName.length() < MAXIMUM_LINE_NAME_LENGTH) {
            throw new BusinessException(SubWayErrorCode.LINE_NAME_LENGTH_INVALID, MAXIMUM_LINE_NAME_LENGTH,
                    lineName.length());
        }
    }

    private void initFinalStation(Station upBoundTerminal, Station downBoundTerminal) {
        stations.add(downBoundTerminal);
        stations.add(upBoundTerminal);
    }

    public void addStation(Station station) {
        stations.add(1, station);
    }

    public void addStation(Station station, int index) {
        if (index <= 0 && index >= stations.size() - 1) {
            throw new BusinessException(SubWayErrorCode.LINE_ADD_INVALID_INDEX, stations.size(), index);
        }
        stations.add(index, station);
    }

    public void removeStation(Station targetStation) {
        if (!stations.removeIf(station -> station.equals(targetStation))) {
            throw new BusinessException(SubWayErrorCode.LINE_REMOVE_NOTFOUND, lineName, targetStation.getStationName());
        }
    }

    public void removeStationByName(String targetName) {
        if (stations.size() <= 2) {
            throw new BusinessException(SubWayErrorCode.LINE_CANNOT_DELETE_MIN_SIZE);
        }

        if (!stations.removeIf(station -> station.getStationName().equals(targetName))) {
            throw new BusinessException(SubWayErrorCode.LINE_REMOVE_NOTFOUND, lineName, targetName);
        }
    }

    public String getLineName() {
        return lineName;
    }

    public List<Station> getStations() {
        return stations;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Line line)) {
            return false;
        }
        return Objects.equals(lineName, line.lineName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(lineName);
    }
}
