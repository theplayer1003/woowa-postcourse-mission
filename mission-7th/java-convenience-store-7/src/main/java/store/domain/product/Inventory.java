package store.domain.product;

import java.util.List;
import java.util.Map;
import store.global.util.Validator;

public class Inventory {
    private final Map<String, List<Stock>> items;

    public Inventory(Map<String, List<Stock>> items) {
        Validator.requireNonNull(items, "inventoryitems");

        this.items = items;
    }

    public List<Stock> findbyName(String target) {
        final List<Stock> stocks = items.get(target);

        return stocks;
    }
}
