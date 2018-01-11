package usage;

import org.antlr.v4.runtime.InputMismatchException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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

        @Nested
        class 名前だけの場合 {
            final String operation = "operation()";

            @BeforeEach
            void setup() {
                walk(operation);
            }

            @Test
            void 属性を返す() {
                String expected = "operation";

                String actual = obj.extractName();

                assertThat(actual).isEqualTo(expected);
            }
        }

        @Nested
        class 可視性を含む場合 {
            final String operation = "+ operation()";

            @BeforeEach
            void setup() {
                walk(operation);
            }

            @Test
            void 属性を返す() {
                String expected = "operation";

                String actual = obj.extractName();

                assertThat(actual).isEqualTo(expected);
            }

            @Test
            void 可視性を返す() {
                String expected = "+";

                String actual = obj.extractVisibility();

                assertThat(actual).isEqualTo(expected);
            }

            @Test
            void 可視性を書いていない場合はnullを返す() {
                walk("doNotHasVisibility()");

                assertThat(obj.extractVisibility()).isNull();
            }
        }

        @Nested
        class 型を含む場合 {
            final String operation = "operation() : int";

            @BeforeEach
            void setup() {
                walk(operation);
            }

            @Test
            void 名前を返す() {
                String expected = "operation";

                String actual = obj.extractName();

                assertThat(actual).isEqualTo(expected);
            }

            @Test
            void 型を返す() {
                String expected = "int";

                String actual = obj.extractReturnType();

                assertThat(actual).isEqualTo(expected);
            }
        }

        @Nested
        class プロパティを含む場合 {

            @Nested
            class 単純なプロパティが1つであれば {

                final String operation = "operation () {query}";

                @BeforeEach
                void setup() {
                    walk(operation);
                }

                @Test
                void プロパティを返す() {
                    String expected = "query";

                    String actual = obj.extractOperationProperty();

                    assertThat(actual).isEqualTo(expected);
                }
            }

            @Nested
            class 単純なプロパティが複数であれば {

                final String operation = "operation() {query, ordered, unique}";

                @BeforeEach
                void setup() {
                    walk(operation);
                }

                @Test
                void プロパティを返す() {
                    String expected = "query, ordered, unique";

                    String actual = obj.extractOperationProperty();

                    assertThat(actual).isEqualTo(expected);
                }
            }

            @Nested
            class プロパティ名を持つプロパティが1つであれば {

                final String operation = "operation () {redefines method()}";

                @BeforeEach
                void setup() {
                    walk(operation);
                }

                @Test
                void プロパティを返す() {
                    String expected = "redefines method()";

                    String actual = obj.extractOperationProperty();

                    assertThat(actual).isEqualTo(expected);
                }
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

            @Test
            void 走査したら例外を返す() {
                obj = new OperationEvaluation();

                assertThatThrownBy(() -> obj.walk()).isInstanceOf(IllegalArgumentException.class);
            }
        }

        @Nested
        class 取得する要素が {

            @Nested
            class 名前の場合 {

                @Test
                void 空文字を入力するとエラーを返す() {
                    walk("");

                    assertThatThrownBy(() -> obj.extractName()).isInstanceOf(IllegalArgumentException.class);
                }

                @Test
                void 予約語と同じ文字列を入力するとエラーを返す() {
                    walk("not() : float");

                    assertThatThrownBy(() -> obj.extractName()).isInstanceOf(InputMismatchException.class);
                }
            }

            @Nested
            class 可視性の場合 {

                @Test
                void 予約語と同じ文字列を名前に入力してもエラーは返さない() {
                    walk("+ int()");

                    assertThat(obj.extractVisibility()).isEqualTo("+");
                }
            }

            @Nested
            class 型の場合 {

                @Test
                void 予約語と同じ文字列を名前に入力するとエラーを返す() {
                    walk("double() : double");

                    assertThatThrownBy(() -> obj.extractReturnType()).isInstanceOf(InputMismatchException.class);
                }
            }

            @Nested
            class プロパティの場合 {

                @Test
                void 予約語と同じ文字列を名前に入力するとエラーを返す() {
                    walk("int() {query}");

                    assertThatThrownBy(() -> obj.extractOperationProperty()).isInstanceOf(InputMismatchException.class);
                }
            }
        }
    }

    private void walk(String text) {
        obj = new OperationEvaluation();
        obj.setText(text);
        obj.walk();
    }
}