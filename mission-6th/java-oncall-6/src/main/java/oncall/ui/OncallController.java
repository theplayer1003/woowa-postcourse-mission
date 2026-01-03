package oncall.ui;

import java.util.List;
import oncall.domain.Month;
import oncall.global.util.RetryHandler;
import oncall.service.MonthRequestDto;
import oncall.service.OncallService;
import oncall.service.WorkLogResponse;
import oncall.service.WorkOrderRequestDto;

public class OncallController {
    private final OncallService oncallService;
    private final InputView inputView;
    private final OutputView outputView;

    public OncallController(OncallService oncallService, InputView inputView, OutputView outputView) {
        this.oncallService = oncallService;
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        final Month month = RetryHandler.retryUntilSuccess(() -> {
            final String monthAndFirstDayOfWeek = inputView.getMonthAndFirstDayOfWeek();
            final String[] split = monthAndFirstDayOfWeek.split(",");
            return oncallService.saveMonth(new MonthRequestDto(split[0], split[1]));
        }, outputView::printError);

        final List<WorkLogResponse> workLog = RetryHandler.retryUntilSuccess(() -> {
            final String weekdayWorkers = inputView.getWeekdayWorkers();
            final String holidayWorkers = inputView.getHolidayWorkers();
            final int monthNumber = month.getMonth();

            final WorkOrderRequestDto workOrderRequestDto = new WorkOrderRequestDto(weekdayWorkers, holidayWorkers);

            return oncallService.getWorkLog(workOrderRequestDto, monthNumber);

        }, outputView::printError);

        outputView.printWorkLog(workLog);

    }
}
