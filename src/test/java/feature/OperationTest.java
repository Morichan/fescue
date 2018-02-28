package feature;

import feature.name.Name;
import feature.parameter.Parameter;
import feature.property.Property;
import feature.property.ReadOnly;
import feature.property.Subsets;
import feature.type.Type;
import feature.value.expression.OneIdentifier;
import feature.visibility.Visibility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class OperationTest {
    Operation obj;

    @Nested
    class 名前の場合 {

        @BeforeEach
        void setup() {
            obj = new Operation();
        }

        @Test
        void 設定すると名前を返す() {
            String expected = "name";
            Name name = new Name("name");

            obj.setName(name);
            Name actual = obj.getName();

            assertThat(actual.getNameText()).isEqualTo(expected);
        }

        @Test
        void nullを設定すると例外を返す() {
            assertThatThrownBy(() -> obj.setName(null)).isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        void 設定せずに取得しようとすると例外を返す() {
            assertThatThrownBy(() -> obj.getName()).isInstanceOf(IllegalStateException.class);
        }
    }

    @Nested
    class 可視性に関して {

        @BeforeEach
        void setup() {
            obj = new Operation();
        }

        @Test
        void 設定すると可視性を返す() {
            String expected = "-";

            obj.setVisibility(Visibility.Private);
            Visibility actual = obj.getVisibility();

            assertThat(actual.is(expected)).isTrue();
        }

        @Test
        void 文字列で設定すると可視性を返す() {
            String expected = "-";

            obj.setVisibility(Visibility.choose("-"));
            Visibility actual = obj.getVisibility();

            assertThat(actual.is(expected)).isTrue();
        }

        @Test
        void nullを設定すると例外を返す() {
            assertThatThrownBy(() -> obj.setVisibility(null)).isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        void 設定せずに取得しようとすると例外を返す() {
            assertThatThrownBy(() -> obj.getVisibility()).isInstanceOf(IllegalStateException.class);
        }
    }

    @Nested
    class パラメータについて {
        Parameter parameter1;
        Parameter parameter2;

        @Nested
        class 単数の場合 {

            @BeforeEach
            void setup() {
                obj = new Operation();
                parameter1 = new Parameter();
                parameter2 = new Parameter();
                parameter1.setName(new Name("arg1"));
                parameter2.setName(new Name("arg2"));
            }

            @Test
            void 追加するとパラメータを返す() {
                String expected = "arg1";

                obj.addParameter(parameter1);
                String actual = obj.getParameter(0).toString();

                assertThat(actual).isEqualTo(expected);
            }

            @Test
            void 複数追加するとパラメータを返す() {
                String expected = "arg2";

                obj.addParameter(parameter1);
                obj.addParameter(parameter2);
                String actual = obj.getParameter(1).toString();

                assertThat(actual).isEqualTo(expected);
            }

            @Test
            void nullを設定すると例外を投げる() {
                assertThatThrownBy(() -> obj.addParameter(null)).isInstanceOf(IllegalArgumentException.class);
            }

            @Test
            void リスト以上のインデックスを設定して取得しようとすると例外を投げる() {
                assertThatThrownBy(() -> obj.getParameter(1)).isInstanceOf(IllegalStateException.class);
            }
        }

        @Nested
        class リストの場合 {

            @BeforeEach
            void setup() {
                obj = new Operation();
                parameter1 = new Parameter();
                parameter2 = new Parameter();
                parameter1.setName(new Name("arg1"));
                parameter2.setName(new Name("arg2"));
            }

            @Test
            void 複数同時に追加するとプロパティのリストを返す() {

                obj.setParameters(Arrays.asList(parameter1, parameter2));
                List<Parameter> actual = obj.getParameters();

                assertThat(actual).containsExactly(parameter1, parameter2);
            }

            @Test
            void nullを設定すると例外を投げる() {
                assertThatThrownBy(() -> obj.setParameters(null)).isInstanceOf(IllegalArgumentException.class);
            }

            @Test
            void リストが空の場合に取得しようとすると例外を投げる() {
                assertThatThrownBy(() -> obj.getParameters()).isInstanceOf(IllegalStateException.class);
            }
        }
    }

    @Nested
    class 戻り値の型に関して {

        @BeforeEach
        void setup() {
            obj = new Operation();
        }

        @Test
        void 設定すると型を返す() {
            String expected = "int";

            obj.setReturnType(new Type("int"));
            String actual = obj.getReturnType().toString();

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void nullを設定すると例外を返す() {
            assertThatThrownBy(() -> obj.setReturnType(null)).isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        void 設定せずに取得しようとすると例外を返す() {
            assertThatThrownBy(() -> obj.getReturnType()).isInstanceOf(IllegalStateException.class);
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
                obj = new Operation();
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
                obj = new Operation();
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
}