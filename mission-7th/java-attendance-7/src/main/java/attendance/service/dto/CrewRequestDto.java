package attendance.service.dto;

import java.time.LocalDateTime;

public record CrewRequestDto(String nickname, LocalDateTime time) {
}
