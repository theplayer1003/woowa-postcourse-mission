package store.domain.product;

import store.global.util.Validator;

public class Product {
    private final String name;
    private final int price;

    public Product(String name, int price) {
        Validator.requireNonNullOrBlank(name, "productname");

        this.name = name;
        this.price = price;
    }
}
