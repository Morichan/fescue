package feature;

import feature.direction.Direction;
import feature.direction.In;
import feature.multiplicity.Bounder;
import feature.multiplicity.MultiplicityRange;
import feature.name.Name;
import feature.property.*;
import feature.type.Type;
import feature.value.DefaultValue;
import feature.value.expression.Binomial;
import feature.value.expression.OneIdentifier;
import feature.visibility.Visibility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class AttributeTest {

    Attribute obj;

    @Nested
    class 名前に関して {

        @BeforeEach
        void setup() {
            obj = new Attribute(new Name("name"));
        }

        @Test
        void 名前を設定するとその名前を返す() {
            String expected = "name";
            Name name = new Name("name");

            obj.setName(name);
            Name actual = obj.getName();

            assertThat(actual.getNameText()).isEqualTo(expected);
        }

        @Test
        void 名前にnullを設定すると例外を返す() {
            assertThatThrownBy(() -> obj.setName(null)).isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Nested
    class 可視性に関して {

        @BeforeEach
        void setup() {
            obj = new Attribute(new Name("name"));
        }

        @Test
        void 可視性を設定するとその可視性を返す() {
            String expected = "-";

            obj.setVisibility(Visibility.Private);
            Visibility actual = obj.getVisibility();

            assertThat(actual.is(expected)).isTrue();
        }

        @Test
        void 可視性を文字列で設定するとその可視性を返す() {
            String expected = "-";

            obj.setVisibility(Visibility.choose("-"));
            Visibility actual = obj.getVisibility();

            assertThat(actual.is(expected)).isTrue();
        }

        @Test
        void 可視性にnullを設定すると例外を返す() {
            assertThatThrownBy(() -> obj.setVisibility(null)).isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        void 可視性を設定せずに取得しようとすると例外を返す() {
            assertThatThrownBy(() -> obj.getVisibility()).isInstanceOf(IllegalStateException.class);
        }
    }

    @Nested
    class 派生に関して {

        @BeforeEach
        void setup() {
            obj = new Attribute(new Name("name"));
        }

        @Test
        void 派生に真を設定すると真を返す() {

            obj.setDerived(true);
            boolean actual = obj.isDerived();

            assertThat(actual).isTrue();
        }

        @Test
        void 派生に偽を設定すると偽を返す() {

            obj.setDerived(false);
            boolean actual = obj.isDerived();

            assertThat(actual).isFalse();
        }

        @Test
        void 派生を文字列で設定すると真を返す() {

            obj.setDerived("/");
            boolean actual = obj.isDerived();

            assertThat(actual).isTrue();
        }

        @Test
        void 派生を設定していない場合は偽を返す() {

            boolean actual = obj.isDerived();

            assertThat(actual).isFalse();
        }

        @Test
        void 派生を文字列で設定する際に別の文字列を入力すると例外を返す() {
            assertThatThrownBy(() -> obj.setDerived("notDerivedText")).isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        void 派生を文字列で設定する際にnullを入力すると例外を返す() {
            assertThatThrownBy(() -> obj.setDerived(null)).isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Nested
    class 型に関して {

        @BeforeEach
        void setup() {
            obj = new Attribute(new Name("name"));
        }

        @Test
        void 型を設定するとその型を返す() {
            String expected = "int";

            obj.setType(new Type("int"));
            String actual = obj.getType().toString();

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void 型にnullを設定すると例外を返す() {
            assertThatThrownBy(() -> obj.setType(null)).isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        void 型を設定せずに取得しようとすると例外を返す() {
            assertThatThrownBy(() -> obj.getType()).isInstanceOf(IllegalStateException.class);
        }
    }

    @Nested
    class 既定値に関して {

        @BeforeEach
        void setup() {
            obj = new Attribute(new Name("name"));
        }

        @Test
        void 設定すると既定値を返す() {
            String expected = "1";

            obj.setDefaultValue(new DefaultValue(new OneIdentifier(1)));
            String actual = obj.getDefaultValue().toString();

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void nullを設定すると例外を返す() {
            assertThatThrownBy(() -> obj.setDefaultValue(null)).isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        void 設定せずに取得しようとすると例外を返す() {
            assertThatThrownBy(() -> obj.getDefaultValue()).isInstanceOf(IllegalStateException.class);
        }
    }

    @Nested
    class 多重度に関して {

        @BeforeEach
        void setup() {
            obj = new Attribute(new Name("name"));
        }

        @Test
        void 上限のみの多重度を設定すると多重度を返す() {
            String expected = "*";

            obj.setMultiplicityRange(new MultiplicityRange(new Bounder("*")));
            String actual = obj.getMultiplicityRange().toString();

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void 下限と上限を持つ多重度を設定すると多重度を返す() {
            String expected = "1..*";

            obj.setMultiplicityRange(new MultiplicityRange(new Bounder(new OneIdentifier(1)), new Bounder("*")));
            String actual = obj.getMultiplicityRange().toString();

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void nullを設定すると例外を返す() {
            assertThatThrownBy(() -> obj.setMultiplicityRange(null)).isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        void 設定せずに取得しようとすると例外を返す() {
            assertThatThrownBy(() -> obj.getMultiplicityRange()).isInstanceOf(IllegalStateException.class);
        }
    }

    @Nested
    class プロパティについて {
        Property property1;
        Property property2;

        @Nested
        class 単数の場合 {

            @BeforeEach
            void setup() {
                obj = new Attribute(new Name("name"));
                property1 = new ReadOnly();
                property2 = new Subsets(new OneIdentifier("instance"));
            }

            @Test
            void 追加するとプロパティを返す() {
                String expected = "readOnly";

                obj.addProperty(property1);
                String actual = obj.getProperty(0).toString();

                assertThat(actual).isEqualTo(expected);
            }

            @Test
            void 複数追加するとプロパティを返す() {
                String expected = "subsets instance";

                obj.addProperty(property1);
                obj.addProperty(property2);
                String actual = obj.getProperty(1).toString();

                assertThat(actual).isEqualTo(expected);
            }

            @Test
            void nullを設定すると例外を投げる() {
                assertThatThrownBy(() -> obj.addProperty(null)).isInstanceOf(IllegalArgumentException.class);
            }

            @Test
            void リスト以上のインデックスを設定して取得しようとすると例外を投げる() {
                assertThatThrownBy(() -> obj.getProperty(1)).isInstanceOf(IllegalStateException.class);
            }
        }

        @Nested
        class リストの場合 {

            @BeforeEach
            void setup() {
                obj = new Attribute(new Name("name"));
                property1 = new ReadOnly();
                property2 = new Subsets(new OneIdentifier("instance"));
            }

            @Test
            void 複数同時に追加するとプロパティのリストを返す() {

                obj.setProperties(Arrays.asList(property1, property2));
                List<Property> actual = obj.getProperties();

                assertThat(actual).containsExactly(property1, property2);
            }

            @Test
            void nullを設定すると例外を投げる() {
                assertThatThrownBy(() -> obj.setProperties(null)).isInstanceOf(IllegalArgumentException.class);
            }

            @Test
            void リストが空の場合に取得しようとすると例外を投げる() {
                assertThatThrownBy(() -> obj.getProperties()).isInstanceOf(IllegalStateException.class);
            }
        }
    }

    @Nested
    class 名前設定コンストラクタについて {

        @Test
        void 設定すると名前を返す() {
            String expected = "name";

            obj = new Attribute(new Name("name"));
            String actual = obj.getName().toString();

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void nullを設定すると例外を投げる() {
            assertThatThrownBy(() -> obj = new Attribute(null)).isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Nested
    class 出力文字列について {

        @Test
        void 名前を出力する() {
            String expected = "name";

            obj = new Attribute(new Name("name"));
            String actual = obj.toString();

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void 名前と可視性を出力する() {
            String expected = "# name";

            obj = new Attribute(new Name("name"));
            obj.setVisibility(Visibility.Protected);
            String actual = obj.toString();

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void 名前と派生を出力する() {
            String expected = "/name";

            obj = new Attribute(new Name("name"));
            obj.setDerived(true);
            String actual = obj.toString();

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void 名前と型を出力する() {
            String expected = "name : char";

            obj = new Attribute(new Name("name"));
            obj.setType(new Type("char"));
            String actual = obj.toString();

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void 名前と多重度を出力する() {
            String expected = "name [*]";

            obj = new Attribute(new Name("name"));
            obj.setMultiplicityRange(new MultiplicityRange(new Bounder("*")));
            String actual = obj.toString();

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void 名前と既定値を出力する() {
            String expected = "name = \"string text\"";

            obj = new Attribute(new Name("name"));
            obj.setDefaultValue(new DefaultValue(new OneIdentifier("\"string text\"")));
            String actual = obj.toString();

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void 名前とプロパティ1つを出力する() {
            String expected = "name {union}";

            obj = new Attribute(new Name("name"));
            obj.addProperty(new Union());
            String actual = obj.toString();

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void 可視性と派生と名前と型と多重度と既定値とプロパティ2つを出力する() {
            String expected = "- /numberId : int [0..*] = id + '001' {readOnly, redefines id}";

            Name name = new Name("numberId");
            Visibility visibility = Visibility.Private;
            String derived = "/";
            Type type = new Type("int");
            MultiplicityRange range = new MultiplicityRange(new Bounder(new OneIdentifier(0)), new Bounder("*"));
            DefaultValue value = new DefaultValue(
                    new Binomial("+", new OneIdentifier("id"), new OneIdentifier("'001'")));
            List<Property> properties = Arrays.asList(new ReadOnly(), new Redefines(new OneIdentifier("id")));

            obj = new Attribute(name);
            obj.setVisibility(visibility);
            obj.setDerived(derived);
            obj.setType(type);
            obj.setMultiplicityRange(range);
            obj.setDefaultValue(value);
            obj.setProperties(properties);
            String actual = obj.toString();

            assertThat(actual).isEqualTo(expected);
        }
    }
}