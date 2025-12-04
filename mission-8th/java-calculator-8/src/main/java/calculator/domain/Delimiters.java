package calculator.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Delimiters {
    private static final String BASIC_DELIMITER_COMMA = ",";
    private static final String BASIC_DELIMITER_COLON = ":";
    private static final Pattern CUSTOM_DELIMITER_PATTERN = Pattern.compile("^//(.*)\\n");

    private final List<Delimiter> delimiters;

    private Delimiters(String userInput) {
        List<Delimiter> tempList = new ArrayList<>();
        getBasicDelimiter(tempList);
        addCustomDelimiter(tempList, userInput);

        this.delimiters = List.copyOf(tempList);
    }

    private void getBasicDelimiter(List<Delimiter> tempList) {
        final Delimiter commaDelimiter = Delimiter.from(BASIC_DELIMITER_COMMA);
        final Delimiter colonDelimiter = Delimiter.from(BASIC_DELIMITER_COLON);

        tempList.add(commaDelimiter);
        tempList.add(colonDelimiter);
    }

    private void addCustomDelimiter(List<Delimiter> tempList, String userInput) {
        final Matcher matcher = CUSTOM_DELIMITER_PATTERN.matcher(userInput);

        if (matcher.find()) {
            final String group = matcher.group(1);
            final Delimiter customDelimiter = Delimiter.from(group);
            tempList.add(customDelimiter);
        }
    }

    public static Delimiters from(String userInput) {
        return new Delimiters(userInput);
    }

    public String trimCustomDelimiterSyntax(String userInput) {
        final Matcher matcher = CUSTOM_DELIMITER_PATTERN.matcher(userInput);

        if (matcher.find()) {
            final String trimedUserInput = matcher.replaceFirst("");

            return trimedUserInput;
        }

        return userInput;
    }

    public List<String> split(String target) {
        final String splitRegex = delimiters.stream()
                .map(Delimiter::getValue)
                .map(Pattern::quote)
                .collect(Collectors.joining("|"));

        return List.of(target.split(splitRegex));
    }

    public List<Delimiter> getDelimiters() {
        return delimiters;
    }
}
