package christmas.domain.event.policy;

import christmas.domain.event.Benefit;
import christmas.domain.event.BenefitType;
import christmas.domain.event.EventPolicy;
import christmas.domain.order.Order;
import christmas.domain.order.VisitDate;

public class DdayEventPolicy implements EventPolicy {
    private final int DEFAULT_BENEFIT_AMOUNT = 1_000;
    private final int DDAY_INCREASE_AMOUNT = 100;
    private final String EVENT_NAME = "크리스마스 디데이 할인";

    @Override
    public boolean isSatisfiedBy(Order target) {
        return target.calculateTotalAmount() >= 10_000 && target.isDdayRange();
    }

    @Override
    public Benefit apply(Order target) {
        return new Benefit(BenefitType.DISCOUNT, EVENT_NAME,
                calculateBenefitAmount(target));
    }

    private int calculateBenefitAmount(Order target) {
        final VisitDate targetVisitDate = target.getVisitDate();
        final int countUntilDday = targetVisitDate.calculateDaysSinceEventStart();

        return countUntilDday * DDAY_INCREASE_AMOUNT + DEFAULT_BENEFIT_AMOUNT;
    }
}
