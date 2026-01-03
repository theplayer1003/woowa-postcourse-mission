package oncall.service;

import java.util.ArrayList;
import java.util.List;
import oncall.domain.Month;
import oncall.domain.MonthFactory;
import oncall.domain.WorkOrder;
import oncall.domain.WorkOrderEditor;
import oncall.domain.WorkOrders;

public class OncallService {
    private Month month;
    private WorkOrderEditor workOrderEditor;

    public void getMonth(MonthRequestDto monthRequestDto) {
        final String monthInput = monthRequestDto.month();
        final String dayOfWeek = monthRequestDto.dayOfWeek();

        month = MonthFactory.of(monthInput, dayOfWeek);
    }

    public List<WorkLogResponse> getWorkLog(WorkOrderRequestDto workOrderRequestDto) {
        final String s = workOrderRequestDto.weekdayList();
        final String s1 = workOrderRequestDto.holidayList();

        workOrderEditor = new WorkOrderEditor(s, s1);

        final WorkOrders workOrders = workOrderEditor.calculateWorkOrder(month);

        final List<WorkOrder> workOrders1 = workOrders.getWorkOrders();

        List<WorkLogResponse> result = new ArrayList<>();
        for (WorkOrder workOrder : workOrders1) {
            final WorkLogResponse from = WorkLogResponse.from(workOrder);
            result.add(from);
        }

        return result;
    }
}
