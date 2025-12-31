package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


import christmas.global.exception.BusinessException;
import christmas.global.exception.GlobalErrorCode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class MenuTest {

    @Test
    void Menu_CreateSuccess() {
        assertThatCode(() -> new Menu(MenuType.APPETIZER, "양송이수프", 6000))
                .doesNotThrowAnyException();
    }

    @Test
    void Menu_CreateFail_MenuTypeNull() {
        assertThatThrownBy(() -> new Menu(null, "양송이수프", 6000))
                .isInstanceOf(BusinessException.class)
                .extracting("errorCode")
                .isEqualTo(GlobalErrorCode.PARAMETER_REQUIRED_NOT_NULL);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "\t", "\n"})
    void Menu_CreateFail_MenuNameNullorBlank(String invalidName) {
        assertThatThrownBy(() -> new Menu(MenuType.APPETIZER, invalidName, 6000))
                .isInstanceOf(BusinessException.class)
                .hasMessageContaining("null 이거나 비어 있을 수 없습니다.");
    }

    @Test
    void Menu_CreateFail_MenuPriceNegative() {
        assertThatThrownBy(() -> new Menu(MenuType.APPETIZER, "양송이수프", -6000))
                .isInstanceOf(BusinessException.class)
                .hasMessage("메뉴 가격은 음수 일 수 없습니다. -6,000");
    }

    @Test
    void Menu_isType() {
        final Menu menu = new Menu(MenuType.MAIN, "티본스테이크", 55000);

        final boolean expectedFalse = menu.isType(MenuType.APPETIZER);
        final boolean expectedTrue = menu.isType(MenuType.MAIN);

        assertThat(expectedFalse).isFalse();
        assertThat(expectedTrue).isTrue();
    }
}