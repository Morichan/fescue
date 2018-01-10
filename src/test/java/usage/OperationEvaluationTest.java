package usage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class OperationEvaluationTest {

    OperationEvaluation obj;

    @Nested
    class 正しい文法を入力する際に {

        @Nested
        class 文を入力していない場合 {

            @BeforeEach
            void setup() {
                obj = new OperationEvaluation();
            }

            @Test
            void 文を設定すると文を返す() {
                String expected = "+ operation()";

                obj.setText(expected);
                String actual = obj.getText();

                assertThat(actual).isEqualTo(expected);
            }

            @Test
            void 文を設定して走査するとエラーを返さない() {
                String expected = "+ operation()";

                obj.setText(expected);
                obj.walk();
                String actual = obj.getText();

                assertThat(actual).isEqualTo(expected);
            }
        }
    }

    @Nested
    class 正しくない文法を入力する際に {

        @Nested
        class 文を入力していない場合 {

            @BeforeEach
            void setup() {
                obj = new OperationEvaluation();
            }

            @Test
            void 文を設定せずに取得しようとするとnullを返す() {

                String actual = obj.getText();

                assertThat(actual).isNull();
            }
        }
    }
}