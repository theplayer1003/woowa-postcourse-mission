package attendance.domain;

import java.util.List;
import java.util.Objects;

public class Crew {
    private final String nickName;
    private final List<Attendence> attendences;

    public Crew(String nickName, List<Attendence> attendences) {
        this.nickName = nickName;
        this.attendences = attendences;
    }

    public Indication getIndication() {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Crew crew)) {
            return false;
        }
        return Objects.equals(nickName, crew.nickName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nickName);
    }

    public String getNickName() {
        return nickName;
    }

    public List<Attendence> getAttendences() {
        return attendences;
    }
}
