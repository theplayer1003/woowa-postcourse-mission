package store.domain.product;

import camp.nextstep.edu.missionutils.DateTimes;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import store.domain.exception.StoreException;
import store.global.exception.BusinessException;
import store.global.util.Validator;

public class Inventory {
    private final Map<String, List<Stock>> items;

    public Inventory(Map<String, List<Stock>> items) {
        Validator.requireNonNull(items, "inventoryitems");

        this.items = items;
    }

    public boolean isExist(String key) {
        if (!items.containsKey(key)) {
            throw new BusinessException(StoreException.PRODUCT_NOT_EXIST);
        }

        return true;
    }

    public StockResult getStockByName(String key) {
        this.isExist(key);
        final List<Stock> stocks = items.get(key);

        return StockResult.from(stocks);
    }
}
