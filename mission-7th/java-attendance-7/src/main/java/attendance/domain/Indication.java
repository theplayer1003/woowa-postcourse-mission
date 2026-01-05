package attendance.domain;

public enum Indication {
    WARN("경고 대상자"),
    CONSULT("면담 대상자"),
    EXPULSION("제적 대상자"),
    NONE("이상 없음");

    private final String description;

    Indication(String description) {
        this.description = description;
    }
}
