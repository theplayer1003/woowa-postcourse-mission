package subway.ui.controller;

import java.util.HashMap;
import java.util.Map;
import subway.service.StationService;
import subway.ui.view.InputView;
import subway.ui.view.OutputView;

public class StationController implements Controller {
    private final InputView inputView;
    private final OutputView outputView;
    private final StationService stationService;
    private final Map<String, Runnable> actions = new HashMap<>();

    public StationController(InputView inputView, OutputView outputView, StationService stationService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.stationService = stationService;

        actions.put("1", this::processCreate);
        actions.put("2", this::processDelete);
        actions.put("3", this::processFindAll);
    }

    @Override
    public void run() {
        while (true) {
            try {
                final String command = inputView.showStationMenu();
                if ("B".equals(command)) {
                    return;
                }

                processCommand(command);
            } catch (Exception e) {
                outputView.printError(e.getMessage());
            }
        }
    }

    private void processCommand(String command) {
        if (actions.containsKey(command)) {
            actions.get(command).run();
            return;
        }
        throw new IllegalArgumentException("잘못된 선택 입니다.");
    }

    private void processCreate() {
        final String inputStationNameCreate = inputView.getInputStationNameCreate();
        stationService.createStationByName(inputStationNameCreate);
        outputView.printInfo("지하철 역이 등록되었습니다.");
    }

    private void processDelete() {
        final String inputStationNameDelete = inputView.getInputStationNameDelete();
        stationService.deleteStationByName(inputStationNameDelete);
        outputView.printInfo("지하철 역이 삭제되었습니다.");
    }

    private void processFindAll() {
        outputView.printStations(stationService.findAllStationNames());
    }
}
