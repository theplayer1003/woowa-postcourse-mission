package christmas.domain.event.policy;

import christmas.domain.event.Benefit;
import christmas.domain.event.BenefitType;
import christmas.domain.event.EventPolicy;
import christmas.domain.order.MenuType;
import christmas.domain.order.Order;

public class WeekdaysEventPolicy implements EventPolicy {

    private final BenefitType benefitType = BenefitType.DISCOUNT;
    private final String eventName = "평일 할인";
    private final int BENEFIT_AMOUNT = 2_023;

    @Override
    public boolean isSatisfiedBy(Order order) {
        return order.isWeekday();
    }

    @Override
    public Benefit apply(Order order) {
        return new Benefit(
                benefitType,
                eventName,
                order.countMenuType(MenuType.DESSERT) * BENEFIT_AMOUNT
        );
    }
}
