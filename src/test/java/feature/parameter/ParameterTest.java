package feature.parameter;

import feature.direction.Direction;
import feature.direction.In;
import feature.direction.InOut;
import feature.multiplicity.Bounder;
import feature.multiplicity.MultiplicityRange;
import feature.name.Name;
import feature.property.Property;
import feature.property.ReadOnly;
import feature.property.Subsets;
import feature.type.Type;
import feature.value.DefaultValue;
import feature.value.expression.Binomial;
import feature.value.expression.OneIdentifier;
import feature.value.expression.symbol.Multi;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class ParameterTest {

    Parameter obj;

    @Nested
    class 方向について {

        @BeforeEach
        void setup() {
            obj = new Parameter(new Name("name"));
        }

        @Test
        void 設定すると方向を返す() {
            String expected = "inout";

            obj.setDirection(new InOut());
            String actual = obj.getDirection().toString();

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void nullを設定すると例外を投げる() {
            assertThatThrownBy(() -> obj.setDirection(null)).isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Nested
    class パラメータ名について {

        @BeforeEach
        void setup() {
            obj = new Parameter(new Name("name"));
        }

        @Test
        void 設定すると名前を返す() {
            String expected = "parameterName";

            obj.setName(new Name("parameterName"));
            String actual = obj.getName().toString();

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void nullを設定すると例外を投げる() {
            assertThatThrownBy(() -> obj.setName(null)).isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Nested
    class パラメータの型について {

        @BeforeEach
        void setup() {
            obj = new Parameter(new Name("name"));
        }

        @Test
        void 設定すると型を返す() {
            String expected = "int";

            obj.setType(new Type("int"));
            String actual = obj.getType().toString();

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void nullを設定すると例外を投げる() {
            assertThatThrownBy(() -> obj.setType(null)).isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        void 設定前に取得しようとすると例外を返す() {
            assertThatThrownBy(() -> obj.getType()).isInstanceOf(IllegalStateException.class);
        }
    }

    @Nested
    class 多重度について {

        @BeforeEach
        void setup() {
            obj = new Parameter(new Name("name"));
        }

        @Test
        void 設定すると型を返す() {
            String expected = "*";

            obj.setMultiplicityRange(new MultiplicityRange(new Bounder("*")));
            String actual = obj.getMultiplicityRange().toString();

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void nullを設定すると例外を投げる() {
            assertThatThrownBy(() -> obj.setMultiplicityRange(null)).isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        void 設定前に取得しようとすると例外を返す() {
            assertThatThrownBy(() -> obj.getMultiplicityRange()).isInstanceOf(IllegalStateException.class);
        }
    }

    @Nested
    class 既定値について {

        @BeforeEach
        void setup() {
            obj = new Parameter(new Name("name"));
        }

        @Test
        void 設定すると型を返す() {
            String expected = "10";

            obj.setDefaultValue(new DefaultValue(new OneIdentifier(10)));
            String actual = obj.getDefaultValue().toString();

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void nullを設定すると例外を投げる() {
            assertThatThrownBy(() -> obj.setDefaultValue(null)).isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        void 設定前に取得しようとすると例外を返す() {
            assertThatThrownBy(() -> obj.getDefaultValue()).isInstanceOf(IllegalStateException.class);
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
                obj = new Parameter(new Name("name"));
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
                obj = new Parameter(new Name("name"));
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
    class プロパティ名設定コンストラクタについて {

        @Test
        void 設定するとプロパティ名を返す() {
            String expected = "name";

            obj = new Parameter(new Name("name"));
            String actual = obj.getName().toString();

            assertThat(actual).isEqualTo(expected);
        }
    }

    @Nested
    class 出力文字列について {
        @Test
        void 名前を出力する() {
            String expected = "name";

            obj = new Parameter(new Name("name"));
            String actual = obj.toString();

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void 名前と型を出力する() {
            String expected = "name : char";

            obj = new Parameter(new Name("name"));
            obj.setType(new Type("char"));
            String actual = obj.toString();

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void 名前と方向を出力する() {
            String expected = "inout name";

            obj = new Parameter(new Name("name"));
            obj.setDirection(new InOut());
            String actual = obj.toString();

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void 名前と多重度を出力する() {
            String expected = "name [0..1]";

            obj = new Parameter(new Name("name"));
            obj.setMultiplicityRange(new MultiplicityRange(
                    new Bounder(new OneIdentifier(0)),
                    new Bounder(new OneIdentifier(1))));
            String actual = obj.toString();

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void 名前と既定値を出力する() {
            String expected = "name = 'name text'";

            obj = new Parameter(new Name("name"));
            obj.setDefaultValue(new DefaultValue(new OneIdentifier("'name text'")));
            String actual = obj.toString();

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void 名前とプロパティ1つを出力する() {
            String expected = "name {readOnly}";

            obj = new Parameter(new Name("name"));
            obj.addProperty(new ReadOnly());
            String actual = obj.toString();

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void 方向と名前と型と多重度と既定値とプロパティ2つを出力する() {
            String expected = "in number : int [*] = 10 {readOnly, subsets super.number}";

            Name name = new Name("number");
            Direction in = new In();
            in.setOuted(true);
            Type type = new Type("int");
            MultiplicityRange range = new MultiplicityRange(new Bounder("*"));
            DefaultValue value = new DefaultValue(new OneIdentifier(10));
            List<Property> properties = Arrays.asList(new ReadOnly(), new Subsets(
                    new Binomial(".", new OneIdentifier("super"), new OneIdentifier("number"))));

            obj = new Parameter(name);
            obj.setDirection(in);
            obj.setType(type);
            obj.setMultiplicityRange(range);
            obj.setDefaultValue(value);
            obj.setProperties(properties);
            String actual = obj.toString();

            assertThat(actual).isEqualTo(expected);
        }
    }
}