package feature.value.expression;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class OneIdentifierTest {

    OneIdentifier obj;

    @BeforeEach
    void setup() {
        obj = new OneIdentifier();
    }

    @Test
    void 数値を設定すると数値を返す() {
        String expected = "1";

        obj.set(new Identifier(1));
        String actual = obj.toString();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void 文字列を設定すると文字列を返す() {
        String expected = "\"string value\"";

        obj.set(new Identifier("\"string value\""));
        String actual = obj.toString();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void 変数名を設定すると変数名を返す() {
        String expected = "stringName";

        obj.set(new Identifier("stringName"));
        String actual = obj.toString();

        assertThat(actual).isEqualTo(expected);
    }
}