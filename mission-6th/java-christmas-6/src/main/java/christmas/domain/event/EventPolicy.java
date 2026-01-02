package christmas.domain.event;

import christmas.domain.order.Order;

public interface EventPolicy {
    boolean isSatisfiedBy(Order order);

    Benefit apply(Order order);
}
