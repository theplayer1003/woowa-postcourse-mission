package subway.service.dto.request;

public record CreateLineDto(String lineName, String upBoundTerminal, String downBoundTerminal) {
}
