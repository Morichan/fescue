package feature.value.expression;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ExpressionWithParenTest {

    ExpressionWithParen obj;

    @Nested
    class 入力が式の場合 {

        @Test
        void 初期化時に数値を設定すると括弧で囲んだ数値を返す() {
            String expected = "(1)";

            obj = new ExpressionWithParen(new OneIdentifier(1));
            String actual = obj.toString();

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void 初期化時に文字列を設定すると文字列を返す() {
            String expected = "(\"string value\")";

            obj = new ExpressionWithParen(new OneIdentifier("\"string value\""));
            String actual = obj.toString();

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void 初期化時に変数名を設定すると変数名を返す() {
            String expected = "(stringName)";

            obj = new ExpressionWithParen(new OneIdentifier("stringName"));
            String actual = obj.toString();

            assertThat(actual).isEqualTo(expected);
        }
    }
}