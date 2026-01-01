package christmas.domain.fixture;

import christmas.domain.Menu;
import christmas.domain.MenuType;
import christmas.domain.OrderItem;
import christmas.domain.OrderItems;
import christmas.domain.VisitDate;
import java.util.List;
import org.junit.jupiter.api.Order;

public class ChrismasOrderFixture {

    public static final Menu MUSHROOM_SOUP = new Menu(MenuType.APPETIZER, "양송이수프", 6_000);
    public static final Menu TAPAS = new Menu(MenuType.APPETIZER, "타파스", 5_500);

    public static final Menu T_BONE_STEAK = new Menu(MenuType.MAIN, "티본스테이크", 55_000);
    public static final Menu BBQ_RIPS = new Menu(MenuType.MAIN, "바베큐립", 54_000);

    public static final Menu CHOCO_CAKE = new Menu(MenuType.DESSERT, "초코케이크", 15_000);
    public static final Menu ICE_CREAM = new Menu(MenuType.DESSERT, "아이스크림", 5_000);

    public static final Menu ZERO_COCK = new Menu(MenuType.DRINK, "제로콜라", 3_000);
    public static final Menu RED_WINE = new Menu(MenuType.DRINK, "레드와인", 60_000);

    private ChrismasOrderFixture() {
    }

    public static OrderItem createOrderItem(Menu menu, int quantity) {
        return new OrderItem(menu, quantity);
    }

    public static OrderItem soup(int count) {
        return createOrderItem(MUSHROOM_SOUP, count);
    }

    public static OrderItem tapas(int count) {
        return createOrderItem(TAPAS, count);
    }

    public static OrderItem steak(int count) {
        return createOrderItem(T_BONE_STEAK, count);
    }

    public static OrderItem bbq(int count) {
        return createOrderItem(BBQ_RIPS, count);
    }

    public static OrderItem choco(int count) {
        return createOrderItem(CHOCO_CAKE, count);
    }

    public static OrderItem icecream(int count) {
        return createOrderItem(ICE_CREAM, count);
    }

    public static OrderItem coke(int count) {
        return createOrderItem(ZERO_COCK, count);
    }

    public static OrderItem wine(int count) {
        return createOrderItem(RED_WINE, count);
    }

    public static OrderItems createOrderItems(OrderItem... items) {
        return new OrderItems(List.of(items));
    }

    // order

    public static VisitDate createVisitDate(int day) {
        return new VisitDate(day);
    }
}
