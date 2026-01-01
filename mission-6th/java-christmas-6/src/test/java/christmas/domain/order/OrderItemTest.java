package christmas.domain.order;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.global.exception.BusinessException;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class OrderItemTest {

    @ParameterizedTest
    @CsvFileSource(resources = "/menu.csv", numLinesToSkip = 1)
    void OrderItem_CreateSuccess1(@ConvertWith(MenuTypeConverter.class) MenuType type, String menuName, int price) {
        assertThatCode(() -> new OrderItem(
                new Menu(type, menuName, price),
                1))
                .doesNotThrowAnyException();
    }

    static class MenuTypeConverter extends SimpleArgumentConverter {

        @Override
        protected Object convert(Object source, Class<?> aClass) throws ArgumentConversionException {
            return MenuType.findByDescription((String) source);
        }
    }

    @Test
    void OrderItem_CreateFail_MenuIsNull() {
        assertThatThrownBy(() -> new OrderItem(null, 1))
                .isInstanceOf(BusinessException.class)
                .hasMessageContaining("null 일 수 없습니다.");
    }

    /**
     * 생성 성공은 isNotNull 및 실제 값이 잘 들어있는지 확인 하는 것 예외가 던져지지 않았음은 반쪽 짜리
     * <p>
     * 생성 성공이란 에러가 안나며 -> 값이 제대로 들어가있나? 를 확인해야하는데 값이 제대로 들어가져 있음을 확인하는 작업이 에러가 안 났는지 확인하는 작업을 포함함
     */
    @ParameterizedTest
    @MethodSource("provideOrderItems")
    void OrderItem_CreateSuccess2(Menu menu, int quantity) {
        assertThatCode(() -> new OrderItem(menu, quantity))
                .doesNotThrowAnyException();

        final OrderItem orderItem = new OrderItem(menu, quantity);

        assertThat(orderItem).isNotNull();

        assertThat(orderItem.getMenu()).isEqualTo(menu);
        assertThat(orderItem.getQuantity()).isEqualTo(quantity);

        assertThat(new OrderItem(menu, quantity))
                .extracting("menu", "quantity")
                .containsExactly(menu, quantity);
    }

    static Stream<Arguments> provideOrderItems() {
        final Menu appetizer = new Menu(MenuType.APPETIZER, "양송이수프", 6000);
        final Menu main = new Menu(MenuType.MAIN, "티본스테이크", 55000);

        return Stream.of(
                Arguments.of(appetizer, 1),
                Arguments.of(appetizer, 20),
                Arguments.of(main, 1),
                Arguments.of(main, 20)
        );
    }


    @ParameterizedTest
    @CsvSource(value = {
            "에피타이저,양송이수프,6000,1",
            "메인,티본스테이크,55000,20",
            "음료,제로콜라,3000,1"
    })
    void OrderItem_CreateSuccess3(@ConvertWith(MenuTypeConverter.class) MenuType type, String name, int price,
                                  int quantity) {
        final Menu menu = new Menu(type, name, price);

        assertThatCode(() -> new OrderItem(menu, quantity))
                .doesNotThrowAnyException();
    }

    /**
     * 테스트 하고자 하는 값에 집중하기. 많은 케이스를 테스트하기 위해 파라미터를 받는게 아니다. 핵심 대상만 테스트 할 수 있다면 나머지는 더미 값을 넣어도 좋다
     */
    @ParameterizedTest
    @CsvSource(value = {
            "에피타이저, 에피타이저, true",
            "에피타이저, 메인, false",
            "음료, 디저트, false"
    })
    void isMenuType(@ConvertWith(MenuTypeConverter.class) MenuType actualType,
                    @ConvertWith(MenuTypeConverter.class) MenuType targetType, boolean expected) {
        final Menu menu = new Menu(actualType, "테스트", 0);
        final OrderItem orderItem = new OrderItem(menu, 1);

        assertThat(orderItem.isMenuType(targetType)).isEqualTo(expected);

    }
}