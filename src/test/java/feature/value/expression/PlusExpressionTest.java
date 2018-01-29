package feature.value.expression;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PlusExpressionTest {

    PlusExpression obj;

    @Test
    void 数値同士の足し算を設定すると式を返す() {
        String expected = "1 + 2";

        obj = new PlusExpression(new OneIdentifier(1), new OneIdentifier(2));
        String actual = obj.toString();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void 足し算を1つ目に設定すると足し算が3つ繋がった式を返す() {
        String expected = "1 + 2 + 3";

        obj = new PlusExpression(
                new PlusExpression(new OneIdentifier(1), new OneIdentifier(2)),
                new OneIdentifier(3));
        String actual = obj.toString();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void 足し算を2つ目に設定すると足し算が3つ繋がった式を返す() {
        String expected = "1 + 2 + 3";

        obj = new PlusExpression(
                new OneIdentifier(1),
                new PlusExpression(new OneIdentifier(2), new OneIdentifier(3)));
        String actual = obj.toString();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void 括弧で囲んだ足し算を1つ目に設定すると足し算が3つ繋がった式を返す() {
        String expected = "(1 + 2) + 3";

        obj = new PlusExpression(
                new ExpressionWithParen(new PlusExpression(new OneIdentifier(1), new OneIdentifier(2))),
                new OneIdentifier(3));
        String actual = obj.toString();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void 括弧で足し算を2つ目に設定すると足し算が3つ繋がった式を返す() {
        String expected = "1 + (2 + 3)";

        obj = new PlusExpression(
                new OneIdentifier(1),
                new ExpressionWithParen(new PlusExpression(new OneIdentifier(2), new OneIdentifier(3))));
        String actual = obj.toString();

        assertThat(actual).isEqualTo(expected);
    }
}