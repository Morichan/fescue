package feature.value.expression;

import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SymbolTest {

    Symbol obj;

    @Nested
    class 反復テスト {
        final List<String> symbolStrings = Arrays.asList(
                "+", "-", "*", "/", "%",
                "<=", ">=", "<", ">", "==", "!=",
                "&&", "and", "AND",
                "||", "or", "OR",
                "!", "not", "NOT"
        );
        final List<Symbol> symbols = Arrays.asList(
                Symbol.Add, Symbol.Sub, Symbol.Multi, Symbol.Divide, Symbol.Mod,
                Symbol.LessEqual, Symbol.GreaterEqual, Symbol.Less, Symbol.Greater, Symbol.Equal, Symbol.NotEqual,
                Symbol.And, Symbol.And, Symbol.And,
                Symbol.Or, Symbol.Or, Symbol.Or,
                Symbol.Not, Symbol.Not, Symbol.Not
        );
        final int symbolCount = 20;

        @BeforeEach
        void setup(RepetitionInfo info) {
            obj = symbols.get(info.getCurrentRepetition() - 1);
        }

        @RepeatedTest(symbolCount)
        void 等しい場合は真を返す(RepetitionInfo info) {
            assertThat(obj.is(symbolStrings.get(info.getCurrentRepetition() - 1))).isTrue();
        }

        @RepeatedTest(symbolCount)
        void 等しくない場合は偽を返す(RepetitionInfo info) {
            int count = -1;
            int index = info.getCurrentRepetition() - 1;
            Symbol withoutSymbol = symbols.get(index);

            for (String symbolString : symbolStrings) {
                count++;
                if (symbols.get(count) == withoutSymbol) continue;
                assertThat(obj.is(symbolString))
                        .isFalse();
            }
        }

        @RepeatedTest(symbolCount)
        void オブジェクトの文字列を取得する(RepetitionInfo info) {
            String expected = symbolStrings.get(info.getCurrentRepetition() - 1);

            obj.is(expected);
            String actual = obj.toString();

            assertThat(actual).isEqualTo(expected);
        }
    }
}