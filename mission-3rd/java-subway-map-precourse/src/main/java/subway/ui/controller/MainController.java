package subway.ui.controller;

import java.util.HashMap;
import java.util.Map;
import subway.service.LineService;
import subway.ui.view.InputView;
import subway.ui.view.OutputView;

public class MainController implements Controller {
    private final InputView inputView;
    private final OutputView outputView;
    private final LineService lineService;
    private final Map<String, Controller> controllers = new HashMap<>();

    public MainController(InputView inputView, OutputView outputView, LineService lineService,
                          StationController stationController, LineController lineController,
                          SectionController sectionController) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lineService = lineService;

        controllers.put("1", stationController);
        controllers.put("2", lineController);
        controllers.put("3", sectionController);
    }

    @Override
    public void run() {
        while (true) {
            try {
                final String command = inputView.showMainMenu();
                if ("Q".equals(command)) {
                    return;
                }

                processCommand(command);
            } catch (Exception e) {
                outputView.printError(e.getMessage());
            }
        }
    }

    private void processCommand(String command) {
        if ("4".equals(command)) {
            outputView.printLinesInfo(lineService.getAllLinesInfo());
            return;
        }

        if (controllers.containsKey(command)) {
            controllers.get(command).run();
            return;
        }

        throw new IllegalArgumentException("잘못된 선택입니다.");
    }
}
