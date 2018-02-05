package feature.value;

import feature.value.expression.Binomial;
import feature.value.expression.Expression;
import feature.value.expression.OneIdentifier;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.assertj.core.api.Java6Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class DefaultValueTest {

    DefaultValue obj;

    @Test
    void 既定値の式を設定すると式の文字列を返す() {
        String expected = "1";
        obj = new DefaultValue(new OneIdentifier(1));

        String actual = obj.toString();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void 既定値の式にnullを設定すると例外を投げる() {
        assertThatThrownBy(() -> obj = new DefaultValue(null)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 既定値の式を設定すると式を返す() {
        String expected = "1 + 2";
        obj = new DefaultValue(new Binomial("+", new OneIdentifier(1), new OneIdentifier(2)));

        Expression actual = obj.getExpression();

        assertThat(actual).hasToString(expected);
    }
}