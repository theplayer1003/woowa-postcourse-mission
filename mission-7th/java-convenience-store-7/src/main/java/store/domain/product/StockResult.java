package store.domain.product;

import camp.nextstep.edu.missionutils.DateTimes;
import java.time.LocalDate;
import java.util.List;
import store.service.dto.response.OrderCheckResult;

public record StockResult(Stock promotion, Stock general) {

    public static StockResult from(List<Stock> stocks) {
        Stock promotion = null;
        Stock general = null;
        for (Stock stock : stocks) {
            if (stock.hasPromotion()) {
                promotion = stock;
            }
            if (!stock.hasPromotion()) {
                general = stock;
            }
        }

        return new StockResult(promotion, general);
    }

    public OrderCheckResult checkPromotion(String itemName, int requestQty) {
        if (!isValidPromotion()) { // 현재 프로모션을 이용할 수 있는 상태인지 체크
            return OrderCheckResult.pass(itemName);
        }

        final int promoStockQty = promotion.getQuantity(); // 프로모션에 쓸 수 있는 물건 재고
        final int buy = promotion.getPromotion().getBuy(); // 프로모 조건
        final int get = promotion.getPromotion().getGet(); // 프로모 혜택 수
        int setSize = buy + get; // 행사에 필요한 전체 개수. 2+1 = 3, 1+1 = 2

        if (requestQty > promoStockQty) { // 주문 수량이 프로모션 재고보다 많다면 몇 개까지 가능한지 계산해야함
            return calculateShortage(itemName, requestQty, promoStockQty, setSize);
        }

        return calculateBringMore(itemName, requestQty, buy, get, setSize);
    }

    private OrderCheckResult calculateBringMore(String itemName, int requestQty, int buy, int get, int setSize) {
        if (requestQty % setSize == buy) { // 주문하려는 수량 % 세트사이즈, 3%3 == 0, 4%3 == 1, 5%3 == 2, 6%3 == 0
            if (getTotalQuantity() >= requestQty + get) { // 주문하려는 수량 + 증정하려고하는 수량 <= 전체 재고
                return OrderCheckResult.bringMore(itemName, get); // 재고가 충분하다면 더 가지고 오겠냐는 안내 출력
            }
        }

        return OrderCheckResult.pass(itemName); // 그렇지 않으면 패스
    }

    private OrderCheckResult calculateShortage(String itemName, int requestQty, int promoStockQty, int setSize) {
        final int maxSets = promoStockQty / setSize; // 현재 만들 수 있는 최대 세트 수

        final int promoApplicableQty = maxSets * setSize; // 프로모션 적용 가능한 수량

        final int nonPromoQty = requestQty - promoApplicableQty; // 원하는 수량 - 프로모 적용 가능 수량 = 정가로 결제해야 하는 수량

        if (nonPromoQty > 0) { // 주문 중에 프로모 외에 일반 주문해야할 수량이 존재한다면
            return OrderCheckResult.shortage(itemName, nonPromoQty);
        }
        return  OrderCheckResult.pass(itemName); // 프로모션 재고 부족 분이 없다면 계산 진행
    }

    private boolean isValidPromotion() {
        if (promotion == null || !promotion.hasPromotion()) {
            return false;
        }

        return promotion.isPromotionActive(DateTimes.now().toLocalDate());
    }

    public int getTotalQuantity() {
        return getQuantity(promotion) + getQuantity(general);
    }

    public boolean isPromotionActive(LocalDate now) {
        return promotion.isPromotionApplicable(now);
    }

    public int getPromotionStockQuantity() {
        return getQuantity(promotion);
    }

    public int getPromotionBuy() {
        return promotion.getPromotion().getBuy();
    }

    private int getQuantity(Stock target) {
        return target.getQuantity();
    }

    public boolean hasPromotion() {
        if (promotion == null) {
            return false;
        }

        return true;
    }

    public int getPromotionGet() {
        return promotion.getPromotion().getGet();
    }
}
