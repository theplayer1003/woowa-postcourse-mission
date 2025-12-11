package subway.service;

import java.util.Optional;
import subway.domain.Station;
import subway.domain.StationRepository;
import subway.exception.SubWayErrorCode;
import subway.global.exception.BusinessException;
import subway.service.dto.response.StationNamesResponseDto;

public class StationService {
    private final StationRepository stationRepository;

    public StationService(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    public void createStationByName(String stationName) {
        if (stationRepository.existByName(stationName)) {
            throw new BusinessException(SubWayErrorCode.STATION_ALREADY_EXIST, stationName);
        }
        stationRepository.save(new Station(stationName));
    }

    public void deleteStationByName(String stationName) {
        stationRepository.deleteByName(stationName);
    }

    public Station getOrCreateStationByName(String stationName) {
        return stationRepository.findByName(stationName)
                .orElseGet(() -> {
                            final Station station = new Station(stationName);
                            stationRepository.save(station);
                            return station;
                        }
                );
    }

    public StationNamesResponseDto findAllStationNames() {
        return new StationNamesResponseDto(stationRepository.findAll()
                .stream()
                .map(Station::getStationName)
                .toList());
    }

    public boolean isExist(String targetName) {
        return stationRepository.existByName(targetName);
    }

    public Optional<Station> findStationByName(String targetName) {
        return stationRepository.findByName(targetName);
    }
}
