package oncall;

import oncall.infra.MonthRepository;
import oncall.service.OncallService;
import oncall.ui.InputView;
import oncall.ui.OncallController;
import oncall.ui.OutputView;

public class Application {
    public static void main(String[] args) {
        final MonthRepository monthRepository = new MonthRepository();
        final OncallService oncallService = new OncallService(monthRepository);

        final InputView inputView = new InputView();
        final OutputView outputView = new OutputView();

        final OncallController oncallController = new OncallController(oncallService, inputView, outputView);

        oncallController.run();
    }
}
