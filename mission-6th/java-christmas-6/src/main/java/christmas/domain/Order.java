package christmas.domain;

import christmas.global.util.Validator;

public class Order {
    private final OrderItems orderItems;
    private final VisitDate visitDate;

    public Order(OrderItems orderItems, VisitDate visitDate) {
        Validator.requireNonNull(orderItems, "orderitems");
        Validator.requireNonNull(visitDate, "visitdate");

        this.orderItems = orderItems;
        this.visitDate = visitDate;
    }

    public boolean isWeekday() {
        return visitDate.isWeekdaysPeriod();
    }

    public boolean hasMenuType(MenuType target) {
        return orderItems.hasMenuType(target);
    }

    public int countMenuType(MenuType target) {
        return orderItems.countByMenuType(target);
    }

    public OrderItems getOrderItems() {
        return orderItems;
    }

    public VisitDate getVisitDate() {
        return visitDate;
    }
}
