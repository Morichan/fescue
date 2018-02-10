package feature.multiplicity;

import feature.value.expression.Expression;
import feature.value.expression.OneIdentifier;
import net.java.quickcheck.Generator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static net.java.quickcheck.generator.PrimitiveGenerators.letterStrings;
import static net.java.quickcheck.generator.PrimitiveGenerators.strings;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BounderTest {

    Bounder obj;

    @Nested
    class 式の場合 {

        @Test
        void 設定すると式を返す() {
            String expected = "1";

            obj = new Bounder(new OneIdentifier(1));
            Expression actual = obj.getExpression();

            assertThat(actual).hasToString(expected);
        }

        @Test
        void 設定せずに取得しようとすると例外を投げる() {
            obj = new Bounder("expression is null");

            assertThatThrownBy(() -> obj.getExpression()).isInstanceOf(IllegalStateException.class);
        }
    }

    @Nested
    class アスタリスクの場合 {
        final Generator<String> textGenerator = strings();

        @Test
        void 設定するとアスタリスクを文字列として返す() {
            String expected = "*";

            obj = new Bounder("*");
            String actual = obj.toString();

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void 空文字を設定すると例外を投げる() {
            assertThatThrownBy(() -> obj = new Bounder("")).isInstanceOf(IllegalArgumentException.class);
        }

        @RepeatedTest(100)
        void アスタリスク以外を設定すると文字列を返す() {
            String expected = selectNameWithoutAsterisk();

            obj = new Bounder(expected);
            String actual = obj.toString();

            assertThat(actual).isEqualTo(expected);
        }

        private String selectNameWithoutAsterisk() {
            String text;

            do {
                text = textGenerator.next();
            } while (text.length() <= 0 || text.equals("*"));

            return text;
        }
    }
}