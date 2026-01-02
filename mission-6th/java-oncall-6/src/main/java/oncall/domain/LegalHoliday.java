package oncall.domain;

public enum LegalHoliday {
    NEWYEAR(1, 1, "신정"),
    MARCHFIRST(3, 1, "삼일절"),
    CHILDRENDAY(5, 5, "어린이날"),
    MEMORIALDAY(6, 6, "현충일"),
    LIBERATIONDAY(8, 15, "광복절"),
    NATIONALFOUNDATIONDAY(10, 3, "개천절"),
    HANGULDAY(10, 9, "한글날"),
    CHRISMAS(12, 25, "성탄절"),
    NONE(0, 0, "공휴일이 아님");

    private final int month;
    private final int day;
    private final String description;

    LegalHoliday(int month, int day, String description) {
        this.month = month;
        this.day = day;
        this.description = description;
    }

    public String getSuffix() {
        return "(휴일)";
    }
}
