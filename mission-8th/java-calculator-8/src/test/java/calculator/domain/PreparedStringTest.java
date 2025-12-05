package calculator.domain;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;

class PreparedStringTest {

    @Test
    void from_Success() {
        final Delimiters delimiters = Delimiters.from("//;\n1;2;3");
        final PreparedString preparedString = PreparedString.from("1;2;3", delimiters);

        final String trimed = preparedString.getValue();

        assertThat(trimed).isEqualTo("1;2;3");
    }

    @Test
    void extractNumberString_Success() {
        final Delimiters delimiters = Delimiters.from("//;\n1;2;3");
        final PreparedString preparedString = PreparedString.from("1;2;3", delimiters);

        final List<String> splitNumbers = preparedString.extractNumberString();

        assertThat(splitNumbers).containsExactlyInAnyOrder("1", "2", "3");
    }

}