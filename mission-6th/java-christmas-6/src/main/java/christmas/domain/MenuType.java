package christmas.domain;

public enum MenuType {
    APPETIZER("에피타이저"),
    MAIN("메인"),
    DESSERT("디저트"),
    DRINK("음료");

    private final String description;

    MenuType(String description) {
        this.description = description;
    }

    public static MenuType findByDescription(String description) {
        for (MenuType type : values()) {
            if (type.description.equals(description)) {
                return type;
            }
        }

        throw new IllegalArgumentException("유효하지 않은 메뉴 타입입니다 " + description);
    }
}
