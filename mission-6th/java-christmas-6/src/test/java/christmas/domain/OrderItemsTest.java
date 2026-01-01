package christmas.domain;

import static christmas.domain.fixture.ChrismasOrderFixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.global.exception.BusinessException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class OrderItemsTest {

    @Test
    void OrderItems_CreateSuccess() {
        final OrderItems orderItems = createOrderItems(soup(1), steak(2), icecream(2), wine(2));

        assertThat(orderItems).isNotNull();

        assertThat(orderItems.getOrderItems())
                .containsExactly(soup(1), steak(2), icecream(2), wine(2));
    }

    @Test
    void OrderItems_CreateFailNull() {
        assertThatThrownBy(() -> new OrderItems(null))
                .isInstanceOf(BusinessException.class)
                .hasMessageContaining("null 일 수 없습니다.");

        List<OrderItem> withNull = new ArrayList<>();
        withNull.add(soup(1));
        withNull.add(null);
        withNull.add(steak(1));

        assertThatThrownBy(() -> new OrderItems(withNull))
                .isInstanceOf(BusinessException.class)
                .hasMessageContaining("null 일 수 없습니다.");
    }

    @Test
    void hasMenuType(){
        final OrderItems orderItems = createOrderItems(soup(3), steak(1), coke(6));

        assertThat(orderItems.hasMenuType(MenuType.APPETIZER)).isTrue();
        assertThat(orderItems.hasMenuType(MenuType.MAIN)).isTrue();
        assertThat(orderItems.hasMenuType(MenuType.DRINK)).isTrue();
        assertThat(orderItems.hasMenuType(MenuType.DESSERT)).isFalse();
    }

    @Test
    void countByMenuType(){
        final OrderItems orderItems = createOrderItems(soup(2), tapas(5));

        assertThat(orderItems.countByMenuType(MenuType.APPETIZER)).isEqualTo(7);
        assertThat(orderItems.countByMenuType(MenuType.DESSERT)).isEqualTo(0);
    }
}