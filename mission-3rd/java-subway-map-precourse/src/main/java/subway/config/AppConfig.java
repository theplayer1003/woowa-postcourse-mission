package subway.config;

import java.util.Scanner;
import subway.domain.LineRepository;
import subway.domain.StationRepository;
import subway.service.LineService;
import subway.service.StationService;
import subway.ui.controller.LineController;
import subway.ui.controller.MainController;
import subway.ui.controller.SectionController;
import subway.ui.controller.StationController;
import subway.ui.view.InputView;
import subway.ui.view.OutputView;

public class AppConfig {

    private final Scanner scanner;

    public AppConfig(Scanner scanner) {
        this.scanner = scanner;
    }

    public MainController mainController() {
        return new MainController(
                inputView(),
                outputView(),
                lineService(),
                stationController(),
                lineController(),
                sectionController()
        );
    }

    public DataInitializer dataInitializer() {
        return new DataInitializer(stationService(), lineService());
    }

    private InputView inputView() {
        return new InputView(scanner);
    }

    private OutputView outputView() {
        return new OutputView();
    }

    private LineService lineService() {
        return new LineService(lineRepository(), stationService());
    }

    private LineRepository lineRepository() {
        return new LineRepository();
    }

    private StationService stationService() {
        return new StationService(stationRepository());
    }

    private StationController stationController() {
        return new StationController(inputView(), outputView(), stationService());
    }

    private LineController lineController() {
        return new LineController(inputView(), outputView(), lineService());
    }

    private SectionController sectionController() {
        return new SectionController(inputView(), outputView(), lineService());
    }

    private StationRepository stationRepository() {
        return new StationRepository();
    }
}
