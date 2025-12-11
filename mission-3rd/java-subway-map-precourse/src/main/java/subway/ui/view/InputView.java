package subway.ui.view;

import java.util.Scanner;

public class InputView {
    private final Scanner scanner;

    public InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public String inputFunction() {
       return null;
    }

    private String getUserInput(String message) {
        return scanner.nextLine();
    }


}
