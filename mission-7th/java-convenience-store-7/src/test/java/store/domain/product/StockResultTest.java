package store.domain.product;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import store.service.dto.response.OrderCheckResult;

class StockResultTest {

    public static final LocalDate NOW = LocalDate.of(2024, 1, 1);

    @Test
    void checkPromotion() {
        StockResult stockResult = createStockresult("콜라", 10, 10, 2, 1);

        final OrderCheckResult result = stockResult.checkPromotion("콜라", 2);

        assertThat(result.status()).isEqualTo(PromotionCheckStatus.BRING_MORE_AVAILABLE);
        assertThat(result.bringMoreQty()).isEqualTo(1);
    }

    private StockResult createStockresult(String name, int promoQty, int genQty, int buy, int get) {
        final Promotion promo = new Promotion("반짝할인", buy, get, LocalDate.of(2023, 1, 1), LocalDate.of(2025, 12, 31));
        final Product cola = new Product(name, 1_000);

        final Stock promostock = new Stock(cola, promoQty, promo);
        final Stock genstock = new Stock(cola, genQty, null);

        return new StockResult(promostock, genstock);
    }
}