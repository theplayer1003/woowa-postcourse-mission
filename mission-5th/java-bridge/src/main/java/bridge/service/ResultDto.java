package bridge.service;

import bridge.domain.RoundResult;
import java.util.List;
import java.util.stream.Collectors;

public record ResultDto(String result, boolean failFlag) {
    public static ResultDto from(List<RoundResult> results) {
        final String upLine = results.stream()
                .map(round -> {
                    if (round.getPosition() == 1) {
                        return round.getMark();
                    }
                    return " ";
                })
                .collect(Collectors.joining(" | ", "[ ", " ]"));

        final String downLine = results.stream()
                .map(round -> {
                    if (round.getPosition() == 0) {
                        return round.getMark();
                    }
                    return " ";
                })
                .collect(Collectors.joining(",", "[ ", " ]"));

        return new ResultDto(upLine + "\n" + downLine);
    }
}
