package calculator.domain;

public class PreparedStringFactory {
    private final String userInput;

    public PreparedStringFactory(String userInput) {
        this.userInput = userInput;
    }

    public PreparedString getPreparedString() {
        final Delimiters delimiters = Delimiters.from(userInput);
        final String trimedUserInput = delimiters.trimCustomDelimiterSyntax(userInput);

        final PreparedString preparedString = PreparedString.from(trimedUserInput, delimiters);

        return preparedString;
    }
}
