package subway;

import java.util.Scanner;
import subway.config.AppConfig;
import subway.config.DataInitializer;
import subway.ui.controller.MainController;

public class Application {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            final AppConfig appConfig = new AppConfig(scanner);

            final DataInitializer dataInitializer = appConfig.dataInitializer();
            dataInitializer.init();

            final MainController mainController = appConfig.mainController();
            mainController.run();
        }
    }
}
