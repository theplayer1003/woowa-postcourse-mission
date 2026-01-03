package oncall.domain;

public record WorkLogResponse(int month, int day, String dayOfWeek, boolean isLegalHoliday, String staffName) {
    public static WorkLogResponse from(WorkOrder workOrder) {
        return new WorkLogResponse(
                workOrder.getMonth(),
                workOrder.getDay(),
                workOrder.getDayOfWeekToString(),
                workOrder.isHoliday(),
                workOrder.getStaffName()
        );
    }
}
