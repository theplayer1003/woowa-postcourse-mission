package attendance.domain;

public class Attendence {
    private final Day day;
    private final Absent absent;
    private final Late late;

    public Attendence(Day day, Absent absent, Late late) {
        this.day = day;
        this.absent = absent;
        this.late = late;
    }

    public Day getDay() {
        return day;
    }

    public Absent getAbsent() {
        return absent;
    }

    public Late getLate() {
        return late;
    }
}
