package subway.ui.view;

import java.util.Scanner;
import subway.global.exception.BusinessException;
import subway.ui.exception.ConsoleErrorCode;

public class InputView {
    private final Scanner scanner;

    public InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public String selectAction() {
        return getUserInput("원하는 기능을 선택하세요.");
    }

    public String showMainMenu() {
        System.out.println("""
                ## 메인 화면
                1. 역 관리
                2. 노선 관리
                3. 구간 관리
                4. 지하철 노선도 출력
                Q. 종료
                
                """);
        return selectAction();
    }

    public String showStationMenu() {
        System.out.println("""
                ## 역 관리 화면
                1. 역 등록
                2. 역 삭제
                3. 역 조회
                B. 돌아가기
                
                """);
        return selectAction();
    }

    public String showLineMenu() {
        System.out.println("""
                ## 노선 관리 화면
                1. 노선 등록
                2. 노선 삭제
                3. 노선 조회
                B. 돌아가기
                
                """);
        return selectAction();
    }

    public String showSectionMenu() {
        System.out.println("""
                ## 구간 관리 화면
                1. 구간 등록
                2. 구간 삭제
                B. 돌아가기
                
                """);
        return selectAction();
    }

    public String getInputStationNameCreate() {
        return getUserInput("등록할 역 이름을 입력하세요.");
    }

    public String getInputStationNameDelete() {
        return getUserInput("삭제할 역 이름을 입력하세요.");
    }

    public String getInputLineNameCreate() {
        return getUserInput("등록할 노선 이름을 입력하세요.");
    }

    public String getInputUpBoundName() {
        return getUserInput("등록할 노선의 상행 종점역 이름을 입력하세요.");
    }

    public String getInputDownBoundName() {
        return getUserInput("등록할 노선의 하행 종점역 이름을 입력하세요.");
    }

    public String getInputLineNameDelete() {
        return getUserInput("삭제할 노선 이름을 입력하세요.");
    }

    public String getInputSectionName() {
        return getUserInput("노선을 입력하세요.");
    }

    public String getInputSectionStationName() {
        return getUserInput("역이름을 입력하세요.");
    }

    public String getInputSectionStationNameDelete() {
        return getUserInput("삭제할 구간의 역을 입력하세요");
    }

    public String getInputSectionLineNameDelete() {
        return getUserInput("삭제할 구간의 노선을 입력하세요.");
    }

    public int getInputSectionIndex() {
        try {
            final String userInput = getUserInput("순서를 입력하세요.");
            return Integer.parseInt(userInput);
        } catch (NumberFormatException e) {
            throw new BusinessException(ConsoleErrorCode.USERINPUT_NOT_NUMBER);
        }

    }

    private String getUserInput(String prompt) {
        System.out.println("## " + prompt);
        final String userInput = scanner.nextLine();
        validateNullOrBlank(userInput);
        return userInput.trim();
    }

    private void validateNullOrBlank(String userInput) {
        if (userInput == null || userInput.isBlank()) {
            throw new BusinessException(ConsoleErrorCode.USERINPUT_INVALID_BLANK);
        }
    }


}
