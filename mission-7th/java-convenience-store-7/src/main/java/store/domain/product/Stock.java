package store.domain.product;

import store.global.util.Validator;

public class Stock {
    private final Product product;
    private final int quantity;
    private final Promotion promotion;

    public Stock(Product product, int quantity, Promotion promotion) {
        Validator.requireNonNull(product, "stockproduct");
        Validator.requireNonNull(promotion, "stockpromotion");

        this.product = product;
        this.quantity = quantity;
        this.promotion = promotion;
    }
}
