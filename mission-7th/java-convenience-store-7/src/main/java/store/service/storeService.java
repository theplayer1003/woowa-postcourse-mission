package store.service;

import java.util.Map;
import store.domain.DomainParser;
import store.domain.product.Inventory;
import store.global.util.Validator;
import store.service.dto.request.OrderRequestDto;
import store.service.dto.response.PromotionCheckResponse;

public class storeService {
    private final Inventory inventory;

    public storeService(Inventory inventory) {
        Validator.requireNonNull(inventory, "serviceinventory");

        this.inventory = inventory;
    }

    public PromotionCheckResponse checkPromotion(OrderRequestDto dto) {
        final Map<String, Integer> ordermap = DomainParser.orderToMap(dto);


    }
}
