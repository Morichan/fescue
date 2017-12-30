package usage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AttributeEvaluationTest {

    AttributeEvaluation obj;

    @Nested
    class 正しい文法を入力する際に {

        @Nested
        class 文を入力していない場合 {

            @BeforeEach
            void setup() {
                obj = new AttributeEvaluation();
            }

            @Test
            void テキストをセットして走査するとエラーを返さない() {
                String expected = "attribute";
                String actual;

                obj.setAttribute("attribute");
                obj.walk();
                actual = obj.getAttribute();

                assertThat(actual).isEqualTo(expected);
            }
        }

        @Nested
        class 名前だけの場合 {
            final String attribute = "attribute";

            @BeforeEach
            void setup() {
                walk(attribute);
            }

            @Test
            void 属性を返す() {
                String expected = "attribute";

                String actual = obj.extractClassName();

                assertThat(actual).isEqualTo(expected);
            }
        }

        @Nested
        class 可視性を含む場合 {
            final String attribute = "- attribute";

            @BeforeEach
            void setup() {
                walk(attribute);
            }

            @Test
            void 属性を返す() {
                String expected = "attribute";

                String actual = obj.extractClassName();

                assertThat(actual).isEqualTo(expected);
            }

            @Test
            void 可視性を返す() {
                String expected = "-";

                String actual = obj.extractVisibility();

                assertThat(actual).isEqualTo(expected);
            }
        }

        @Nested
        class 派生を含む場合 {
            final String attribute = "/attribute";

            @BeforeEach
            void setup() {
                walk(attribute);
            }

            @Test
            void 属性を返す() {
                String expected = "attribute";

                String actual = obj.extractClassName();

                assertThat(actual).isEqualTo(expected);
            }

            @Test
            void 派生を返す() {
                String expected = "/";

                String actual = obj.extractDivided();

                assertThat(actual).isEqualTo(expected);
            }
        }

        @Nested
        class 属性と派生を含む場合 {
            final String attribute = "- /attribute";

            @BeforeEach
            void setup() {
                walk(attribute);
            }

            @Test
            void 属性を返す() {
                String expected = "attribute";

                String actual = obj.extractClassName();

                assertThat(actual).isEqualTo(expected);
            }

            @Test
            void 可視性を返す() {
                String expected = "-";

                String actual = obj.extractVisibility();

                assertThat(actual).isEqualTo(expected);
            }

            @Test
            void 派生を返す() {
                String expected = "/";

                String actual = obj.extractDivided();

                assertThat(actual).isEqualTo(expected);
            }
        }

        @Nested
        class 型を含む場合 {
            final String attribute = "attribute : int";

            @BeforeEach
            void setup() {
                walk(attribute);
            }

            @Test
            void 属性を返す() {
                String expected = "attribute";

                String actual = obj.extractClassName();

                assertThat(actual).isEqualTo(expected);
            }

            @Test
            void 型を返す() {
                String expected = "int";

                String actual = obj.extractPropType();

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
                obj = new AttributeEvaluation();
            }

            @Test
            void テキストをセットせずに走査したらエラーを返す() {
                assertThrows(IllegalArgumentException.class, () -> obj.walk());
            }
        }
    }

    private void walk(String text) {
        obj = new AttributeEvaluation();
        obj.setAttribute(text);
        obj.walk();
    }
}