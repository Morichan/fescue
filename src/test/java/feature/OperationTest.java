package feature;

import feature.direction.In;
import feature.multiplicity.Bounder;
import feature.multiplicity.MultiplicityRange;
import feature.name.Name;
import feature.parameter.Parameter;
import feature.property.*;
import feature.type.Type;
import feature.value.DefaultValue;
import feature.value.expression.Binomial;
import feature.value.expression.ExpressionWithParen;
import feature.value.expression.MethodCall;
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
            obj = new Operation(new Name("name"));
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
    }

    @Nested
    class 可視性に関して {

        @BeforeEach
        void setup() {
            obj = new Operation(new Name("name"));
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
                obj = new Operation(new Name("name"));
                parameter1 = new Parameter(new Name("arg1"));
                parameter2 = new Parameter(new Name("arg2"));
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
                obj = new Operation(new Name("name"));
                parameter1 = new Parameter(new Name("arg1"));
                parameter2 = new Parameter(new Name("arg2"));
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
            obj = new Operation(new Name("name"));
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
                obj = new Operation(new Name("name"));
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
                obj = new Operation(new Name("name"));
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

            obj = new Operation(new Name("name"));
            String actual = obj.getName().toString();

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void nullを設定すると例外を投げる() {
            assertThatThrownBy(() -> obj = new Operation(null)).isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Nested
    class 出力文字列について {

        @Test
        void 名前を出力する() {
            String expected = "name()";

            obj = new Operation(new Name("name"));
            String actual = obj.toString();

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void 名前と可視性を出力する() {
            String expected = "~ name()";

            obj = new Operation(new Name("name"));
            obj.setVisibility(Visibility.Package);
            String actual = obj.toString();

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void 名前とパラメータ1つを出力する() {
            String expected = "name(paramName)";

            obj = new Operation(new Name("name"));
            obj.addParameter(new Parameter(new Name("paramName")));
            String actual = obj.toString();

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void 名前と型を出力する() {
            String expected = "name() : double";

            obj = new Operation(new Name("name"));
            obj.setReturnType(new Type("double"));
            String actual = obj.toString();

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void 名前とプロパティ1つを出力する() {
            String expected = "name() {query}";

            obj = new Operation(new Name("name"));
            obj.addProperty(new Query());
            String actual = obj.toString();

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void 可視性と名前とパラメータ2つと型とプロパティ3つを出力する() {
            // Arrange
            String expected = "+ selectProjectData(in projectId : char [*] = \"paramId001\","
                    + " datum : i32 [(lowCount - 1)..*] = getDataList(dataId, count).get(Project.number) {readOnly})"
                    + " : void {query, redefines selectProjectDataImpl, unique}";

            Name name = new Name("selectProjectData");
            Visibility visibility = Visibility.choose("+");

            Parameter param1 = new Parameter(new Name("projectId"));
            param1.setDirection(new In(true));
            param1.setType(new Type("char"));
            param1.setMultiplicityRange(new MultiplicityRange(new Bounder("*")));
            param1.setDefaultValue(new DefaultValue(new OneIdentifier("\"paramId001\"")));

            Parameter param2 = new Parameter(new Name("datum"));
            param2.setDirection(new In(false));
            param2.setType(new Type("i32"));
            param2.setMultiplicityRange(
                    new MultiplicityRange(
                            new Bounder(new ExpressionWithParen(new Binomial("-", new OneIdentifier("lowCount"), new OneIdentifier(1)))),
                            new Bounder("*")));
            param2.setDefaultValue(new DefaultValue(new Binomial(".",
                    new MethodCall("getDataList", new OneIdentifier("dataId"), new OneIdentifier("count")),
                    new MethodCall("get", new Binomial(".", new OneIdentifier("Project"), new OneIdentifier("number"))))));
            param2.addProperty(new ReadOnly());

            List<Parameter> parameters = Arrays.asList(param1, param2);

            Type type = new Type("void");
            List<Property> properties = Arrays.asList(new Query(), new Redefines(new OneIdentifier("selectProjectDataImpl")), new Unique());

            // Act
            obj = new Operation(name);
            obj.setVisibility(visibility);
            obj.setParameters(parameters);
            obj.setReturnType(type);
            obj.setProperties(properties);
            String actual = obj.toString();

            // Assert
            assertThat(actual)
                    .as(actual)
                    .isEqualTo(expected);
        }
    }
}