package calculator.domain;

import calculator.util.Validator;
import java.util.ArrayList;
import java.util.List;

public class PositiveNumbers {
    private final List<PositiveNumber> numbers;

    private PositiveNumbers(List<PositiveNumber> numbers) {
        Validator.requireNonNull(numbers, "[ERROR] PositiveNumber 리스트는 null 일 수 없습니다.");

        this.numbers = List.copyOf(numbers);
    }

    public static PositiveNumbers from(List<String> stringNumbers) {
        Validator.requireNonNull(stringNumbers, "[ERROR] 숫자 리스트는 null 일 수 없습니다.");

        return new PositiveNumbers(getPositiveNumberList(stringNumbers));
    }

    private static List<PositiveNumber> getPositiveNumberList(List<String> stringNumbers) {
        return stringNumbers.stream()
                .map(PositiveNumber::from)
                .toList();
    }

    public SumResult addAll() {
        return new SumResult(getSumResult());
    }

    private int getSumResult() {
        int result = 0;
        for (PositiveNumber number : numbers) {
            result += number.getValue();
        }

        return result;
    }

    public List<PositiveNumber> getNumbers() {
        return numbers;
    }
}
