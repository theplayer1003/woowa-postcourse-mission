package store.service.dto.response;

import store.domain.product.PromotionCheckStatus;

public record OrderCheckResult (String itemName, int bringMoreQty, int nonPromotionQty, PromotionCheckStatus status){
    public static OrderCheckResult pass(String itemName) {
        return new OrderCheckResult(itemName, 0, 0, PromotionCheckStatus.PROMOTION_APPLIED);
    }

    public static OrderCheckResult shortage(String name, int nonPromoQty) {
        return new OrderCheckResult(name, 0, nonPromoQty, PromotionCheckStatus.PROMOTION_STOCK_SHORTAGE);
    }

    public static OrderCheckResult bringMore(String name, int get) {
        return new OrderCheckResult(name, get, 0, PromotionCheckStatus.BRING_MORE_AVAILABLE);
    }

    public boolean isBringMore() {
        return status == PromotionCheckStatus.BRING_MORE_AVAILABLE;
    }

    public boolean isShortage() {
        return status == PromotionCheckStatus.PROMOTION_STOCK_SHORTAGE;
    }
}
