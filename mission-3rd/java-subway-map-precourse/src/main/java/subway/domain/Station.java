package subway.domain;

import java.util.Objects;
import subway.exception.SubWayErrorCode;
import subway.global.exception.BusinessException;

public class Station {
    public static final int MAXIMUM_STATION_NAME_LENGTH = 2;

    private String stationName;

    public Station(String stationName) {
        validateNameLength(stationName);
        this.stationName = stationName;
    }

    private void validateNameLength(String stationName) {
        if (stationName.length() < MAXIMUM_STATION_NAME_LENGTH) {
            throw new BusinessException(SubWayErrorCode.STATION_NAME_LENGTH_INVALID, MAXIMUM_STATION_NAME_LENGTH,
                    stationName.length());
        }
    }

    public String getStationName() {
        return stationName;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Station station)) {
            return false;
        }
        return Objects.equals(stationName, station.stationName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(stationName);
    }
}
