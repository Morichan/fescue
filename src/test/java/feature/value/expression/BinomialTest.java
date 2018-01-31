package feature.value.expression;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BinomialTest {

    Binomial obj;

    @Test
    void 数値同士の足し算を設定すると式を返す() {
        String expected = "1 + 2";

        obj = new Binomial(new OneIdentifier(1), new OneIdentifier(2));
        String actual = obj.toString();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void 足し算を1つ目に設定すると足し算が3つ繋がった式を返す() {
        String expected = "1 + 2 + 3";

        obj = new Binomial(
                new Binomial(new OneIdentifier(1), new OneIdentifier(2)),
                new OneIdentifier(3));
        String actual = obj.toString();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void 足し算を2つ目に設定すると足し算が3つ繋がった式を返す() {
        String expected = "1 + 2 + 3";

        obj = new Binomial(
                new OneIdentifier(1),
                new Binomial(new OneIdentifier(2), new OneIdentifier(3)));
        String actual = obj.toString();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void 括弧で囲んだ足し算を1つ目に設定すると足し算が3つ繋がった式を返す() {
        String expected = "(1 + 2) + 3";

        obj = new Binomial(
                new ExpressionWithParen(new Binomial(new OneIdentifier(1), new OneIdentifier(2))),
                new OneIdentifier(3));
        String actual = obj.toString();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void 括弧で足し算を2つ目に設定すると足し算が3つ繋がった式を返す() {
        String expected = "1 + (2 + 3)";

        obj = new Binomial(
                new OneIdentifier(1),
                new ExpressionWithParen(new Binomial(new OneIdentifier(2), new OneIdentifier(3))));
        String actual = obj.toString();

        assertThat(actual).isEqualTo(expected);
    }
}