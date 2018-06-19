package feature.value.expression.symbol;

import feature.value.expression.symbol.Symbol;
import org.antlr.v4.parse.GrammarTreeVisitor;
import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SymbolTest {

    Symbol obj;

    @Nested
    class 反復テスト {
        final List<String> symbolStrings = Arrays.asList(
                "+", "-", "*", "/", "%",
                "<=", ">=", "<", ">", "==", "!=",
                "&&", "and", "AND",
                "||", "or", "OR",
                "!", "not", "NOT",
                "."
        );
        final List<Symbol> symbols = Arrays.asList(
                new Add(), new Sub(), new Multi(), new Divide(), new Mod(),
                new LessEqual(), new GreaterEqual(), new Less(), new Greater(), new Equal(), new NotEqual(),
                new And(), new And(), new And(),
                new Or(), new Or(), new Or(),
                new Not(), new Not(), new Not(),
                new Dot()
        );
        final List<Boolean> isHadSpaceBothSidesList = Arrays.asList(
                true, true, true, true, true,
                true, true, true, true, true, true,
                true, true, true,
                true, true, true,
                true, true, true,
                false
        );
        final int symbolCount = 21;

        @BeforeEach
        void setup(RepetitionInfo info) {
            obj = symbols.get(info.getCurrentRepetition() - 1);
        }

        @RepeatedTest(symbolCount)
        void オブジェクト状態と文字列が等しい場合は真を返す(RepetitionInfo info) {
            assertThat(obj.is(symbolStrings.get(info.getCurrentRepetition() - 1))).isTrue();
        }

        @RepeatedTest(symbolCount)
        void オブジェクト状態と文字列が等しくない場合は偽を返す(RepetitionInfo info) {
            int count = -1;
            int index = info.getCurrentRepetition() - 1;
            Symbol withoutSymbol = symbols.get(index);

            for (String symbolString : symbolStrings) {
                count++;
                if (symbols.get(count).is(withoutSymbol.toString())) continue;
                assertThat(obj.is(symbolString)).isFalse();
            }
        }

        @RepeatedTest(symbolCount)
        void オブジェクトの文字列を取得する(RepetitionInfo info) {
            String expected = symbolStrings.get(info.getCurrentRepetition() - 1);

            obj.is(expected);
            String actual = obj.toString();

            assertThat(actual).isEqualTo(expected);
        }

        @RepeatedTest(symbolCount)
        void オブジェクトを文字列から選択すると正しいオブジェクト状態を返す(RepetitionInfo info) {
            Symbol expected = symbols.get(info.getCurrentRepetition() - 1);

            Symbol actual = Symbol.choose(symbolStrings.get(info.getCurrentRepetition() - 1));
            actual.is(symbols.get(info.getCurrentRepetition() - 1).toString());

            assertThat(actual).hasToString(expected.toString());
        }

        @RepeatedTest(symbolCount)
        void オブジェクトの両端にスペースを保持しているかどうか正しい真偽値判定を返す(RepetitionInfo info) {
            boolean expected = isHadSpaceBothSidesList.get(info.getCurrentRepetition() - 1);

            Symbol actual = Symbol.choose(symbolStrings.get(info.getCurrentRepetition() - 1));
            actual.is(symbols.get(info.getCurrentRepetition() - 1).toString());

            assertThat(actual.isHadSpaceBothSides()).isEqualTo(expected);
        }

        @RepeatedTest(symbolCount)
        void 存在する演算子を入力すると真を返す(RepetitionInfo info) {

            boolean actual = Symbol.isIncluded(symbolStrings.get(info.getCurrentRepetition() - 1));

            assertThat(actual).isTrue();
        }
    }

    @Nested
    class 静的メソッドについて {

        @Test
        void 演算子の文字列以外を選択すると例外を投げる() {
            assertThatThrownBy(() -> Symbol.choose("ErrorText")).isInstanceOf(IllegalStateException.class);
        }

        @Test
        void 演算子の選択にnullを入力すると例外を投げる() {
            assertThatThrownBy(() -> Symbol.choose(null)).isInstanceOf(IllegalStateException.class);
        }

        @Test
        void 演算子の文字列として存在しない文字列を入れると偽を返す() {

            boolean actual = Symbol.isIncluded("NotFoundText");

            assertThat(actual).isFalse();
        }

        @Test
        void 演算子の文字列として空文字を入れると偽を返す() {

            boolean actual = Symbol.isIncluded("");

            assertThat(actual).isFalse();
        }

        @Test
        void 演算子の文字列としてnullを入れると偽を返す() {

            boolean actual = Symbol.isIncluded(null);

            assertThat(actual).isFalse();
        }
    }
}