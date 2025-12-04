package calculator.domain;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;

class DelimitersTest {

    @Test
    void from_CreateSuccess() {
        final Delimiters delimiters = Delimiters.from("//;\n1;2;3");
        final List<Delimiter> delimitersList = delimiters.getDelimiters();

        assertThat(delimitersList)
                .extracting(Delimiter::getValue)
                .containsExactlyInAnyOrder(",", ":", ";");
    }

    @Test
    void from_CheckBasicDelimiters() {
        final Delimiters delimiters = Delimiters.from("1;2;3");

        assertThat(delimiters.getDelimiters())
                .extracting(Delimiter::getValue)
                .containsExactlyInAnyOrder(",", ":");

    }

    @Test
    void from_CheckCustomDelimiters(){
        final Delimiters delimiters = Delimiters.from("//;\n1;2;3");

        assertThat(delimiters.getDelimiters())
                .extracting(Delimiter::getValue)
                .contains(";");
    }

    @Test
    void from_CheckNoCustomDelimiters(){
        final Delimiters delimiters = Delimiters.from("1;2;3//;\n");

        assertThat(delimiters.getDelimiters())
                .extracting(Delimiter::getValue)
                .containsExactlyInAnyOrder(",", ":");
    }

    @Test
    void trimCustomDelimiterSyntax_Success(){
        final Delimiters delimiters = Delimiters.from("//;\n1;2;3");

        final String trimedUserInput = delimiters.trimCustomDelimiterSyntax("//;\n1;2;3");

        assertThat(trimedUserInput).isEqualTo("1;2;3");
    }
}