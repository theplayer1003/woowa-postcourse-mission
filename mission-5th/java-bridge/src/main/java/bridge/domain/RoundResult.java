package bridge.domain;

public class RoundResult {
    private final int position;
    private final String mark;

    public RoundResult(int position, String mark) {
        this.position = position;
        this.mark = mark;
    }

    public int getPosition() {
        return position;
    }

    public String getMark() {
        return mark;
    }
}
