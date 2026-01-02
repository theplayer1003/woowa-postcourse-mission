package oncall.domain;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StaffTest {

    @Test
    void Staff_CreateSuccess(){
        final Staff staff = new Staff(new StaffName("루루"));

        assertThat(staff).isNotNull();

        assertThat(staff.getName().name()).isEqualTo("루루");
    }

    @Test
    void equals(){
        final Staff target = new Staff(new StaffName("루루"));
        final Staff same = new Staff(new StaffName("루루"));
        final Staff another = new Staff(new StaffName("누누"));


        assertThat(target.equals(same)).isTrue();
        assertThat(target.equals(another)).isFalse();
    }
}