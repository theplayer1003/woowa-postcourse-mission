package subway.service;

import subway.domain.Line;
import subway.domain.LineRepository;
import subway.domain.Station;
import subway.domain.exception.SubWayErrorCode;
import subway.global.exception.BusinessException;
import subway.service.dto.request.AddStationInLineDto;
import subway.service.dto.request.CreateLineDto;
import subway.service.dto.request.DeleteStationInLineDto;
import subway.service.dto.response.LineNamesResponseDto;
import subway.service.dto.response.LinesInfoResponseDto;
import subway.service.mapper.LineMapper;

public class LineService {
    private final LineRepository lineRepository;
    private final StationService stationService;

    public LineService(LineRepository lineRepository, StationService stationService) {
        this.lineRepository = lineRepository;
        this.stationService = stationService;
    }

    public void createLine(CreateLineDto createLineDto) {
        validateDuplicateLine(createLineDto);

        final Station upBoundTerminal = stationService.getOrCreateStationByName(createLineDto.upBoundTerminal());
        final Station downBoundTerminal = stationService.getOrCreateStationByName(
                createLineDto.downBoundTerminal());

        lineRepository.save(LineMapper.mapToEntity(createLineDto, upBoundTerminal, downBoundTerminal));
    }

    private void validateDuplicateLine(CreateLineDto createLineDto) {
        if (lineRepository.existByName(createLineDto.lineName())) {
            throw new BusinessException(SubWayErrorCode.LINE_ALREADY_EXIST, createLineDto.lineName());
        }
    }

    public void deleteLineByName(String lineName) {
        lineRepository.deleteByName(lineName);
    }

    public LineNamesResponseDto findAllLineNames() {
        return LineMapper.toLineNamesDto(lineRepository.findAll());
    }

    public void addStationInLine(AddStationInLineDto addStationInLineDto) {
        final Line targetLine = lineRepository.findByName(addStationInLineDto.lineName()).orElseThrow(
                () -> new BusinessException(SubWayErrorCode.LINE_IS_NOT_EXIST, addStationInLineDto.stationName()));
        final Station targetStation = stationService.findStationByName(addStationInLineDto.stationName()).orElseThrow(
                () -> new BusinessException(SubWayErrorCode.STATION_IS_NOT_EXIST, addStationInLineDto.stationName()));

        targetLine.addStation(targetStation, addStationInLineDto.index());
    }

    public void deleteStationInLine(DeleteStationInLineDto deleteStationInLineDto) {
        final Line targetLine = lineRepository.findByName(deleteStationInLineDto.lineName()).orElseThrow(
                () -> new BusinessException(SubWayErrorCode.LINE_IS_NOT_EXIST, deleteStationInLineDto.lineName()));
        targetLine.removeStationByName(deleteStationInLineDto.stationName());
    }

    public LinesInfoResponseDto getAllLinesInfo() {
        return LineMapper.toLinesInfoDto(lineRepository.findAll());
    }
}
