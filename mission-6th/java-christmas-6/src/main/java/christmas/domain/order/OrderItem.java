package christmas.domain.order;

import christmas.domain.exception.ChristmasErrorCode;
import christmas.global.exception.BusinessException;
import christmas.global.util.Validator;
import java.util.Objects;

public class OrderItem {
    private final Menu menu;
    private final int quantity;

    public OrderItem(Menu menu, int quantity) {
        Validator.requireNonNull(menu, "menu");
        validateQuantity(quantity);

        this.menu = menu;
        this.quantity = quantity;
    }

    private void validateQuantity(int quantity) {
        if (quantity <= 0) {
            throw new BusinessException(ChristmasErrorCode.QUANTITY_CANNOT_NEGATIVE, quantity);
        }
    }

    public boolean isMenuType(MenuType target) {
        return menu.isType(target);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof OrderItem orderItem)) {
            return false;
        }
        return quantity == orderItem.quantity && Objects.equals(menu, orderItem.menu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(menu, quantity);
    }

    public Menu getMenu() {
        return menu;
    }

    public int getQuantity() {
        return quantity;
    }
}
