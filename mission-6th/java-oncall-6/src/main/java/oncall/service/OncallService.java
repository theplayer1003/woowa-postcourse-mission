package oncall.service;

import java.util.ArrayList;
import java.util.List;
import oncall.domain.Month;
import oncall.domain.MonthFactory;
import oncall.domain.WorkOrder;
import oncall.domain.WorkOrderEditor;
import oncall.domain.WorkOrders;
import oncall.infra.MonthRepository;

public class OncallService {
    private final MonthRepository monthRepository;

    public OncallService(MonthRepository monthRepository) {
        this.monthRepository = monthRepository;
    }

    public Month saveMonth(MonthRequestDto monthRequestDto) {
        final String monthInput = monthRequestDto.month();
        final String dayOfWeek = monthRequestDto.dayOfWeek();

        final Month month = MonthFactory.of(monthInput, dayOfWeek);

        monthRepository.save(month);

        return month;
    }

    public List<WorkLogResponse> getWorkLog(WorkOrderRequestDto workOrderRequestDto, int monthNumber) {
        final String s = workOrderRequestDto.weekdayList();
        final String s1 = workOrderRequestDto.holidayList();

        final WorkOrderEditor workOrderEditor = new WorkOrderEditor(s, s1);

        final WorkOrders workOrders = workOrderEditor.calculateWorkOrder(
                monthRepository.findByMonthNumber(monthNumber));

        final List<WorkOrder> workOrders1 = workOrders.getWorkOrders();

        List<WorkLogResponse> result = new ArrayList<>();
        for (WorkOrder workOrder : workOrders1) {
            final WorkLogResponse from = WorkLogResponse.from(workOrder);
            result.add(from);
        }

        return result;
    }
}
