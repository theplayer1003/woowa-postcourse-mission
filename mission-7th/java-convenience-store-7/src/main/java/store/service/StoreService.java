package store.service;

import camp.nextstep.edu.missionutils.DateTimes;
import store.domain.exception.StoreException;
import store.domain.product.Inventory;
import store.domain.product.StockResult;
import store.global.exception.BusinessException;
import store.global.util.Validator;
import store.service.dto.response.OrderCheckResult;

public class StoreService {
    private final Inventory inventory;

    public StoreService(Inventory inventory) {
        Validator.requireNonNull(inventory, "serviceinventory");

        this.inventory = inventory;
    }

    public OrderCheckResult checkPromotion(String name, int requestQty) {
        StockResult stock = inventory.getStockByName(name);

        if (stock.getTotalQuantity() < requestQty) {
            throw new BusinessException(StoreException.PRODUCT_NOT_ENOUGH);
        }

        return stock.checkPromotion(name, requestQty);
    }
}
