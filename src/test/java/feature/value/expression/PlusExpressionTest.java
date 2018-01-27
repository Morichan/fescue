package feature.value.expression;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PlusExpressionTest {

    PlusExpression obj;

    @BeforeEach
    void setup() {
        obj = new PlusExpression();
    }

    @Test
    void 数値同士の足し算を設定すると式を返す() {
        String expected = "1 + 2";
        Expression added = new OneIdentifier();
        Expression adding = new OneIdentifier();
        added.set(new Identifier(1));
        adding.set(new Identifier(2));

        obj.set(added, adding);
        String actual = obj.toString();

        assertThat(actual).isEqualTo(expected);
    }
}