package christmas.domain.event.policy;

import static christmas.domain.fixture.ChrismasOrderFixture.*;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import christmas.domain.event.Benefit;
import christmas.domain.fixture.ChrismasOrderFixture;
import christmas.domain.order.Order;
import org.junit.jupiter.api.Test;

class DdayEventPolicyTest {

    @Test
    void isSatisfiedBy(){
        final DdayEventPolicy ddayEventPolicy = new DdayEventPolicy();
        final Order order1 = createOrder(1, soup(1), steak(10));
        final Order order25 = createOrder(25, soup(1), steak(10));
        final Order order25lessTotalAmount = createOrder(25, soup(1));
        final Order order26 = createOrder(26, soup(1), steak(10));


        assertThat(ddayEventPolicy.isSatisfiedBy(order1)).isTrue();
        assertThat(ddayEventPolicy.isSatisfiedBy(order25)).isTrue();
        assertThat(ddayEventPolicy.isSatisfiedBy(order25lessTotalAmount)).isFalse();
        assertThat(ddayEventPolicy.isSatisfiedBy(order26)).isFalse();
    }

    @Test
    void apply() {
        final DdayEventPolicy ddayEventPolicy = new DdayEventPolicy();
        final Order order1 = createOrder(
                1,
                soup(1),
                steak(2),
                wine(2)
        );
        final Order order25 = createOrder(
                25,
                soup(1),
                steak(2),
                wine(2)
        );

        final Benefit benefitResult1 = ddayEventPolicy.apply(order1);
        final Benefit benefitResult25 = ddayEventPolicy.apply(order25);

        assertThat(benefitResult1.getAmount()).isEqualTo(1_000);
        assertThat(benefitResult25.getAmount()).isEqualTo(3_400);

    }
}