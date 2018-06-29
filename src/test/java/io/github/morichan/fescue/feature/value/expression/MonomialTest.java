package io.github.morichan.fescue.feature.value.expression;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class MonomialTest {

    Monomial obj;

    @Test
    void 式と演算子を設定するとその文字列を返す() {
        String expected = "+ 1";
        obj = new Monomial("+", new OneIdentifier(1));

        String actual = obj.toString();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void 演算子にnullを設定すると例外を投げる() {
        assertThatThrownBy(() -> obj = new Monomial(null, new OneIdentifier("isBadArgument"))).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 式にnullを設定すると例外を投げる() {
        assertThatThrownBy(() -> obj = new Monomial("+", null)).isInstanceOf(IllegalArgumentException.class);
    }


    @Nested
    class 演算子が1つの場合 {
        final List<String> symbolStrings = Arrays.asList(
                "+", "-", "!", "not", "NOT"
        );
        final int symbolCount = 5;

        @RepeatedTest(symbolCount)
        void 数値と演算子を設定すると式を返す(RepetitionInfo info) {
            int index = info.getCurrentRepetition() - 1;
            String expected = symbolStrings.get(index) + " 1";

            obj = new Monomial(symbolStrings.get(index), new OneIdentifier(1));
            String actual = obj.toString();

            assertThat(actual).isEqualTo(expected);
        }
    }
}