package subway.service.mapper;

import java.util.List;
import subway.domain.Line;
import subway.domain.Station;
import subway.service.dto.request.CreateLineDto;
import subway.service.dto.response.LineInfoResponseDto;
import subway.service.dto.response.LineNamesResponseDto;
import subway.service.dto.response.LinesInfoResponseDto;

public class LineMapper {

    public static Line mapToEntity(CreateLineDto dto, Station upBoundTerminal, Station downBoundTerminal) {
        return new Line(
                dto.lineName(),
                upBoundTerminal,
                downBoundTerminal
        );
    }

    public static LineNamesResponseDto toLineNamesDto(List<Line> lines) {
        return new LineNamesResponseDto(lines.stream()
                .map(Line::getLineName)
                .toList());
    }

    public static LinesInfoResponseDto toLinesInfoDto(List<Line> lines) {
        final List<LineInfoResponseDto> lineInfoResponseDtos = lines.stream()
                .map(LineMapper::toLineInfoDto)
                .toList();

        return new LinesInfoResponseDto(lineInfoResponseDtos);
    }

    private static LineInfoResponseDto toLineInfoDto(Line line) {
        final List<String> stationNames = line.getStations().stream()
                .map(Station::getStationName)
                .toList();

        return new LineInfoResponseDto(line.getLineName(), stationNames);
    }
}
