package attendance;

import attendance.global.util.CsvReader;
import attendance.ui.Controller;
import attendance.ui.InputView;
import attendance.ui.OutputView;
import camp.nextstep.edu.missionutils.DateTimes;
import java.time.LocalDateTime;

public class Application {
    public static void main(String[] args) {
        final InputView inputView = new InputView();
        final OutputView outputView = new OutputView();
        final CsvReader csvReader = new CsvReader();

        final Controller controller = new Controller(inputView, outputView, csvReader);

        controller.run();
    }
}
