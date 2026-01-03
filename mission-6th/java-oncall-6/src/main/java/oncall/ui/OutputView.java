package oncall.ui;

import java.util.List;
import oncall.service.WorkLogResponse;

public class OutputView {
    private static final String ERROR_PREFIX = "[ERROR] ";

    public void printError(String message) {
        System.out.println(ERROR_PREFIX + message);
    }

    public void printWorkLog(List<WorkLogResponse> workLogResponses) {
        for (WorkLogResponse response : workLogResponses) {
            String holidayMark = getHolidayMark(response.isLegalHoliday());

            System.out.printf("%d월 %d일 %s%s %s%n",
                    response.month(),
                    response.day(),
                    response.dayOfWeek(),
                    holidayMark,
                    response.staffName()
            );
        }
    }

    private String getHolidayMark(boolean legalHoliday) {
        if (legalHoliday) {
            return "(휴일)";
        }
        return "";
    }
}
