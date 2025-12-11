package subway.service.dto.response;

import java.util.List;

public record LineInfoResponseDto(String lineName, List<String> stationNames) {
}
