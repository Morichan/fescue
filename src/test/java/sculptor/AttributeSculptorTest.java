package sculptor;

import feature.Attribute;
import feature.multiplicity.Bounder;
import feature.multiplicity.MultiplicityRange;
import feature.name.Name;
import feature.type.Type;
import feature.value.DefaultValue;
import feature.value.expression.OneIdentifier;
import feature.visibility.Visibility;
import org.antlr.v4.runtime.ParserRuleContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import parser.ClassFeatureParser;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class AttributeSculptorTest {

    AttributeSculptor obj;

    @Nested
    class 正しい属性文の際 {

        @BeforeEach
        void setup() {
            obj = new AttributeSculptor();
        }

        @Test
        void 取得するコンテキストがPropertyContext型である() {
            String text = "- attribute";

            obj.parse(text);
            ParserRuleContext actual = obj.getContext();

            assertThat(actual).isInstanceOf(ClassFeatureParser.PropertyContext.class);
        }

        @Nested
        class 名前のみの場合 {

            @BeforeEach
            void setup() {
                obj = new AttributeSculptor();
            }

            @Test
            void 設定したインスタンスを返す() {
                Attribute expected = new Attribute(new Name("name"));

                obj.parse("name");
                Attribute actual = obj.carve();

                assertThat(actual).isEqualToComparingFieldByFieldRecursively(expected);
            }
        }

        @Nested
        class 名前と可視性の場合 {

            @BeforeEach
            void setup() {
                obj = new AttributeSculptor();
            }

            @Test
            void 設定したインスタンスを返す() {
                Attribute expected = new Attribute(new Name("name"));
                expected.setVisibility(Visibility.Private);

                obj.parse("- name");
                Attribute actual = obj.carve();

                assertThat(actual).isEqualToComparingFieldByFieldRecursively(expected);
            }
        }

        @Nested
        class 名前と派生の場合 {

            @BeforeEach
            void setup() {
                obj = new AttributeSculptor();
            }

            @Test
            void 設定したインスタンスを返す() {
                Attribute expected = new Attribute(new Name("name"));
                expected.setDerived(true);

                obj.parse("/name");
                Attribute actual = obj.carve();

                assertThat(actual).isEqualToComparingFieldByFieldRecursively(expected);
            }
        }

        @Nested
        class 名前と型の場合 {

            @BeforeEach
            void setup() {
                obj = new AttributeSculptor();
            }

            @Test
            void 設定したインスタンスを返す() {
                Attribute expected = new Attribute(new Name("number"));
                expected.setType(new Type("int"));

                obj.parse("number : int");
                Attribute actual = obj.carve();

                assertThat(actual).isEqualToComparingFieldByFieldRecursively(expected);
            }
        }

        @Nested
        class 名前と多重度の場合 {

            @BeforeEach
            void setup() {
                obj = new AttributeSculptor();
            }

            @Test
            void 上限のみを設定したインスタンスを返す() {
                Attribute expected = new Attribute(new Name("number"));
                expected.setMultiplicityRange(new MultiplicityRange(new Bounder("*")));

                obj.parse("number[*]");
                Attribute actual = obj.carve();

                assertThat(actual).isEqualToComparingFieldByFieldRecursively(expected);
            }

            @Test
            void 上限および下限を設定したインスタンスを返す() {
                Attribute expected = new Attribute(new Name("number"));
                expected.setMultiplicityRange(new MultiplicityRange(new Bounder("0"), new Bounder("1")));

                obj.parse("number[0..1]");
                Attribute actual = obj.carve();

                assertThat(actual).isEqualToComparingFieldByFieldRecursively(expected);
            }
        }

        @Nested
        class 名前と既定値の場合 {

            @Nested
            class 項が1つの場合 {

                @BeforeEach
                void setup() {
                    obj = new AttributeSculptor();
                }

                @Test
                void デフォルト数値のインスタンスを返す() {
                    Attribute expected = new Attribute(new Name("number"));
                    expected.setDefaultValue(new DefaultValue(new OneIdentifier(0)));

                    obj.parse("number = 0");
                    Attribute actual = obj.carve();

                    assertThat(actual).isEqualToComparingFieldByFieldRecursively(expected);
                }
            }
        }
    }

    @Nested
    class 不正な属性文の際 {

        @BeforeEach
        void setup() {
            obj = new AttributeSculptor();
        }

        @Test
        void 空文字をパースしようとすると例外を投げる() {
            assertThatThrownBy(() -> obj.parse("")).isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        void nullをパースしようとすると例外を投げる() {
            assertThatThrownBy(() -> obj.parse(null)).isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        void パースする前にコンテキストを取得しようとすると例外を投げる() {
            assertThatThrownBy(() -> obj.getContext()).isInstanceOf(IllegalStateException.class);
        }
    }
}