package feature;

import feature.name.Name;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
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
        Name name = new Name("name");

        obj.setName(name);
        Name actual = obj.getName();

        assertThat(actual.getNameText()).isEqualTo(expected);
    }

    @Test
    void 名前にnullを設定すると例外を返す() {
        assertThatThrownBy(() -> obj.setName(null)).isInstanceOf(IllegalStateException.class);
    }
}