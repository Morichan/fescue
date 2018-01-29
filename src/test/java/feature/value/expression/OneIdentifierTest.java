package feature.value.expression;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class OneIdentifierTest {

    OneIdentifier obj;

    @Test
    void 初期化時に数値を直接設定すると数値を返す() {
        String expected = "1";

        obj = new OneIdentifier(1);
        String actual = obj.toString();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void 初期化時に文字列を直接設定すると文字列を返す() {
        String expected = "\"string value\"";

        obj = new OneIdentifier("\"string value\"");
        String actual = obj.toString();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void 初期化時に変数名を直接設定すると変数名を返す() {
        String expected = "stringName";

        obj = new OneIdentifier("stringName");
        String actual = obj.toString();

        assertThat(actual).isEqualTo(expected);
    }
}