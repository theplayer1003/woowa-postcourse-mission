package attendance.service;

import java.time.LocalDateTime;

public record CrewRequestDto(String nickname, LocalDateTime time) {
}
