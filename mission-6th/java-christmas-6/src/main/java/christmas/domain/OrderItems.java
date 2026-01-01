package christmas.domain;

import christmas.global.util.Validator;
import java.util.List;

public class OrderItems {
    private final List<OrderItem> orderItems;

    public OrderItems(List<OrderItem> orderItems) {
        Validator.requireNonNulls(orderItems, "orderItems");

        this.orderItems = List.copyOf(orderItems);
    }

    public boolean hasMenuType(MenuType target) {
        return orderItems.stream()
                .anyMatch(orderItem -> orderItem.isMenuType(target));
    }

    public int countByMenuType(MenuType target) {
        return orderItems.stream()
                .filter(orderItem -> orderItem.isMenuType(target))
                .mapToInt(OrderItem::getQuantity)
                .sum();
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }
}
