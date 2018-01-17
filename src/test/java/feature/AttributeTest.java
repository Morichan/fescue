package feature;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AttributeTest {
    Attribute obj;

    @BeforeEach
    void setup() {
        obj = new Attribute();
    }

    @Test
    void 文字列を設定するとその文字列を返す() {
        String expected = "name";

        obj.setName(expected);
        String actual = obj.getName();

        assertThat(actual).isEqualTo(expected);
    }
}