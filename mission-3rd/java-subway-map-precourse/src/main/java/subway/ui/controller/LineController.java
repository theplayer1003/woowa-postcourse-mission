package subway.ui.controller;

import java.util.HashMap;
import java.util.Map;
import subway.service.LineService;
import subway.service.dto.request.CreateLineDto;
import subway.ui.view.InputView;
import subway.ui.view.OutputView;

public class LineController implements Controller {
    private final InputView inputView;
    private final OutputView outputView;
    private final LineService lineService;
    private final Map<String, Runnable> actions = new HashMap<>();

    public LineController(InputView inputView, OutputView outputView, LineService lineService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lineService = lineService;

        actions.put("1", this::processCreate);
        actions.put("2", this::processDelete);
        actions.put("3", this::processFindAll);
    }

    @Override
    public void run() {
        while (true) {
            try {
                final String command = inputView.showLineMenu();
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

    private void processDelete() {
        final String lineName = inputView.getInputLineNameDelete();
        lineService.deleteLineByName(lineName);
        outputView.printInfo("지하철 노선이 삭제되었습니다.");
    }

    private void processFindAll() {
        outputView.printLines(lineService.findAllLineNames());
    }

    private void processCreate() {
        final String lineName = inputView.getInputLineNameCreate();
        final String upBoundName = inputView.getInputUpBoundName();
        final String downBoundName = inputView.getInputDownBoundName();

        final CreateLineDto createLineDto = new CreateLineDto(lineName, upBoundName, downBoundName);
        lineService.createLine(createLineDto);

        outputView.printInfo("지하철 노선이 등록되었습니다.");
    }
}
