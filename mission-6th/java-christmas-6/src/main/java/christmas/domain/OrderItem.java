package christmas.domain;

import christmas.domain.exception.ChristmasErrorCode;
import christmas.global.exception.BusinessException;

public class OrderItem {
    private final Menu menu;
    private final int quantity;

    public OrderItem(Menu menu, int quantity) {
        validateQuantity(quantity);

        this.menu = menu;
        this.quantity = quantity;
    }

    private void validateQuantity(int quantity) {
        if (quantity <= 0) {
            throw new BusinessException(ChristmasErrorCode.QUANTITY_CANNOT_NEGATIVE, quantity);
        }
    }

    public boolean isMenuType(MenuType type) {
        return menu.isType(type);
    }

    public Menu getMenu() {
        return menu;
    }

    public int getQuantity() {
        return quantity;
    }
}
