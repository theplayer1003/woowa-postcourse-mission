package calculator.domain;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PreparedStringFactoryTest {

    @Test
    void new_Success(){
        final PreparedStringFactory preparedStringFactory = new PreparedStringFactory("//;\n1;2;3");

        final PreparedString preparedString = preparedStringFactory.getPreparedString();

        assertThat(preparedString.getValue()).isEqualTo("1;2;3");

        assertThat(preparedString.extractNumberString()).containsExactlyInAnyOrder("1", "2", "3");

        String input = "1,2:3";
        final PreparedStringFactory preparedStringFactory1 = new PreparedStringFactory(input);
        final PreparedString preparedString1 = preparedStringFactory1.getPreparedString();

        assertThat(preparedString1.getValue()).isEqualTo("1,2:3");
        assertThat(preparedString1.extractNumberString()).containsExactlyInAnyOrder("1","2","3");
    }

    @Test
    void test(){
        String input = "1,2:3//";
        final PreparedStringFactory preparedStringFactory1 = new PreparedStringFactory(input);
        final PreparedString preparedString1 = preparedStringFactory1.getPreparedString();

        assertThat(preparedString1.getValue()).isEqualTo("1,2:3//");
        assertThat(preparedString1.extractNumberString()).containsExactlyInAnyOrder("1","2","3");
    }
}