package christmas.service;

import christmas.domain.Menu;
import christmas.domain.MenuType;
import christmas.global.util.CsvReader;
import christmas.infra.MenuRepository;
import java.util.List;

public class MenuInitializer {
    private final CsvReader csvReader;
    private final MenuRepository menuRepository;

    public MenuInitializer(CsvReader csvReader, MenuRepository menuRepository) {
        this.csvReader = csvReader;
        this.menuRepository = menuRepository;
    }

    public void init() {
        final List<Menu> menus = csvReader.read("menu.csv", (line) -> {
            final String[] parts = line.split(",");

            return new Menu(
                    MenuType.valueOf(parts[0].trim()),
                    parts[1].trim(),
                    Integer.parseInt(parts[2].trim())
            );
        });

        menus.forEach(menuRepository::save);
    }
}
