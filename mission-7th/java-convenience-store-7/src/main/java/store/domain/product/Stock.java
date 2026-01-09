package store.domain.product;

import camp.nextstep.edu.missionutils.DateTimes;
import java.time.LocalDate;
import store.global.util.Validator;

public class Stock {
    private final Product product;
    private final int quantity;
    private final Promotion promotion;

    public Stock(Product product, int quantity, Promotion promotion) {
        Validator.requireNonNull(product, "stockproduct");

        this.product = product;
        this.quantity = quantity;
        this.promotion = promotion;
    }

    public boolean hasPromotion() {
        if (promotion == null) {
            return false;
        }

        return true;
    }

    public boolean isPromotionApplicable(LocalDate now) {
        return promotion.isApplicable(now);
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public boolean isPromotionActive(LocalDate now) {
        return promotion.isApplicable(now);
    }
}
