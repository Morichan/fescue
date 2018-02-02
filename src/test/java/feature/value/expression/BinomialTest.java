package feature.value.expression;

import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BinomialTest {

    Binomial obj;

    @Nested
    class 演算子が1つの場合 {
        final List<String> symbolStrings = Arrays.asList(
                "+", "-", "*", "/", "%",
                "<=", ">=", "<", ">", "==", "!=",
                "&&", "and", "AND",
                "||", "or", "OR",
                "!", "not", "NOT"
        );
        final int symbolCount = 20;

        @RepeatedTest(symbolCount)
        void 数値同士の足し算を設定すると式を返す(RepetitionInfo info) {
            int index = info.getCurrentRepetition() - 1;
            String expected = "1 " + symbolStrings.get(index) + " 2";

            obj = new Binomial(symbolStrings.get(index), new OneIdentifier(1), new OneIdentifier(2));
            String actual = obj.toString();

            assertThat(actual).isEqualTo(expected);
        }
    }

    @Nested
    class 演算子が2つの場合 {
        final List<String> symbolStrings = Arrays.asList(
                "+", "-", "*", "/", "%",
                "<=", ">=", "<", ">", "==", "!=",
                "&&", "and", "AND",
                "||", "or", "OR",
                "!", "not", "NOT"
        );
        final int symbolCount = 20;
        String firstSymbol;
        String secondSymbol;

        @BeforeEach
        void setup(RepetitionInfo info) {
            int count = info.getCurrentRepetition() - 1;
            firstSymbol = symbolStrings.get(count / symbolCount);
            secondSymbol = symbolStrings.get(count % symbolCount);
        }

        @RepeatedTest(symbolCount * symbolCount)
        void 足し算を1つ目に設定すると足し算が3つ繋がった式を返す(RepetitionInfo info) {
            String expected = "1 "+firstSymbol+" 2 "+secondSymbol+" 3";

            obj = new Binomial(secondSymbol,
                    new Binomial(firstSymbol, new OneIdentifier(1), new OneIdentifier(2)),
                    new OneIdentifier(3));
            String actual = obj.toString();

            assertThat(actual).isEqualTo(expected);
        }

        @RepeatedTest(symbolCount * symbolCount)
        void 足し算を2つ目に設定すると足し算が3つ繋がった式を返す(RepetitionInfo info) {
            String expected = "1 + 2 + 3";

            obj = new Binomial("+",
                    new OneIdentifier(1),
                    new Binomial("+", new OneIdentifier(2), new OneIdentifier(3)));
            String actual = obj.toString();

            assertThat(actual).isEqualTo(expected);
        }

        @RepeatedTest(symbolCount * symbolCount)
        void 括弧で囲んだ足し算を1つ目に設定すると足し算が3つ繋がった式を返す(RepetitionInfo info) {
            String expected = "(1 + 2) + 3";

            obj = new Binomial("+",
                    new ExpressionWithParen(new Binomial("+", new OneIdentifier(1), new OneIdentifier(2))),
                    new OneIdentifier(3));
            String actual = obj.toString();

            assertThat(actual).isEqualTo(expected);
        }

        @RepeatedTest(symbolCount * symbolCount)
        void 括弧で足し算を2つ目に設定すると足し算が3つ繋がった式を返す(RepetitionInfo info) {
            String expected = "1 + (2 + 3)";

            obj = new Binomial("+",
                    new OneIdentifier(1),
                    new ExpressionWithParen(new Binomial("+", new OneIdentifier(2), new OneIdentifier(3))));
            String actual = obj.toString();

            assertThat(actual).isEqualTo(expected);
        }
    }

    @Nested
    class 初期設定が正しくない場合 {

        @Test
        void 演算子にnullを設定すると例外を投げる() {
            assertThatThrownBy(() -> obj = new Binomial(null, new OneIdentifier("is"), new OneIdentifier("Error")))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        void 最初の式にnullを設定すると例外を投げる() {
            assertThatThrownBy(() -> obj = new Binomial("+", null, new OneIdentifier("\"is Error\"")))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        void 次の式にnullを設定すると例外を投げる() {
            assertThatThrownBy(() -> obj = new Binomial("-", new OneIdentifier("\"Error param is\""), null))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }
}