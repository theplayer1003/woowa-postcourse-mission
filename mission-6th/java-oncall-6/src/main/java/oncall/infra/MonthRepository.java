package oncall.infra;

import java.util.HashMap;
import java.util.Map;
import oncall.domain.Month;

public class MonthRepository {
    private static final Map<Integer, Month> monthRepository = new HashMap<>();

    public void save(Month target) {
        monthRepository.put(target.getMonth(), target);
    }

    public Month findByMonthNumber(int monthNumber) {
        if (!monthRepository.containsKey(monthNumber)) {

        }

        return monthRepository.get(monthNumber);
    }
}
