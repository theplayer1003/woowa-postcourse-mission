package christmas.domain.event;

import christmas.domain.exception.ChristmasErrorCode;
import christmas.global.exception.BusinessException;
import christmas.global.util.Validator;
import java.util.Objects;

public class Benefit {
    private final BenefitType benefitType;
    private final String eventName;
    private final int amount;

    public Benefit(BenefitType benefitType, String eventName, int amount) {
        Validator.requireNonNull(benefitType, "benefitType");
        Validator.requireNonNullOrBlank(eventName, "eventName");
        validateNotNegative(amount);

        this.benefitType = benefitType;
        this.eventName = eventName;
        this.amount = amount;
    }

    private void validateNotNegative(int amount) {
        if (amount < 0) {
            throw new BusinessException(ChristmasErrorCode.BENEFITAMOUNT_CANNOT_NEGATIVE, amount);
        }
    }

    public int getDiscountAmount() {
        if (benefitType.isDiscount()) {
            return amount;
        }

        return 0;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Benefit benefit)) {
            return false;
        }
        return amount == benefit.amount && benefitType == benefit.benefitType && Objects.equals(eventName,
                benefit.eventName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(benefitType, eventName, amount);
    }

    public BenefitType getBenefitType() {
        return benefitType;
    }

    public String getEventName() {
        return eventName;
    }
}
