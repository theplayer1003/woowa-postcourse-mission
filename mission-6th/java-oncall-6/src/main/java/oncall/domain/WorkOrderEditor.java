package oncall.domain;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import oncall.global.util.Validator;

public class WorkOrderEditor {
    private final Deque<Staff> weekDayStaff;
    private final Deque<Staff> holidayStaff;

    public WorkOrderEditor(String weekdayWorkers, String holidayWorkers) {
        Validator.requireNonNullOrBlank(weekdayWorkers, "weekdayWorkers");
        Validator.requireNonNullOrBlank(holidayWorkers, "holidayWorkers");

        List<Staff> weekdayList = getStaffList(weekdayWorkers);
        List<Staff> holidayList = getStaffList(holidayWorkers);

        final Deque<Staff> weekDayStaff = new ArrayDeque<>(weekdayList);
        final Deque<Staff> holidayStaff = new ArrayDeque<>(holidayList);

        this.weekDayStaff = weekDayStaff;
        this.holidayStaff = holidayStaff;
    }

    private List<Staff> getStaffList(String weekdayWorker) {
        final String[] split = weekdayWorker.split(",");

        return Arrays.stream(split)
                .map(StaffName::new)
                .map(Staff::new)
                .toList();
    }

    public WorkOrders calculateWorkOrder(Month month) {
        final int monthMaxDay = month.getMonthMaxDay();
        List<WorkOrder> workOrders = new ArrayList<>();
        Staff previous = new Staff(new StaffName("First"));

        for (int i = 1; i <= monthMaxDay; i++) {
            final Day day = month.getDayByDayNumber(i);

            if (day.isWeekDay()) {
                final Staff staff = weekDayStaff.pollFirst();

                if (previous.equals(staff)) {
                    final Staff replace = weekDayStaff.pollFirst();
                    final WorkOrder workOrder = new WorkOrder(month.getMonth(), day.getDayNumber(), day.getDayOfWeek(),
                            day.isLegalHoliday(), replace.getName().name());
                    workOrders.add(workOrder);
                    weekDayStaff.offerFirst(staff);
                    weekDayStaff.offerLast(replace);
                    previous = replace;

                    continue;
                }

                final WorkOrder workOrder = new WorkOrder(month.getMonth(), day.getDayNumber(), day.getDayOfWeek(),
                        day.isLegalHoliday(), staff.getName().name());
                workOrders.add(workOrder);
                weekDayStaff.offerLast(staff);
                previous = staff;
            }

            if (day.isWeekendOrHoliday()) {
                final Staff staff = holidayStaff.pollFirst();

                if (previous.equals(staff)) {
                    final Staff replace = holidayStaff.pollFirst();
                    final WorkOrder workOrder = new WorkOrder(month.getMonth(), day.getDayNumber(), day.getDayOfWeek(),
                            day.isLegalHoliday(), replace.getName().name());
                    workOrders.add(workOrder);
                    holidayStaff.offerFirst(staff);
                    holidayStaff.offerLast(replace);
                    previous = replace;

                    continue;
                }

                final WorkOrder workOrder = new WorkOrder(month.getMonth(), day.getDayNumber(), day.getDayOfWeek(),
                        day.isLegalHoliday(), staff.getName().name());
                workOrders.add(workOrder);
                holidayStaff.offerLast(staff);
                previous = staff;
            }
        }

        return new WorkOrders(workOrders);
    }
}
