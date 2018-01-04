package usage;

import net.java.quickcheck.Generator;
import org.antlr.v4.runtime.InputMismatchException;
import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.List;

import static net.java.quickcheck.generator.CombinedGenerators.lists;
import static net.java.quickcheck.generator.PrimitiveGenerators.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.in;
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
                    String expected = "1+2";
                    String attribute = "onePlusTwo = 1 + 2";
                    walk(attribute);

                    String actual = obj.extractDefaultValue();

                    assertThat(actual).isEqualTo(expected);
                }

                @Test
                void 減算を返す() {
                    String expected = "1-2";
                    String attribute = "oneMinusTwo = 1 - 2";
                    walk(attribute);

                    String actual = obj.extractDefaultValue();

                    assertThat(actual).isEqualTo(expected);
                }

                @Test
                void 乗算を返す() {
                    String expected = "1*2";
                    String attribute = "oneMultipliedByTwo = 1 * 2";
                    walk(attribute);

                    String actual = obj.extractDefaultValue();

                    assertThat(actual).isEqualTo(expected);
                }

                @Test
                void 除算を返す() {
                    String expected = "1/2";
                    String attribute = "oneDividedByTwo = 1 / 2";
                    walk(attribute);

                    String actual = obj.extractDefaultValue();

                    assertThat(actual).isEqualTo(expected);
                }

                @Test
                void 余りを返す() {
                    String expected = "1%2";
                    String attribute = "remainderOfOneDividedByTwo = 1 % 2";
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
        class ランダムテストの場合 {
            final Generator<String> nameGenerator = letterStrings();
            final Generator<String> visibilityGenerator = fixedValues("", "-", "+", "#", "~");
            final Generator<Boolean> dividedGenerator = booleans();
            final Generator<String> propTypeGenerator = fixedValues("bool", "boolean", "c", "char", "character", "byte", "s", "short", "i", "int", "integer", "l", "long", "f", "float", "lf", "double");

            String name;

            @BeforeEach
            void selectName() {
                name = selectNameOfLengthZero();
            }

            @RepeatedTest(100)
            void 名前を返す() {
                String expected = selectNameOfLengthZero();
                walk(expected);

                try {
                    String actual = obj.extractName();
                    assertThat(actual).isEqualTo(expected);

                } catch (InputMismatchException e) {
                    System.out.println("InputMismatchException : input '"+expected+"'");
                }
            }

            @RepeatedTest(100)
            void 可視性を返す() {
                String visibility = visibilityGenerator.next();
                walk(visibility + " " + name);

                String actual = obj.extractVisibility();

                if (visibility.length() <= 0) assertThat(actual).isNull();
                else assertThat(actual).isEqualTo(visibility);
            }

            @RepeatedTest(100)
            void 派生を返す() {
                String visibility = visibilityGenerator.next();
                boolean isDivided = dividedGenerator.next();
                String divided = isDivided ? "/" : "";

                walk(visibility + " " + divided + name);
                String actual = obj.extractDivided();

                if (isDivided) assertThat(actual).isEqualTo(divided);
                else assertThat(actual).isNull();
            }

            @RepeatedTest(100)
            void 型を返す() {
                String visibility = visibilityGenerator.next();
                String propType = propTypeGenerator.next();

                walk(visibility + " " + name + " : " + propType);

                try {
                    String actual = obj.extractPropType();
                    assertThat(actual).isEqualTo(propType);

                } catch (InputMismatchException e) {
                    System.out.println("InputMismatchException : input attribute name '"+name+"'" + " and prop type '"+propType+"'");
                }
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

                assertThrows(IllegalArgumentException.class, () -> obj.walk());
            }
        }

        @Nested
        class 取得する要素が {

            @Nested
            class 名前の場合 {

                @Test
                void 空文字を入力するとエラーを返す() {
                    walk("");

                    assertThrows(IllegalArgumentException.class, () -> obj.extractName());
                }

                @Test
                void プリミティブ型と同じ文字列を入力するとエラーを返す() {
                    walk("int : float");

                    assertThrows(InputMismatchException.class, () -> obj.extractName());
                }
            }

            @Nested
            class 型の場合 {

                @Test
                void プリミティブ型と同じ文字列を入力するとエラーを返す() {
                    walk("int : float");

                    assertThrows(InputMismatchException.class, () -> obj.extractPropType());
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
            }
        }
    }

    private void walk(String text) {
        obj = new AttributeEvaluation();
        obj.setAttribute(text);
        obj.walk();
    }
}