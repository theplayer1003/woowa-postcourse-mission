package subway.ui.view;

import java.util.List;
import subway.service.dto.response.LineInfoResponseDto;
import subway.service.dto.response.LineNamesResponseDto;
import subway.service.dto.response.LinesInfoResponseDto;
import subway.service.dto.response.StationNamesResponseDto;

public class OutputView {
    private static final String RESULT_PREFIX = "[INFO] ";
    private static final String ERROR_PREFIX = "[ERROR] ";

    public void printInfo(String message) {
        printBlankLine();
        System.out.println(RESULT_PREFIX + message);
        printBlankLine();
    }

    public void printStations(StationNamesResponseDto stationNamesResponseDto) {
        System.out.println("## 역 목록");
        stationNamesResponseDto.stationNames().forEach(stationName -> System.out.println(RESULT_PREFIX + stationName));
        printBlankLine();
    }

    public void printLines(LineNamesResponseDto lineNamesResponseDto) {
        System.out.println("## 노선 목록");
        lineNamesResponseDto.lineNames().forEach(lineName -> System.out.println(RESULT_PREFIX + lineName));
        printBlankLine();
    }

    public void printLinesInfo(LinesInfoResponseDto linesInfoResponseDto) {
        System.out.println("## 지하철 노선도");

        for (LineInfoResponseDto lineInfoResponseDto : linesInfoResponseDto.lineInfoResponseDtos()) {
            System.out.println(RESULT_PREFIX + lineInfoResponseDto.lineName());
            System.out.println(RESULT_PREFIX + "---");
            lineInfoResponseDto.stationNames().forEach(stationName -> System.out.println(RESULT_PREFIX + stationName));
            printBlankLine();
        }
    }

    public void printError(String message) {
        System.out.println(ERROR_PREFIX + message);
        printBlankLine();
    }

    private static void printBlankLine() {
        System.out.println();
    }
}
