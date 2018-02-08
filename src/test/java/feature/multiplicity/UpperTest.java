package feature.multiplicity;

import feature.value.expression.Expression;
import feature.value.expression.OneIdentifier;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UpperTest {

    Upper obj;

    @Nested
    class 式の場合 {

        @Test
        void 設定すると式を返す() {
            String expected = "1";
            obj = new Upper();

            obj.setExpression(new OneIdentifier(1));
            Expression actual = obj.getExpression();

            assertThat(actual).hasToString(expected);
        }
    }
}