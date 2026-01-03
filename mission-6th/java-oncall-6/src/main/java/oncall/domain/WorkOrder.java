package oncall.domain;

import java.time.DayOfWeek;
import oncall.domain.exception.OncallErrorCode;
import oncall.global.exception.BusinessException;

public class WorkOrder {
    private final int month;
    private final int day;
    private final DayOfWeek dayOfWeek;
    private final boolean isHoliday;
    private final String staffName;

    public WorkOrder(int month, int day, DayOfWeek dayOfWeek, boolean isHoliday, String staffName) {
        this.month = month;
        this.day = day;
        this.dayOfWeek = dayOfWeek;
        this.isHoliday = isHoliday;
        this.staffName = staffName;
    }

    public String getDayOfWeekToString() {
        if (DayOfWeek.MONDAY == dayOfWeek) {
            return "월";
        }
        if (DayOfWeek.TUESDAY == dayOfWeek) {
            return "화";
        }
        if (DayOfWeek.WEDNESDAY == dayOfWeek) {
            return "수";
        }
        if (DayOfWeek.THURSDAY == dayOfWeek) {
            return "목";
        }
        if (DayOfWeek.FRIDAY == dayOfWeek) {
            return "금";
        }
        if (DayOfWeek.SATURDAY == dayOfWeek) {
            return "토";
        }
        if (DayOfWeek.SUNDAY == dayOfWeek) {
            return "일";
        }

        throw new BusinessException(OncallErrorCode.WORKORDER_DAYOFWEEK_NULL, dayOfWeek);
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public boolean isHoliday() {
        return isHoliday;
    }

    public String getStaffName() {
        return staffName;
    }
}
