package usage;

import net.java.quickcheck.Generator;
import org.antlr.v4.runtime.InputMismatchException;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static net.java.quickcheck.generator.PrimitiveGenerators.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
            void 文を設定すると文を返す() {
                String expected = "attribute";

                obj.setText(expected);
                String actual = obj.getText();

                assertThat(actual).isEqualTo(expected);
            }

            @Test
            void 文を設定して走査するとエラーを返さない() {
                String expected = "attribute";
                String actual;

                obj.setText("attribute");
                obj.walk();
                actual = obj.getText();

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

                String actual = obj.extractName();

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

                String actual = obj.extractName();

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

                String actual = obj.extractName();

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

                String actual = obj.extractName();

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

                String actual = obj.extractName();

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
        class 多重度を含む場合 {

            @Nested
            class 下限と上限が数値の場合 {

                final String attribute = "attribute [0..1]";

                @BeforeEach
                void setup() {
                    walk(attribute);
                }

                @Test
                void 多重度の下限を返す() {
                    String expected = "0";

                    String actual = obj.extractMultiplicityRangeLower();

                    assertThat(actual).isEqualTo(expected);
                }

                @Test
                void 多重度の上限を返す() {
                    String expected = "1";

                    String actual = obj.extractMultiplicityRangeUpper();

                    assertThat(actual).isEqualTo(expected);
                }
            }

            @Nested
            class 上限が数値の場合 {

                final String attribute = "attribute [1]";

                @BeforeEach
                void setup() {
                    walk(attribute);
                }

                @Test
                void 多重度の下限ではnullを返す() {

                    String actual = obj.extractMultiplicityRangeLower();

                    assertThat(actual).isNull();
                }

                @Test
                void 多重度の上限を返す() {
                    String expected = "1";

                    String actual = obj.extractMultiplicityRangeUpper();

                    assertThat(actual).isEqualTo(expected);
                }
            }

            @Nested
            class 下限と上限が文字列の場合 {

                final String attribute = "attribute [(this is zero)..(one)]";

                @BeforeEach
                void setup() {
                    walk(attribute);
                }

                @Test
                void 多重度の下限を返す() {
                    String expected = "(this is zero)";

                    String actual = obj.extractMultiplicityRangeLower();

                    assertThat(actual).isEqualTo(expected);
                }

                @Test
                void 多重度の上限を返す() {
                    String expected = "(one)";

                    String actual = obj.extractMultiplicityRangeUpper();

                    assertThat(actual).isEqualTo(expected);
                }
            }

            @Nested
            class 上限が文字列の場合 {

                final String attribute = "attribute [(this is more than 0, constant)]";

                @BeforeEach
                void setup() {
                    walk(attribute);
                }

                @Test
                void 多重度の下限ではnullを返す() {

                    String actual = obj.extractMultiplicityRangeLower();

                    assertThat(actual).isNull();
                }

                @Test
                void 多重度の上限を返す() {
                    String expected = "(this is more than 0, constant)";

                    String actual = obj.extractMultiplicityRangeUpper();

                    assertThat(actual).isEqualTo(expected);
                }
            }

            @Nested
            class 下限と上限が式の場合 {

                final String attribute = "attribute [(1+0)..(one+(twoMethod()+three))]";

                @BeforeEach
                void setup() {
                    walk(attribute);
                }

                @Test
                void 多重度の下限を返す() {
                    String expected = "(1 + 0)";

                    String actual = obj.extractMultiplicityRangeLower();

                    assertThat(actual).isEqualTo(expected);
                }

                @Test
                void 多重度の上限を返す() {
                    String expected = "(one + (twoMethod() + three))";

                    String actual = obj.extractMultiplicityRangeUpper();

                    assertThat(actual).isEqualTo(expected);
                }
            }

            @Nested
            class 上限が式の場合 {

                final String attribute = "attribute [(1/(5-4)%3, not is() and ! test AND number OR method(1,t) or NOT method(thisIs.ins,t), a < b == d && e >= g != h || j > k, m <= n)]";

                @BeforeEach
                void setup() {
                    walk(attribute);
                }

                @Test
                void 多重度の下限ではnullを返す() {

                    String actual = obj.extractMultiplicityRangeLower();

                    assertThat(actual).isNull();
                }

                @Test
                void 多重度の上限を返す() {
                    String expected = "(1 / (5 - 4) % 3, not is() and ! test AND number OR method(1,t) or NOT method(thisIs.ins,t), a < b == d && e >= g != h || j > k, m <= n)";

                    String actual = obj.extractMultiplicityRangeUpper();

                    assertThat(actual).isEqualTo(expected);
                }
            }
        }

        @Nested
        class 既定値を含む場合 {
            final String attribute = "numberFive = 5";

            @BeforeEach
            void setup() {
                walk(attribute);
            }

            @Test
            void 属性を返す() {
                String expected = "numberFive";

                String actual = obj.extractName();

                assertThat(actual).isEqualTo(expected);
            }

            @Test
            void 規定値を返す() {
                String expected = "5";

                String actual = obj.extractDefaultValue();

                assertThat(actual).isEqualTo(expected);
            }

            @Nested
            class 数値に関して {

                @Test
                void プラス付きの整数を返す() {
                    String expected = "+1";
                    String attribute = "integerWithPlus = +1";
                    walk(attribute);

                    String actual = obj.extractDefaultValue();

                    assertThat(actual).isEqualTo(expected);
                }

                @Test
                void 負の整数を返す() {
                    String expected = "-1";
                    String attribute = "negativeInteger = -1";
                    walk(attribute);

                    String actual = obj.extractDefaultValue();

                    assertThat(actual).isEqualTo(expected);
                }

                @Test
                void ゼロを返す() {
                    String expected = "0";
                    String attribute = "integerZero = 0";
                    walk(attribute);

                    String actual = obj.extractDefaultValue();

                    assertThat(actual).isEqualTo(expected);
                }

                @Test
                void プラス付きのゼロを返す() {
                    String expected = "+0";
                    String attribute = "integerZeroWithPlus = +0";
                    walk(attribute);

                    String actual = obj.extractDefaultValue();

                    assertThat(actual).isEqualTo(expected);
                }

                @Test
                void 負のゼロを返す() {
                    String expected = "-0";
                    String attribute = "negativeIntegerZero = -0";
                    walk(attribute);

                    String actual = obj.extractDefaultValue();

                    assertThat(actual).isEqualTo(expected);
                }

                @Test
                void 実数を返す() {
                    String expected = "1.0";
                    String attribute = "real = 1.0";
                    walk(attribute);

                    String actual = obj.extractDefaultValue();

                    assertThat(actual).isEqualTo(expected);
                }

                @Test
                void 後半省略形の実数を返す() {
                    String expected = "1.";
                    String attribute = "realOmittedFirstHalf = 1.";
                    walk(attribute);

                    String actual = obj.extractDefaultValue();

                    assertThat(actual).isEqualTo(expected);
                }

                @Test
                void 前半省略形の実数を返す() {
                    String expected = ".1";
                    String attribute = "realOmittedLastHalf = .1";
                    walk(attribute);

                    String actual = obj.extractDefaultValue();

                    assertThat(actual).isEqualTo(expected);
                }

                @Test
                void 実数ゼロを返す() {
                    String expected = "0.0";
                    String attribute = "realZero = 0.0";
                    walk(attribute);

                    String actual = obj.extractDefaultValue();

                    assertThat(actual).isEqualTo(expected);
                }

                @Test
                void プラス付きの実数を返す() {
                    String expected = "+1.0";
                    String attribute = "realWithPlus = +1.0";
                    walk(attribute);

                    String actual = obj.extractDefaultValue();

                    assertThat(actual).isEqualTo(expected);
                }

                @Test
                void プラス付きの後半省略形の実数を返す() {
                    String expected = "+1.";
                    String attribute = "realOmittedFirstHalfWithPlus = +1.";
                    walk(attribute);

                    String actual = obj.extractDefaultValue();

                    assertThat(actual).isEqualTo(expected);
                }

                @Test
                void プラス付きの前半省略形の実数を返す() {
                    String expected = "+.1";
                    String attribute = "realOmittedLastHalfWithPlus = +.1";
                    walk(attribute);

                    String actual = obj.extractDefaultValue();

                    assertThat(actual).isEqualTo(expected);
                }

                @Test
                void プラス付きの実数ゼロを返す() {
                    String expected = "+0.0";
                    String attribute = "realZeroWithPlus = +0.0";
                    walk(attribute);

                    String actual = obj.extractDefaultValue();

                    assertThat(actual).isEqualTo(expected);
                }

                @Test
                void 負の実数を返す() {
                    String expected = "-1.0";
                    String attribute = "negativeReal = -1.0";
                    walk(attribute);

                    String actual = obj.extractDefaultValue();

                    assertThat(actual).isEqualTo(expected);
                }

                @Test
                void 負の後半省略形の実数を返す() {
                    String expected = "-1.";
                    String attribute = "negativeRealOmittedFirstHalf = -1.";
                    walk(attribute);

                    String actual = obj.extractDefaultValue();

                    assertThat(actual).isEqualTo(expected);
                }

                @Test
                void 負の前半省略形の実数を返す() {
                    String expected = "-.1";
                    String attribute = "negativeRealOmittedLastHalf = -.1";
                    walk(attribute);

                    String actual = obj.extractDefaultValue();

                    assertThat(actual).isEqualTo(expected);
                }

                @Test
                void 負の実数ゼロを返す() {
                    String expected = "-0.0";
                    String attribute = "negativeRealZero = -0.0";
                    walk(attribute);

                    String actual = obj.extractDefaultValue();

                    assertThat(actual).isEqualTo(expected);
                }

                @Test
                void アンダースコアを含む整数を返す() {
                    String expected = "1_000";
                    String attribute = "integerWithinUnderscore = 1_000";
                    walk(attribute);

                    String actual = obj.extractDefaultValue();

                    assertThat(actual).isEqualTo(expected);
                }

                @Test
                void アンダースコアを複数含む整数を返す() {
                    String expected = "1_234_567";
                    String attribute = "integerWithinUnderscores = 1_234_567";
                    walk(attribute);

                    String actual = obj.extractDefaultValue();

                    assertThat(actual).isEqualTo(expected);
                }

                @Test
                void アンダースコアを連続で含む整数を返す() {
                    String expected = "1__________0";
                    String attribute = "integerWithinTenUnderscoresContinuously = 1__________0";
                    walk(attribute);

                    String actual = obj.extractDefaultValue();

                    assertThat(actual).isEqualTo(expected);
                }

                @Test
                void アンダースコアを含むプラス付きの整数を返す() {
                    String expected = "+1_000";
                    String attribute = "integerWithinUnderscoreWithPlus = +1_000";
                    walk(attribute);

                    String actual = obj.extractDefaultValue();

                    assertThat(actual).isEqualTo(expected);
                }

                @Test
                void アンダースコアを複数含むプラス付きの整数を返す() {
                    String expected = "+1_234_567";
                    String attribute = "integerWithinUnderscoresWithPlus = +1_234_567";
                    walk(attribute);

                    String actual = obj.extractDefaultValue();

                    assertThat(actual).isEqualTo(expected);
                }

                @Test
                void アンダースコアを連続で含むプラス付きの整数を返す() {
                    String expected = "+1__________0";
                    String attribute = "integerWithinTenUnderscoresContinuouslyWithPlus = +1__________0";
                    walk(attribute);

                    String actual = obj.extractDefaultValue();

                    assertThat(actual).isEqualTo(expected);
                }

                @Test
                void アンダースコアを含む負の整数を返す() {
                    String expected = "-1_000";
                    String attribute = "negativeIntegerWithinUnderscore = -1_000";
                    walk(attribute);

                    String actual = obj.extractDefaultValue();

                    assertThat(actual).isEqualTo(expected);
                }

                @Test
                void アンダースコアを複数含む負の整数を返す() {
                    String expected = "-1_234_567";
                    String attribute = "negativeIntegerWithinUnderscores = -1_234_567";
                    walk(attribute);

                    String actual = obj.extractDefaultValue();

                    assertThat(actual).isEqualTo(expected);
                }

                @Test
                void アンダースコアを連続で含む負の整数を返す() {
                    String expected = "-1__________0";
                    String attribute = "negativeIntegerWithinTenUnderscoresContinuously = -1__________0";
                    walk(attribute);

                    String actual = obj.extractDefaultValue();

                    assertThat(actual).isEqualTo(expected);
                }

                @Test
                void アンダースコアを含む実数を返す() {
                    String expected = "3.141_592";
                    String attribute = "realWithinUnderscore = 3.141_592";
                    walk(attribute);

                    String actual = obj.extractDefaultValue();

                    assertThat(actual).isEqualTo(expected);
                }

                @Test
                void アンダースコアを複数含む実数を返す() {
                    String expected = "1_234.567_890";
                    String attribute = "realWithinUnderscores = 1_234.567_890";
                    walk(attribute);

                    String actual = obj.extractDefaultValue();

                    assertThat(actual).isEqualTo(expected);
                }

                @Test
                void アンダースコアを連続で含む実数を返す() {
                    String expected = "1__________0.0__________1";
                    String attribute = "realWithinTenUnderscoresContinuously = 1__________0.0__________1";
                    walk(attribute);

                    String actual = obj.extractDefaultValue();

                    assertThat(actual).isEqualTo(expected);
                }

                @Test
                void アンダースコアを含むプラス付きの実数を返す() {
                    String expected = "+3.141_592";
                    String attribute = "realWithinUnderscoreWithPlus = +3.141_592";
                    walk(attribute);

                    String actual = obj.extractDefaultValue();

                    assertThat(actual).isEqualTo(expected);
                }

                @Test
                void アンダースコアを複数含むプラス付きの実数を返す() {
                    String expected = "+1_234.567_890";
                    String attribute = "realWithinUnderscoresWithPlus = +1_234.567_890";
                    walk(attribute);

                    String actual = obj.extractDefaultValue();

                    assertThat(actual).isEqualTo(expected);
                }

                @Test
                void アンダースコアを連続で含むプラス付きの実数を返す() {
                    String expected = "+1__________0.0__________1";
                    String attribute = "realWithinTenUnderscoresContinuouslyWithPlus = +1__________0.0__________1";
                    walk(attribute);

                    String actual = obj.extractDefaultValue();

                    assertThat(actual).isEqualTo(expected);
                }

                @Test
                void アンダースコアを含む負の実数を返す() {
                    String expected = "-1_000.";
                    String attribute = "negativeRealWithinUnderscore = -1_000.";
                    walk(attribute);

                    String actual = obj.extractDefaultValue();

                    assertThat(actual).isEqualTo(expected);
                }

                @Test
                void アンダースコアを複数含む負の実数を返す() {
                    String expected = "-1_0.0_1";
                    String attribute = "negativeIntegerWithinUnderscores = -1_0.0_1";
                    walk(attribute);

                    String actual = obj.extractDefaultValue();

                    assertThat(actual).isEqualTo(expected);
                }

                @Test
                void アンダースコアを連続で含む負の実数を返す() {
                    String expected = "-.0__________1";
                    String attribute = "negativeIntegerWithinTenUnderscoresContinuously = -.0__________1";
                    walk(attribute);

                    String actual = obj.extractDefaultValue();

                    assertThat(actual).isEqualTo(expected);
                }

                @Test
                void 二進数の数値を返す() {
                    String expected = "0b101";
                    String attribute = "binaryDigits = 0b101";
                    walk(attribute);

                    String actual = obj.extractDefaultValue();

                    assertThat(actual).isEqualTo(expected);
                }

                @Test
                void 二進数のプラスを含む数値を返す() {
                    String expected = "+0b101";
                    String attribute = "binaryDigitsWithPlus = +0b101";
                    walk(attribute);

                    String actual = obj.extractDefaultValue();

                    assertThat(actual).isEqualTo(expected);
                }

                @Test
                void 二進数の負の数値を返す() {
                    String expected = "-0b101";
                    String attribute = "negativeBinaryDigits = -0b101";
                    walk(attribute);

                    String actual = obj.extractDefaultValue();

                    assertThat(actual).isEqualTo(expected);
                }

                @Test
                void Java表記の八進数の数値を返す() {
                    String expected = "0101";
                    String attribute = "octalDigitsInJavaStyle = 0101";
                    walk(attribute);

                    String actual = obj.extractDefaultValue();

                    assertThat(actual).isEqualTo(expected);
                }

                @Test
                void Java表記の八進数のプラスを含む数値を返す() {
                    String expected = "+0101";
                    String attribute = "octalDigitsWithPlusInJavaStyle = +0101";
                    walk(attribute);

                    String actual = obj.extractDefaultValue();

                    assertThat(actual).isEqualTo(expected);
                }

                @Test
                void Java表記の八進数の負の数値を返す() {
                    String expected = "-0101";
                    String attribute = "negativeOctalDigitsInJavaStyle = -0101";
                    walk(attribute);

                    String actual = obj.extractDefaultValue();

                    assertThat(actual).isEqualTo(expected);
                }

                @Test
                void Python表記の八進数の数値を返す() {
                    String expected = "0o101";
                    String attribute = "octalDigitsInPythonStyle = 0o101";
                    walk(attribute);

                    String actual = obj.extractDefaultValue();

                    assertThat(actual).isEqualTo(expected);
                }

                @Test
                void Python表記の八進数のプラスを含む数値を返す() {
                    String expected = "+0o101";
                    String attribute = "octalDigitsWithPlusInPythonStyle = +0o101";
                    walk(attribute);

                    String actual = obj.extractDefaultValue();

                    assertThat(actual).isEqualTo(expected);
                }

                @Test
                void Python表記の八進数の負の数値を返す() {
                    String expected = "-0o101";
                    String attribute = "negativeOctalDigitsInPythonStyle = -0o101";
                    walk(attribute);

                    String actual = obj.extractDefaultValue();

                    assertThat(actual).isEqualTo(expected);
                }

                @Test
                void 十六進数の数値を返す() {
                    String expected = "0x101";
                    String attribute = "binaryDigits = 0x101";
                    walk(attribute);

                    String actual = obj.extractDefaultValue();

                    assertThat(actual).isEqualTo(expected);
                }

                @Test
                void 十六進数のプラスを含む数値を返す() {
                    String expected = "+0x101";
                    String attribute = "binaryDigitsWithPlus = +0x101";
                    walk(attribute);

                    String actual = obj.extractDefaultValue();

                    assertThat(actual).isEqualTo(expected);
                }

                @Test
                void 十六進数の負の数値を返す() {
                    String expected = "-0x101";
                    String attribute = "negativeBinaryDigits = -0x101";
                    walk(attribute);

                    String actual = obj.extractDefaultValue();

                    assertThat(actual).isEqualTo(expected);
                }
            }

            @Nested
            class 文字列に関して {
                final List<String> quotation = Arrays.asList("'", "\"");

                @RepeatedTest(2)
                void 文字を返す(RepetitionInfo info) {
                    String expected = surroundedQuotation("c", info);
                    String attribute = "characterSurroundedQuotation = " + expected;
                    walk(attribute);

                    String actual = obj.extractDefaultValue();

                    assertThat(actual).isEqualTo(expected);
                }

                @RepeatedTest(2)
                void 文字列を返す(RepetitionInfo info) {
                    String expected = surroundedQuotation("string", info);
                    String attribute = "stringSurroundedQuotation = " + expected;
                    walk(attribute);

                    String actual = obj.extractDefaultValue();

                    assertThat(actual).isEqualTo(expected);
                }

                @RepeatedTest(2)
                void 空文字を返す(RepetitionInfo info) {
                    String expected = surroundedQuotation(" ", info);
                    String attribute = "nullCharacterSurroundedQuotation = " + expected;
                    walk(attribute);

                    String actual = obj.extractDefaultValue();

                    assertThat(actual).isEqualTo(expected);
                }

                @RepeatedTest(2)
                void 空文字列を返す(RepetitionInfo info) {
                    String expected = surroundedQuotation("   ", info);
                    String attribute = "threeNullsStringSurroundedQuotation = " + expected;
                    walk(attribute);

                    String actual = obj.extractDefaultValue();

                    assertThat(actual).isEqualTo(expected);
                }

                @RepeatedTest(2)
                void 空文字を含む文字列を返す(RepetitionInfo info) {
                    String expected = surroundedQuotation("string with null character", info);
                    String attribute = "stringWithNullCharacterSurroundedQuotation = " + expected;
                    walk(attribute);

                    String actual = obj.extractDefaultValue();

                    assertThat(actual).isEqualTo(expected);
                }

                @RepeatedTest(2)
                void 最初に空文字を含む文字列を返す(RepetitionInfo info) {
                    String expected = surroundedQuotation(" string started null character", info);
                    String attribute = "stringStartedNullCharacterSurroundedQuotation = " + expected;
                    walk(attribute);

                    String actual = obj.extractDefaultValue();

                    assertThat(actual).isEqualTo(expected);
                }

                @RepeatedTest(2)
                void 最後に空文字を含む文字列を返す(RepetitionInfo info) {
                    String expected = surroundedQuotation("string lasted null character ", info);
                    String attribute = "stringLastedNullCharacterSurroundedQuotation = " + expected;
                    walk(attribute);

                    String actual = obj.extractDefaultValue();

                    assertThat(actual).isEqualTo(expected);
                }

                @RepeatedTest(2)
                void 円マークを返す(RepetitionInfo info) {
                    String expected = surroundedQuotation("\\\\", info);
                    String attribute = "yenCharacterSurroundedQuotation = " + expected;
                    walk(attribute);

                    String actual = obj.extractDefaultValue();

                    assertThat(actual).isEqualTo(expected);
                }

                @RepeatedTest(2)
                void シングルクオーテーションを返す(RepetitionInfo info) {
                    String expected = surroundedQuotation("\\\'", info);
                    String attribute = "singleQuotationSurroundedQuotation = " + expected;
                    walk(attribute);

                    String actual = obj.extractDefaultValue();

                    assertThat(actual).isEqualTo(expected);
                }

                @RepeatedTest(2)
                void ダブルクオーテーションを返す(RepetitionInfo info) {
                    String expected = surroundedQuotation("\\\"", info);
                    String attribute = "doubleQuotationSurroundedQuotation = " + expected;
                    walk(attribute);

                    String actual = obj.extractDefaultValue();

                    assertThat(actual).isEqualTo(expected);
                }

                @RepeatedTest(2)
                void シングルクオーテーションを含む文字列を返す(RepetitionInfo info) {
                    String expected = surroundedQuotation("I\\\'m so happy.", info);
                    String attribute = "stringWithSingleQuotationSurroundedQuotation = " + expected;
                    walk(attribute);

                    String actual = obj.extractDefaultValue();

                    assertThat(actual).isEqualTo(expected);
                }

                @RepeatedTest(2)
                void ダブルクオーテーションを含む文字列を返す(RepetitionInfo info) {
                    String expected = surroundedQuotation("I watched \\\"Rain Man,\\\" and it was interesting.", info);
                    String attribute = "doubleQuotationSurroundedQuotation = " + expected;
                    walk(attribute);

                    String actual = obj.extractDefaultValue();

                    assertThat(actual).isEqualTo(expected);
                }

                @RepeatedTest(2)
                void 様々な記号を含む文字列を返す(RepetitionInfo info) {
                    String expected = surroundedQuotation("!#$%&()=~|-^{}[]`+*<>?_@;:,./", info);
                    String attribute = "variousSymbolSurroundedQuotation = " + expected;
                    walk(attribute);

                    String actual = obj.extractDefaultValue();

                    assertThat(actual).isEqualTo(expected);
                }

                @RepeatedTest(2)
                void 様々なエスケープシーケンスを含む文字列を返す(RepetitionInfo info) {
                    String expected = surroundedQuotation("\\0\\a\\b\\t\\n\\v\\f\\r\\s\\\"\\\'\\\\", info);
                    String attribute = "variousEscapeSequencesSurroundedQuotation = " + expected;
                    walk(attribute);

                    String actual = obj.extractDefaultValue();

                    assertThat(actual).isEqualTo(expected);
                }

                private String surroundedQuotation(String surroundedText, RepetitionInfo info) {
                    return quotation.get(info.getCurrentRepetition() - 1) + surroundedText + quotation.get(info.getCurrentRepetition() - 1);
                }
            }

            @Nested
            class 真偽値に関して {

                @Test
                void 真を返す() {
                    String expected = "true";
                    String attribute = "isTrue = true";
                    walk(attribute);

                    String actual = obj.extractDefaultValue();

                    assertThat(actual).isEqualTo(expected);
                }

                @Test
                void 偽を返す() {
                    String expected = "false";
                    String attribute = "isFalse = false";
                    walk(attribute);

                    String actual = obj.extractDefaultValue();

                    assertThat(actual).isEqualTo(expected);
                }
            }

            @Nested
            class 未定義値に関して {

                @Test
                void nullを返す() {
                    String expected = "null";
                    String attribute = "nullValue = null";
                    walk(attribute);

                    String actual = obj.extractDefaultValue();

                    assertThat(actual).isEqualTo(expected);
                }

                @Test
                void nulを返す() {
                    String expected = "nul";
                    String attribute = "nulValue = nul";
                    walk(attribute);

                    String actual = obj.extractDefaultValue();

                    assertThat(actual).isEqualTo(expected);
                }

                @Test
                void nilを返す() {
                    String expected = "nil";
                    String attribute = "nilValue = nil";
                    walk(attribute);

                    String actual = obj.extractDefaultValue();

                    assertThat(actual).isEqualTo(expected);
                }

                @Test
                void noneを返す() {
                    String expected = "none";
                    String attribute = "noneValue = none";
                    walk(attribute);

                    String actual = obj.extractDefaultValue();

                    assertThat(actual).isEqualTo(expected);
                }

                @Test
                void undefを返す() {
                    String expected = "undef";
                    String attribute = "undefValue = undef";
                    walk(attribute);

                    String actual = obj.extractDefaultValue();

                    assertThat(actual).isEqualTo(expected);
                }
            }

            @Nested
            class 式に関して {

                @Test
                void 加算を返す() {
                    String expected = "1 + 2";
                    String attribute = "onePlusTwo = 1 + 2";
                    walk(attribute);

                    String actual = obj.extractDefaultValue();

                    assertThat(actual).isEqualTo(expected);
                }

                @Test
                void 減算を返す() {
                    String expected = "1 - 2";
                    String attribute = "oneMinusTwo = 1 - 2";
                    walk(attribute);

                    String actual = obj.extractDefaultValue();

                    assertThat(actual).isEqualTo(expected);
                }

                @Test
                void 乗算を返す() {
                    String expected = "1 * 2";
                    String attribute = "oneMultipliedByTwo = 1 * 2";
                    walk(attribute);

                    String actual = obj.extractDefaultValue();

                    assertThat(actual).isEqualTo(expected);
                }

                @Test
                void 除算を返す() {
                    String expected = "1 / 2";
                    String attribute = "oneDividedByTwo = 1 / 2";
                    walk(attribute);

                    String actual = obj.extractDefaultValue();

                    assertThat(actual).isEqualTo(expected);
                }

                @Test
                void 余りを返す() {
                    String expected = "1 % 2";
                    String attribute = "remainderOfOneDividedByTwo = 1 % 2";
                    walk(attribute);

                    String actual = obj.extractDefaultValue();

                    assertThat(actual).isEqualTo(expected);
                }

                @Test
                void 括弧付きの式を返す() {
                    String expected = "1 / (2 * ((3 + 4) - 5))";
                    String attribute = "remainderOfOneDividedByTwo = 1 / (2 * ((3 + 4) - 5))";
                    walk(attribute);

                    String actual = obj.extractDefaultValue();

                    assertThat(actual).isEqualTo(expected);
                }

                @Test
                void インスタンス生成文を返す() {
                    String expected = "new ClassName()";
                    String attribute = "classInstance = new ClassName()";
                    walk(attribute);

                    String actual = obj.extractDefaultValue();

                    assertThat(actual).isEqualTo(expected);
                }

                @Test
                void クラスメソッドを用いたインスタンス生成文を返す() {
                    String expected = "new ClassName.getNewInstance()";
                    String attribute = "classInstance = new ClassName.getNewInstance()";
                    walk(attribute);

                    String actual = obj.extractDefaultValue();

                    assertThat(actual).isEqualTo(expected);
                }

                @Test
                void 引数を持つクラスメソッドを用いたインスタンス生成文を返す() {
                    String expected = "new ClassName.getNewInstance(arg1,arg2)";
                    String attribute = "classInstance = new ClassName.getNewInstance(arg1, arg2)";
                    walk(attribute);

                    String actual = obj.extractDefaultValue();

                    assertThat(actual).isEqualTo(expected);
                }

                @Test
                void クラスメソッド文を返す() {
                    String expected = "ClassName.staticClassMethod()";
                    String attribute = "classInstance = ClassName.staticClassMethod()";
                    walk(attribute);

                    String actual = obj.extractDefaultValue();

                    assertThat(actual).isEqualTo(expected);
                }

                @Test
                void 連続したクラスメソッド文を返す() {
                    String expected = "ClassName.staticClassMethod().getInstance()";
                    String attribute = "classInstance = ClassName.staticClassMethod().getInstance()";
                    walk(attribute);

                    String actual = obj.extractDefaultValue();

                    assertThat(actual).isEqualTo(expected);
                }

                @Test
                void 引数を持つクラスメソッド文を返す() {
                    String expected = "ClassName.staticClassMethod(ClassName.method(),instance.method())";
                    String attribute = "classInstance = ClassName.staticClassMethod(ClassName.method(),instance.method())";
                    walk(attribute);

                    String actual = obj.extractDefaultValue();

                    assertThat(actual).isEqualTo(expected);
                }

                @Test
                void 連続した引数を持つクラスメソッド文を返す() {
                    String expected = "ClassName.staticClassMethod(ClassName.method(),instance.method()).getInstance(arg1,arg2,arg3)";
                    String attribute = "classInstance = ClassName.staticClassMethod(ClassName.method(),instance.method()).getInstance(arg1,arg2,arg3)";
                    walk(attribute);

                    String actual = obj.extractDefaultValue();

                    assertThat(actual).isEqualTo(expected);
                }

                @Test
                void インスタンスを返す() {
                    String expected = "instance";
                    String attribute = "classInstance = instance";
                    walk(attribute);

                    String actual = obj.extractDefaultValue();

                    assertThat(actual).isEqualTo(expected);
                }

                @Test
                void インスタンスのインスタンスを返す() {
                    String expected = "instance.hasInstance";
                    String attribute = "classInstance = instance.hasInstance";
                    walk(attribute);

                    String actual = obj.extractDefaultValue();

                    assertThat(actual).isEqualTo(expected);
                }

                @Test
                void インスタンスのメソッドを返す() {
                    String expected = "instance.hasMethod()";
                    String attribute = "classInstance = instance.hasMethod()";
                    walk(attribute);

                    String actual = obj.extractDefaultValue();

                    assertThat(actual).isEqualTo(expected);
                }

                @Test
                void メソッドのインスタンスを返す() {
                    String expected = "method().hasInstance";
                    String attribute = "classInstance = method().hasInstance";
                    walk(attribute);

                    String actual = obj.extractDefaultValue();

                    assertThat(actual).isEqualTo(expected);
                }
            }
        }

        @Nested
        class 修飾子を含む場合 {

            @Nested
            class 単純なプロパティが1つであれば {

                final String attribute = "attribute {readOnly}";

                @BeforeEach
                void setup() {
                    walk(attribute);
                }

                @Test
                void プロパティを返す() {
                    String expected = "readOnly";

                    String actual = obj.extractPropModifier();

                    assertThat(actual).isEqualTo(expected);
                }
            }

            @Nested
            class 単純なプロパティが複数であれば {

                final String attribute = "attribute {readOnly, union, ordered, unique}";

                @BeforeEach
                void setup() {
                    walk(attribute);
                }

                @Test
                void プロパティを返す() {
                    String expected = "readOnly, union, ordered, unique";

                    String actual = obj.extractPropModifier();

                    assertThat(actual).isEqualTo(expected);
                }
            }

            @Nested
            class プロパティ名を持つプロパティが1つであれば {

                final String attribute = "attribute {subsets instance}";

                @BeforeEach
                void setup() {
                    walk(attribute);
                }

                @Test
                void プロパティを返す() {
                    String expected = "subsets instance";

                    String actual = obj.extractPropModifier();

                    assertThat(actual).isEqualTo(expected);
                }
            }

            @Nested
            class プロパティ名を持つプロパティが複数であれば {

                final String attribute = "attribute {subsets instance.method(), redefines method().instance}";

                @BeforeEach
                void setup() {
                    walk(attribute);
                }

                @Test
                void プロパティを返す() {
                    String expected = "subsets instance.method(), redefines method().instance";

                    String actual = obj.extractPropModifier();

                    assertThat(actual).isEqualTo(expected);
                }
            }
        }

        @Nested
        class ランダムテストの場合 {
            final Generator<String> textGenerator = letterStrings();
            final Generator<String> visibilityGenerator = fixedValues("", "-", "+", "#", "~");
            final Generator<Boolean> genuinenessGenerator = booleans();
            final Generator<String> propTypeGenerator = fixedValues("bool", "boolean", "c", "char", "character", "byte", "s", "short", "i", "int", "integer", "l", "long", "f", "float", "lf", "double");
            final Generator<DefaultValueType> defaultValueGenerator = fixedValues(DefaultValueType.Numeric, DefaultValueType.Text, DefaultValueType.Genuineness, DefaultValueType.Undefined, DefaultValueType.Expression);
            final Generator<Integer> methodArgsSizeGenerator = integers(1, 5);
            final Generator<Integer> instanceSizeGenerator = integers(1, 5);
            final Generator<Integer> integerGenerator = integers();
            final Generator<Double> doubleGenerator = doubles();
            final Generator<String> genuinenessTextGenerator = fixedValues("true", "True", "TRUE", "false", "False", "FALSE", "1", "0");
            final Generator<String> nullTextGenerator = fixedValues("null", "NULL", "Null", "nul", "NUL", "Nul", "nil", "NIL", "Nil", "none", "NONE", "None", "undef", "UNDEF", "Undef");
            final Generator<Integer> multiplicityRangeSizeGenerator = integers(1, 5);
            final Generator<Integer> propModifiersSizeGenerator = integers(1, 5);
            final Generator<String> simplePropModifierGenerator = fixedValues("readOnly", "union", "ordered", "unique");

            final List<String> keywords = Arrays.asList("and", "AND", "or", "OR", "not", "NOT", "new",
                    "bool", "boolean", "c", "char", "character", "byte", "s", "short", "i", "int", "integer", "l", "long", "f", "float", "lf", "double",
                    "true", "True", "TRUE", "false", "False", "FALSE",
                    "null", "NULL", "Null", "nul", "NUL", "Nul", "nil", "NIL", "Nil", "none", "NONE", "None", "undef", "UNDEF", "Undef");

            String name;

            @BeforeEach
            void selectName() {
                name = selectNameOfLengthZeroWithoutKeyword();
            }

            @RepeatedTest(100)
            void 名前を返す() {
                String expected = selectNameOfLengthZeroWithoutKeyword();
                walk(expected);

                try {
                    String actual = obj.extractName();
                    assertThat(actual)
                            .as("InputMismatchException : input '"+expected+"'")
                            .isEqualTo(expected);

                } catch (InputMismatchException e) {
                    System.out.println("InputMismatchException : input '"+expected+"'");
                }
            }

            @RepeatedTest(100)
            void 可視性を返す() {
                String visibility = visibilityGenerator.next();
                String attribute = visibility + " " + name;
                walk(attribute);

                String actual = obj.extractVisibility();

                if (visibility.length() <= 0) assertThat(actual).as("InputMismatchException : input '"+attribute+"'").isNull();
                else assertThat(actual).as("InputMismatchException : input '"+attribute+"'").isEqualTo(visibility);
            }

            @RepeatedTest(100)
            void 派生を返す() {
                String visibility = visibilityGenerator.next();
                boolean isDivided = genuinenessGenerator.next();
                String divided = isDivided ? "/" : "";
                String attribute = visibility + " " + divided + name;

                walk(attribute);
                String actual = obj.extractDivided();

                if (isDivided) assertThat(actual).as("InputMismatchException : input '"+attribute+"'").isEqualTo(divided);
                else assertThat(actual).as("InputMismatchException : input '"+attribute+"'").isNull();
            }

            @RepeatedTest(100)
            void 型を返す() {
                String visibility = visibilityGenerator.next();
                String propType = propTypeGenerator.next();
                String attribute = visibility + " " + name + " : " + propType;

                walk(attribute);

                try {
                    String actual = obj.extractPropType();
                    assertThat(actual)
                            .as("InputMismatchException : input '"+attribute+"'")
                            .isEqualTo(propType);

                } catch (InputMismatchException e) {
                    System.out.println("InputMismatchException : input '"+attribute+"'");
                }
            }

            @RepeatedTest(100)
            void 多重度を返す() {
                String visibility = visibilityGenerator.next();
                String multiplicityRange = createMultiplicityRange();
                String attribute = visibility + " " + name + " [" + multiplicityRange + "]";

                walk(attribute);

                try {
                    String lower = obj.extractMultiplicityRangeLower();
                    String upper = obj.extractMultiplicityRangeUpper();
                    String actual = upper;
                    if (lower != null) {
                        actual = lower + ".." + upper;
                    }
                    assertThat(actual)
                            .as("InputMismatchException : input '"+attribute+"'")
                            .isEqualTo(multiplicityRange);

                } catch (InputMismatchException e) {
                    System.out.println("InputMismatchException : input '"+attribute+"'");
                }
            }

            @RepeatedTest(100)
            void 既定値を返す() {
                String visibility = visibilityGenerator.next();
                String defaultValue = createDefaultValue();
                String attribute = visibility + " " + name + " = " + defaultValue;

                walk(attribute);

                try {
                    String actual = obj.extractDefaultValue();
                    assertThat(actual)
                            .as("InputMismatchException : input '"+attribute+"'")
                            .isEqualTo(defaultValue);

                } catch (InputMismatchException e) {
                    System.out.println("InputMismatchException : input '"+attribute+"'");
                }
            }

            @RepeatedTest(100)
            void 修飾子を返す() {
                String visibility = visibilityGenerator.next();
                String propModifiers = createPropModifiers();
                String attribute = visibility + " " + name + " {" + propModifiers + "}";

                walk(attribute);

                try {
                    String actual = obj.extractPropModifier();
                    assertThat(actual)
                            .as("InputMismatchException : input '"+attribute+"'")
                            .isEqualTo(propModifiers);

                } catch (InputMismatchException e) {
                    System.out.println("InputMismatchException : input '"+attribute+"'");
                }
            }

            @RepeatedTest(1000)
            void それぞれの要素を全て返す() {
                String visibility = visibilityGenerator.next();
                boolean isDivided = genuinenessGenerator.next();
                String divided = isDivided ? "/" : "";
                String propType = propTypeGenerator.next();
                String multiplicityRange = createMultiplicityRange();
                String defaultValue = createDefaultValue();
                String propModifiers = createPropModifiers();

                String attribute = visibility + " " + divided + name + " : " + propType + " [" + multiplicityRange + "] = " + defaultValue + " {" + propModifiers + "}";
                walk(attribute);

                try {
                    String actualName = obj.extractName();
                    String actualDivided = obj.extractDivided();
                    String actualVisibility = obj.extractVisibility();
                    String actualPropType = obj.extractPropType();
                    String lower = obj.extractMultiplicityRangeLower();
                    String upper = obj.extractMultiplicityRangeUpper();
                    String actualMultiplicityRange = lower == null
                            ? upper
                            : lower + ".." + upper;
                    String actualDefaultValue = obj.extractDefaultValue();
                    String actualPropModifiers = obj.extractPropModifier();

                    assertAll("InputMismatchException : input '"+attribute+"'",
                            () -> assertThat(actualName).isEqualTo(name),
                            () -> {
                                    if (visibility.length() <= 0) assertThat(actualVisibility).isNull();
                                    else assertThat(actualVisibility).isEqualTo(visibility);
                                },
                            () -> {
                                    if (isDivided) assertThat(actualDivided).isEqualTo(divided);
                                    else assertThat(actualDivided).isNull();
                                },
                            () -> assertThat(actualPropType).isEqualTo(propType),
                            () -> assertThat(actualMultiplicityRange).isEqualTo(multiplicityRange),
                            () -> assertThat(actualDefaultValue).isEqualTo(defaultValue),
                            () -> assertThat(actualPropModifiers).isEqualTo(propModifiers)
                            );

                } catch (InputMismatchException e) {
                    System.out.println("InputMismatchException : input '"+attribute+"'");
                }
            }



            private String selectNameOfLengthZeroWithoutKeyword() {
                String text;

                do {
                    text = textGenerator.next();
                } while (text.length() <= 0 || keywords.contains(text));

                return text;
            }

            private int selectNumberNotLessThan(int lessNumber) {
                int number;

                do {
                    number = integerGenerator.next();
                } while (number <= lessNumber);

                return number;
            }

            private String createDefaultValue() {
                String text = "";
                DefaultValueType type = defaultValueGenerator.next();

                if (type == DefaultValueType.Numeric) {
                    boolean isInteger = genuinenessGenerator.next();
                    boolean isHadSign = genuinenessGenerator.next();
                    if (isHadSign) text = "+";

                    if (isInteger) {
                        int number = integerGenerator.next();
                        text += Integer.toString(number);
                    } else {
                        double number = doubleGenerator.next();
                        text += Double.toString(number);
                    }

                } else if (type == DefaultValueType.Text) {
                    boolean isSingleQuotation = genuinenessGenerator.next();
                    if (isSingleQuotation) {
                        text = "'" + selectNameOfLengthZeroWithoutKeyword() + "'";
                    } else {
                        text = "\"" + selectNameOfLengthZeroWithoutKeyword() + "\"";
                    }

                } else if (type == DefaultValueType.Genuineness) {
                    text = genuinenessTextGenerator.next();

                } else if (type == DefaultValueType.Undefined) {
                    text = nullTextGenerator.next();

                } else if (type == DefaultValueType.Expression) {
                    text = createInstance(instanceSizeGenerator.next(), methodArgsSizeGenerator.next());
                }

                return text;
            }

            private String createPropModifiers() {
                List<String> modifiers = new ArrayList<>();
                int modifiersSize = propModifiersSizeGenerator.next();

                for (int i = 0; i < modifiersSize; i++) {
                    modifiers.add(createPropModifier());
                }

                return String.join(", ", modifiers);
            }

            private String createPropModifier() {
                String text;

                if (genuinenessGenerator.next()) {
                    text = genuinenessGenerator.next()
                            ? "subsets " + createExpression(1)
                            : "redefines " + createExpression(1);

                } else {
                    text = simplePropModifierGenerator.next();
                }

                return text;
            }

            private String createInstance(int instanceSize, int argsSize) {
                String text = selectNameOfLengthZeroWithoutKeyword();
                boolean isMethod = genuinenessGenerator.next();

                if (isMethod) {
                    if (0 < argsSize) {
                        text += "(" + createInstance(instanceSize - 1, methodArgsSizeGenerator.next()) + ")";
                    } else {
                        text += "()";
                    }
                }

                if (0 < instanceSize) {
                    text += "." + createInstance(instanceSize - 1, methodArgsSizeGenerator.next());
                }

                return text;
            }

            private String createMultiplicityRange() {
                String text = "";
                boolean isHadLower = genuinenessGenerator.next();
                boolean isValueSpecificationByLower = genuinenessGenerator.next();
                boolean isValueSpecificationByUpper = genuinenessGenerator.next();
                boolean isUnlimitedByUpper = genuinenessGenerator.next();
                int lowerNumber = 1;

                if (isHadLower) {
                    if (isValueSpecificationByLower) {
                        text = "(" + createMultiplicityRangeExpression(multiplicityRangeSizeGenerator.next()) + ")..";
                    } else {
                        lowerNumber = selectNumberNotLessThan(0);
                        text = Integer.toString(lowerNumber) + "..";
                    }
                }

                if (isValueSpecificationByUpper) {
                    text += "(" + createMultiplicityRangeExpression(multiplicityRangeSizeGenerator.next()) + ")";
                } else if (isUnlimitedByUpper) {
                    text += "*";
                } else {
                    text += Integer.toString(selectNumberNotLessThan(lowerNumber));
                }

                return text;
            }

            private String createMultiplicityRangeExpression(int expressionSize) {

                List<String> expressions = new ArrayList<>();
                for (int i = 0; i < expressionSize; i++) {
                    expressions.add(createExpression(multiplicityRangeSizeGenerator.next()));
                }

                return String.join(", ", expressions);
            }

            private String createExpression(int size) {
                List<String> expression = new ArrayList<>();

                for (int j = 0; j < size; j++) {
                    if (genuinenessGenerator.next()) {
                        expression.add(selectNameOfLengthZeroWithoutKeyword());
                    } else if (genuinenessGenerator.next()) {
                        expression.add(createInstance(instanceSizeGenerator.next(), methodArgsSizeGenerator.next()));
                    } else {
                        expression.add(Integer.toString(selectNumberNotLessThan(0)));
                    }
                }

                return String.join(" ", expression);
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
                    "- attribute : double",
                    "+ /attribute : bool = True",
                    "# attribute : boolean = False",
                    "~ /attribute : c = 'c'",

                    "attribute : char = \"c\"",
                    "- /attribute : character = 'c'",
                    "+ attribute : byte = 0x01",
                    "# /attribute : s = 0",
                    "~ attribute : short = -1",
                    "/attribute : i = -0",
                    "- attribute : int = +9",
                    "+ /attribute : integer = 32767",
                    "# attribute : l = +0",
                    "~ /attribute : long = 9223372036854775807",

                    "attribute : f = 0.0",
                    "- /attribute : float = +0.",
                    "+ attribute : lf = -.0",
                    "# /attribute : double = 3.14159265358979"
            );

            final int testCount = 44;

            @BeforeEach
            void setup(RepetitionInfo info) {
                walk(attributes.get(info.getCurrentRepetition() - 1));
            }

            @RepeatedTest(testCount)
            void 名前を返す() {
                String expected = "attribute";

                String actual = obj.extractName();

                assertThat(actual).isEqualTo(expected);
            }

            @RepeatedTest(testCount)
            void 属性を返す(RepetitionInfo info) {
                List<String> visibilities = Arrays.asList(null, "-", "+", "#", "~");
                String expected = selectExpected(0, info, visibilities);

                String actual = obj.extractVisibility();

                assertThat(actual).isEqualTo(expected);
            }

            @RepeatedTest(testCount)
            void 派生を返す(RepetitionInfo info) {
                List<Boolean> isDivided = Arrays.asList(false, true);
                String expected = null;
                if (10 < info.getCurrentRepetition() && isDivided.get((info.getCurrentRepetition() - 1) % isDivided.size())) expected = "/";
                else if (5 < info.getCurrentRepetition() && info.getCurrentRepetition() <= 10) expected = "/";

                String actual = obj.extractDivided();

                assertThat(actual).isEqualTo(expected);
            }

            @RepeatedTest(testCount)
            void 型を返す(RepetitionInfo info) {
                List<String> propType = Arrays.asList("bool", "boolean", "c", "char", "character", "byte", "s", "short", "i", "int", "integer", "l", "long", "f", "float", "lf", "double");
                String expected = null;
                if (10 < info.getCurrentRepetition()) expected = selectExpected(10, info, propType);

                String actual = obj.extractPropType();

                assertThat(actual).isEqualTo(expected);
            }

            @RepeatedTest(testCount)
            void 既定値を返す(RepetitionInfo info) {
                List<String> defaultValue = Arrays.asList("True", "False", "'c'", "\"c\"", "'c'", "0x01", "0", "-1", "-0", "+9", "32767", "+0", "9223372036854775807", "0.0", "+0.", "-.0", "3.14159265358979");
                String expected = null;
                if (27 < info.getCurrentRepetition()) expected = selectExpected(27, info, defaultValue);

                String actual = obj.extractDefaultValue();

                assertThat(actual).isEqualTo(expected);
            }

            private String selectExpected(int ignoreCount, RepetitionInfo info, List<String> candidates) {
                return candidates.get((info.getCurrentRepetition() - 1 - ignoreCount) % candidates.size());
            }
        }
    }

    @Nested
    class 正しくない文法を入力する際に {

        @Nested
        class 文を入力していない場合 {

            @Test
            void 走査したらエラーを返す() {
                obj = new AttributeEvaluation();

                assertThatThrownBy(() -> obj.walk()).isInstanceOf(IllegalArgumentException.class);
            }

            @Test
            void 文を取得しようとしたらnullを返す() {
                obj = new AttributeEvaluation();

                String actual = obj.getText();

                assertThat(actual).isNull();
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
                    walk("not : float");

                    assertThatThrownBy(() -> obj.extractName()).isInstanceOf(InputMismatchException.class);
                }
            }

            @Nested
            class 可視性の場合 {

                @Test
                void 予約語と同じ文字列を名前に入力してもエラーは返さない() {
                    walk("- int");

                    assertThat(obj.extractVisibility()).isEqualTo("-");
                }
            }

            @Nested
            class 派生の場合 {

                @Test
                void 予約語と同じ文字列を名前に入力してもエラーは返さない() {
                    walk("/int");

                    assertThat(obj.extractDivided()).isEqualTo("/");
                }
            }

            @Nested
            class 型の場合 {

                @Test
                void 予約語と同じ文字列を名前に入力するとエラーを返す() {
                    walk("int : float");

                    assertThatThrownBy(() -> obj.extractPropType()).isInstanceOf(InputMismatchException.class);
                }
            }

            @Nested
            class 多重度の場合 {

                @Test
                void 予約語と同じ文字列を名前に入力すると下限でエラーを返す() {
                    SoftAssertions softly = new SoftAssertions();

                    walk("double [0..1]");

                    softly.assertThatThrownBy(() -> obj.extractMultiplicityRangeUpper()).isInstanceOf(InputMismatchException.class);
                    assertThatThrownBy(() -> obj.extractMultiplicityRangeLower()).isInstanceOf(InputMismatchException.class);
                }

                @Test
                void 予約語と同じ文字列を名前に入力すると上限でエラーを返す() {
                    walk("double [*]");

                    assertThatThrownBy(() -> obj.extractMultiplicityRangeUpper()).isInstanceOf(InputMismatchException.class);
                }
            }

            @Nested
            class 既定値の場合 {

                @Test
                void ドットのみを入力するとドットを返す() {
                    walk("dotOnly = .");

                    String actual = obj.extractDefaultValue();

                    assertThat(actual).isEqualTo(".");
                }

                @Test
                void ドットを複数持つ数値を入力すると2つ目のドットの前の数値の文字列を返す() {
                    walk("valueWithDots = 12.34.56");

                    String actual = obj.extractDefaultValue();

                    assertThat(actual).isEqualTo("12.34");
                }

                @Test
                void カンマを含む整数を入力するとカンマの前の数値の文字列を返す() {
                    walk("integerWithComma = 123,456");

                    String actual = obj.extractDefaultValue();

                    assertThat(actual).isEqualTo("123");
                }

                @Test
                void アンダースコアを最後に含む数値を入力するとアンダースコアの前の数値の文字列を返す() {
                    walk("valueStartedUnderscore = 123_");

                    String actual = obj.extractDefaultValue();

                    assertThat(actual).isEqualTo("123");
                }

                @Test
                void アンダースコアを最後に含む複数のアンダースコアを含んだ数値を入力すると最後のアンダースコアの前の数値の文字列を返す() {
                    walk("valueLastedUnderscore = 123_456_");

                    String actual = obj.extractDefaultValue();

                    assertThat(actual).isEqualTo("123_456");
                }

                @Test
                void アンダースコアをドットの前に含む実数値を入力するとアンダースコアの前の数値の文字列を返す() {
                    walk("realWithUnderscoreBeforeDot = 3_.14");

                    String actual = obj.extractDefaultValue();

                    assertThat(actual).isEqualTo("3");
                }

                @Test
                void アンダースコアをドットの後に含む実数値を入力するとアンダースコアの前の数値の文字列を返す() {
                    walk("realWithUnderscoreAfterDot = 3._14");

                    String actual = obj.extractDefaultValue();

                    assertThat(actual).isEqualTo("3.");
                }

                @Test
                void 予約語と同じ文字列を名前に入力するとエラーを返す() {
                    walk("int = 1");

                    assertThatThrownBy(() -> obj.extractDefaultValue()).isInstanceOf(InputMismatchException.class);
                }
            }

            @Nested
            class 修飾子の場合 {

                @Test
                void 予約語と同じ文字列を名前に入力するとエラーを返す() {
                    walk("int {readOnly}");

                    assertThatThrownBy(() -> obj.extractPropModifier()).isInstanceOf(InputMismatchException.class);
                }
            }
        }
    }

    private void walk(String text) {
        obj = new AttributeEvaluation();
        obj.setText(text);
        obj.walk();
    }

    enum DefaultValueType {
        Numeric,
        Text,
        Genuineness,
        Undefined,
        Expression,
    }
}