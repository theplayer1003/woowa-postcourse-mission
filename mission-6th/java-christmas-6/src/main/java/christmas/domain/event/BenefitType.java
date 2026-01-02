package christmas.domain.event;

public enum BenefitType {
    DISCOUNT("할인", BenefitCategory.DEDUCTION),
    GIFT("증정", BenefitCategory.PRESENTATION);

    private final String eventType;
    private final BenefitCategory benefitCategory;

    BenefitType(String eventType, BenefitCategory benefitCategory) {
        this.eventType = eventType;
        this.benefitCategory = benefitCategory;
    }

    public boolean isDiscount() {
        return benefitCategory.isDeductible();
    }
}
