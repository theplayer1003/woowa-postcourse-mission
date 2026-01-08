package attendance.service.dto;

public record CrewConditionDto(String name, int absentCount, int lateCount, String status) {
    public int getPenaltyScore() {
        return this.absentCount + (this.lateCount / 3);
    }
}
