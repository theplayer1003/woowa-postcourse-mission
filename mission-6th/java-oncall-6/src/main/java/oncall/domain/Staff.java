package oncall.domain;

import java.util.Objects;
import oncall.global.util.Validator;

public class Staff {
    private final StaffName name;

    public Staff(StaffName name) {
        Validator.requireNonNull(name, "staff");
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Staff staff)) {
            return false;
        }
        return Objects.equals(name, staff.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    public StaffName getName() {
        return name;
    }
}
