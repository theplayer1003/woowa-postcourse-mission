package christmas.domain;

import static christmas.domain.fixture.ChrismasOrderFixture.*;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import christmas.domain.fixture.ChrismasOrderFixture;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class OrderTest {

    @Test
    void Order_CreateSuccess() {
        final Order order = createOrder(
                25,
                steak(2),
                bbq(1),
                choco(3),
                wine(3)
        );

        assertThat(order).isNotNull();

        assertThat(order.countMenuType(MenuType.APPETIZER)).isEqualTo(0);
        assertThat(order.countMenuType(MenuType.MAIN)).isEqualTo(3);
        assertThat(order.countMenuType(MenuType.DESSERT)).isEqualTo(3);
        assertThat(order.countMenuType(MenuType.DRINK)).isEqualTo(3);
        assertThat(order.hasMenuType(MenuType.APPETIZER)).isFalse();
        assertThat(order.hasMenuType(MenuType.MAIN)).isTrue();

        assertThat(order.isWeekday()).isTrue();
    }

    @Test
    void isWeekday() {
        final Order weekdayOrder = createOrder(24, steak(20));
        final Order weekendOrder = createOrder(23, steak(20));

        assertThat(weekdayOrder.isWeekday()).isTrue();
        assertThat(weekendOrder.isWeekday()).isFalse();
    }

    @Test
    void hasMenuType() {
        final Order appetizerOrder = createOrder(1, soup(1));

        assertThat(appetizerOrder.hasMenuType(MenuType.APPETIZER)).isTrue();
        assertThat(appetizerOrder.hasMenuType(MenuType.MAIN)).isFalse();
    }

    @Test
    void countMenuType() {
        final Order order = createOrder(1, soup(10), tapas(5));

        assertThat(order.countMenuType(MenuType.APPETIZER)).isEqualTo(15);
        assertThat(order.countMenuType(MenuType.MAIN)).isEqualTo(0);
    }
}