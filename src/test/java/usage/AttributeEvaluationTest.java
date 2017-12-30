package usage;

import net.java.quickcheck.Generator;
import net.java.quickcheck.generator.PrimitiveGenerators;
import org.antlr.v4.runtime.InputMismatchException;
import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.List;

import static net.java.quickcheck.generator.CombinedGenerators.lists;
import static net.java.quickcheck.generator.PrimitiveGenerators.letterStrings;
import static net.java.quickcheck.generator.PrimitiveGenerators.strings;
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

        @Nested
        class QuickCheckの場合 {
            final Generator<String> nameGenerator = letterStrings();
            final Generator<String> visibilityGenerator = strings(" -+#~");

            @RepeatedTest(100)
            void 名前を返す() {
                String expected = selectNameOfLengthZero();
                walk(expected);

                try {
                    String actual = obj.extractClassName();
                    assertThat(actual).isEqualTo(expected);

                } catch (InputMismatchException e) {
                    System.out.println("InputMismatchException : input '"+expected+"'");
                }
            }

            @RepeatedTest(100)
            void 可視性を返す() {
                String expected = selectNameOfLengthZero();
                String visibilities = visibilityGenerator.next();
                String visibility;
                try {
                    visibility = visibilities.substring(0, 1);
                } catch (StringIndexOutOfBoundsException e) {
                    visibility = "";
                }
                walk(visibility + " " + expected);

                String actual = obj.extractVisibility();

                if (visibility.length() <= 0 || visibility.equals(" ")) assertThat(actual).isEqualTo("");
                else assertThat(actual).isEqualTo(visibility);
            }

            private String selectNameOfLengthZero() {
                String text;

                do {
                    text = nameGenerator.next();
                } while (text.length() <= 0);

                return text;
            }
        }

        @Nested
        class 反復テストの場合 {
            final List<String> attributes = Arrays.asList(
                    "attribute",
                    "- attribute",
                    "+ attribute",
                    "# attribute",
                    "~ attribute",
                    "/attribute",
                    "- /attribute",
                    "+ /attribute",
                    "# /attribute",
                    "~ /attribute",
                    "attribute : bool",
                    "- /attribute : boolean",
                    "+ attribute : c",
                    "# /attribute : char",
                    "~ attribute : character",
                    "/attribute : byte",
                    "- attribute : s",
                    "+ /attribute : short",
                    "# attribute : i",
                    "~ /attribute : int",
                    "attribute : integer",
                    "- /attribute : l",
                    "+ attribute : long",
                    "# /attribute : f",
                    "~ attribute : float",
                    "/attribute : lf",
                    "- attribute : double"
            );

            final int testCount = 27;

            @BeforeEach
            void setup(RepetitionInfo info) {
                walk(attributes.get(info.getCurrentRepetition() - 1));
            }

            @RepeatedTest(testCount)
            void 名前を返す() {
                String expected = "attribute";

                String actual = obj.extractClassName();

                assertThat(actual).isEqualTo(expected);
            }

            @RepeatedTest(testCount)
            void 属性を返す(RepetitionInfo info) {
                List<String> visibilities = Arrays.asList("", "-", "+", "#", "~");
                String expected = selectExpected(0, info, visibilities);

                String actual = obj.extractVisibility();

                assertThat(actual).isEqualTo(expected);
            }

            @RepeatedTest(testCount)
            void 派生を返す(RepetitionInfo info) {
                List<Boolean> isDivided = Arrays.asList(false, true);
                String expected = "";
                if (10 < info.getCurrentRepetition() && isDivided.get((info.getCurrentRepetition() - 1) % isDivided.size())) expected = "/";
                else if (5 < info.getCurrentRepetition() && info.getCurrentRepetition() <= 10) expected = "/";

                String actual = obj.extractDivided();

                assertThat(actual).isEqualTo(expected);
            }

            @RepeatedTest(testCount)
            void 型を返す(RepetitionInfo info) {
                List<String> propType = Arrays.asList("bool", "boolean", "c", "char", "character", "byte", "s", "short", "i", "int", "integer", "l", "long", "f", "float", "lf", "double");
                String expected = "";
                if (10 < info.getCurrentRepetition()) expected = selectExpected(10, info, propType);

                String actual = obj.extractPropType();

                assertThat(actual).isEqualTo(expected);
            }

            private String selectExpected(int ignoreCount,RepetitionInfo info, List<String> candidates) {
                return candidates.get((info.getCurrentRepetition() - 1 - ignoreCount) % candidates.size());
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

        @Nested
        class 空文字を入力した場合 {

            @BeforeEach
            void setup() {
                walk("");
            }

            @Test
            void 名前を取得しようとするとエラーを返す () {
                assertThrows(IllegalArgumentException.class, () -> obj.extractClassName());
            }
        }

        @Nested
        class 型と同じ文字列を入力した場合 {

            @BeforeEach
            void setup() {
                walk("int");
            }

            @Test
            void 名前を取得しようとするとエラーを返す () {
                assertThrows(InputMismatchException.class, () -> obj.extractClassName());
            }
        }
    }

    private void walk(String text) {
        obj = new AttributeEvaluation();
        obj.setAttribute(text);
        obj.walk();
    }
}