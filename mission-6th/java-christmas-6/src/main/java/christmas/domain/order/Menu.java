package christmas.domain.order;

import christmas.domain.exception.ChristmasErrorCode;
import christmas.global.exception.BusinessException;
import christmas.global.util.Validator;
import java.util.Objects;

public class Menu {
    private final MenuType menuType;
    private final String menuName;
    private final int menuPrice;

    public Menu(MenuType menuType, String menuName, int menuPrice) {
        Validator.requireNonNull(menuType, "menuType");
        Validator.requireNonNullOrBlank(menuName, "menuName");
        validateMenuPrice(menuPrice);

        this.menuType = menuType;
        this.menuName = menuName;
        this.menuPrice = menuPrice;
    }

    private void validateMenuPrice(int menuPrice) {
        if (menuPrice < 0) {
            throw new BusinessException(ChristmasErrorCode.PRICE_CANNOT_NEGATIVE, menuPrice);
        }
    }

    public boolean isType(MenuType target) {
        return this.menuType == target;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Menu menu)) {
            return false;
        }
        return Objects.equals(menuName, menu.menuName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(menuName);
    }

    public MenuType getMenuType() {
        return menuType;
    }

    public String getMenuName() {
        return menuName;
    }

    public int getMenuPrice() {
        return menuPrice;
    }
}
