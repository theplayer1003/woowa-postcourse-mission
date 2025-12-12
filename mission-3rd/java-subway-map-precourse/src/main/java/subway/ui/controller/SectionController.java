package subway.ui.controller;

import java.util.HashMap;
import java.util.Map;
import subway.service.LineService;
import subway.service.dto.request.AddStationInLineDto;
import subway.service.dto.request.DeleteStationInLineDto;
import subway.ui.view.InputView;
import subway.ui.view.OutputView;

public class SectionController implements Controller {
    private final InputView inputView;
    private final OutputView outputView;
    private final LineService lineService;
    private final Map<String, Runnable> actions = new HashMap<>();

    public SectionController(InputView inputView, OutputView outputView, LineService lineService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lineService = lineService;

        actions.put("1", this::processAdd);
        actions.put("2", this::processDelete);
    }

    @Override
    public void run() {
        while (true) {
            try {
                final String command = inputView.showSectionMenu();
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
        throw new IllegalArgumentException("잘못된 선택입니다.");
    }

    private void processAdd() {
        final String lineName = inputView.getInputSectionName();
        final String stationName = inputView.getInputSectionStationName();
        final int index = inputView.getInputSectionIndex();

        final AddStationInLineDto addStationInLineDto = new AddStationInLineDto(lineName, stationName, index);
        lineService.addStationInLine(addStationInLineDto);

        outputView.printInfo("구간이 등록되었습니다.");
    }

    private void processDelete() {
        final String lineName = inputView.getInputSectionLineNameDelete();
        final String stationName = inputView.getInputSectionStationNameDelete();

        final DeleteStationInLineDto deleteStationInLineDto = new DeleteStationInLineDto(lineName, stationName);
        lineService.deleteStationInLine(deleteStationInLineDto);

        outputView.printInfo("구간이 삭제되었습니다.");
    }
}
