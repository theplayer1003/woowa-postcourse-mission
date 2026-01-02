package christmas.domain.event;

public enum BenefitCategory {
    DEDUCTION("차감"),
    PRESENTATION("증정");

    private final String description;

    BenefitCategory(String description) {
        this.description = description;
    }

    public boolean isDeductible() {
        return this == DEDUCTION;
    }
}
