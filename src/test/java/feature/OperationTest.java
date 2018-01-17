package feature;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class OperationTest {
    Operation obj;

    @BeforeEach
    void setup() {
        obj = new Operation();
    }

    @Test
    void 名前を設定するとその名前を返す() {
        String expected = "name";

        obj.setName(expected);
        String actual = obj.getName();

        assertThat(actual).isEqualTo(expected);
    }
}