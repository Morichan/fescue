package jp.ac.miyazaki_u.cs.earth.fescue.sculptor;

import jp.ac.miyazaki_u.cs.earth.fescue.feature.Operation;
import jp.ac.miyazaki_u.cs.earth.fescue.feature.direction.In;
import jp.ac.miyazaki_u.cs.earth.fescue.feature.direction.InOut;
import jp.ac.miyazaki_u.cs.earth.fescue.feature.direction.Out;
import jp.ac.miyazaki_u.cs.earth.fescue.feature.direction.Return;
import jp.ac.miyazaki_u.cs.earth.fescue.feature.multiplicity.Bounder;
import jp.ac.miyazaki_u.cs.earth.fescue.feature.multiplicity.MultiplicityRange;
import jp.ac.miyazaki_u.cs.earth.fescue.feature.name.Name;
import jp.ac.miyazaki_u.cs.earth.fescue.feature.parameter.Parameter;
import jp.ac.miyazaki_u.cs.earth.fescue.feature.property.*;
import jp.ac.miyazaki_u.cs.earth.fescue.feature.type.Type;
import jp.ac.miyazaki_u.cs.earth.fescue.feature.value.DefaultValue;
import jp.ac.miyazaki_u.cs.earth.fescue.feature.value.expression.*;
import jp.ac.miyazaki_u.cs.earth.fescue.feature.visibility.Visibility;
import org.antlr.v4.runtime.ParserRuleContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import jp.ac.miyazaki_u.cs.earth.fescue.parser.ClassFeatureParser;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class OperationSculptorTest {

    OperationSculptor obj;

    @Nested
    class 正しい属性文の際 {

        @BeforeEach
        void setup() {
            obj = new OperationSculptor();
        }

        @Test
        void 取得するコンテキストがOperationContext型である() {
            String text = "+ operation()";

            obj.parse(text);
            ParserRuleContext actual = obj.getContext();

            assertThat(actual).isInstanceOf(ClassFeatureParser.OperationContext.class);
        }

        @Nested
        class 名前のみの場合 {

            @BeforeEach
            void setup() {
                obj = new OperationSculptor();
            }

            @Test
            void 設定したインスタンスを返す() {
                Operation expected = new Operation(new Name("name"));

                obj.parse("name()");
                Operation actual = obj.carve();

                assertThat(actual).isEqualToComparingFieldByFieldRecursively(expected);
            }
        }

        @Nested
        class 名前と可視性の場合 {

            @BeforeEach
            void setup() {
                obj = new OperationSculptor();
            }

            @Test
            void 設定したインスタンスを返す() {
                Operation expected = new Operation(new Name("name"));
                expected.setVisibility(Visibility.Public);

                obj.parse("+ name");
                Operation actual = obj.carve();

                assertThat(actual).isEqualToComparingFieldByFieldRecursively(expected);
            }
        }

        @Nested
        class 名前と型の場合 {

            @BeforeEach
            void setup() {
                obj = new OperationSculptor();
            }

            @Test
            void 設定したインスタンスを返す() {
                Operation expected = new Operation(new Name("getNumber"));
                expected.setReturnType(new Type("int"));

                obj.parse("getNumber() : int");
                Operation actual = obj.carve();

                assertThat(actual).isEqualToComparingFieldByFieldRecursively(expected);
            }

            @Test
            void 設定したvoidを返す() {
                Operation expected = new Operation(new Name("toDo"));
                expected.setReturnType(new Type("void"));

                obj.parse("toDo() : void");
                Operation actual = obj.carve();

                assertThat(actual).isEqualToComparingFieldByFieldRecursively(expected);
            }
        }

        @Nested
        class パラメータリストにおいて {

            @Nested
            class 名前と型の場合 {

                @BeforeEach
                void setup() {
                    obj = new OperationSculptor();
                }

                @Test
                void 名前と型を1つ持つインスタンスを返す() {
                    Operation expected = new Operation(new Name("setNumber"));
                    List<Parameter> params = new ArrayList<>();
                    params.add(new Parameter(new Name("number")));
                    params.get(0).setType(new Type("int"));
                    expected.setParameters(params);

                    obj.parse("setNumber(number : int)");
                    Operation actual = obj.carve();

                    assertThat(actual).isEqualToComparingFieldByFieldRecursively(expected);
                }

                @Test
                void 名前と型を2つ持つインスタンスを返す() {
                    Operation expected = new Operation(new Name("setNumber"));
                    List<Parameter> params = new ArrayList<>();
                    params.add(new Parameter(new Name("number")));
                    params.get(0).setType(new Type("int"));
                    params.add(new Parameter(new Name("number2")));
                    params.get(1).setType(new Type("int"));
                    expected.setParameters(params);

                    obj.parse("setNumber(number : int, number2 : int)");
                    Operation actual = obj.carve();

                    assertThat(actual).isEqualToComparingFieldByFieldRecursively(expected);
                }

                @Test
                void 名前と型を3つ持つインスタンスを返す() {
                    Operation expected = new Operation(new Name("calculate"));
                    List<Parameter> params = new ArrayList<>();
                    params.add(new Parameter(new Name("number")));
                    params.get(0).setType(new Type("int"));
                    params.add(new Parameter(new Name("text")));
                    params.get(1).setType(new Type("char"));
                    params.add(new Parameter(new Name("price")));
                    params.get(2).setType(new Type("double"));
                    expected.setParameters(params);

                    obj.parse("calculate(number : int, text : char, price : double)");
                    Operation actual = obj.carve();

                    assertThat(actual).isEqualToComparingFieldByFieldRecursively(expected);
                }
            }

            @Nested
            class 名前と型と方向の場合 {

                @BeforeEach
                void setup() {
                    obj = new OperationSculptor();
                }

                @Test
                void 方向がinの名前と型を1つ持つインスタンスを返す() {
                    Operation expected = new Operation(new Name("setNumber"));
                    List<Parameter> params = new ArrayList<>();
                    params.add(new Parameter(new Name("number")));
                    params.get(0).setType(new Type("int"));
                    params.get(0).setDirection(new In(true));
                    expected.setParameters(params);

                    obj.parse("setNumber(in number : int)");
                    Operation actual = obj.carve();

                    assertThat(actual).isEqualToComparingFieldByFieldRecursively(expected);
                }

                @Test
                void 方向がoutの名前と型を1つ持つインスタンスを返す() {
                    Operation expected = new Operation(new Name("getNumber"));
                    List<Parameter> params = new ArrayList<>();
                    params.add(new Parameter(new Name("number")));
                    params.get(0).setType(new Type("int"));
                    params.get(0).setDirection(new Out());
                    expected.setParameters(params);

                    obj.parse("getNumber(out number : int)");
                    Operation actual = obj.carve();

                    assertThat(actual).hasToString(expected.toString());
                }

                @Test
                void 方向がinoutの名前と型を1つ持つインスタンスを返す() {
                    Operation expected = new Operation(new Name("getNumber"));
                    List<Parameter> params = new ArrayList<>();
                    params.add(new Parameter(new Name("number")));
                    params.get(0).setType(new Type("int"));
                    params.get(0).setDirection(new InOut());
                    expected.setParameters(params);

                    obj.parse("getNumber(inout number : int)");
                    Operation actual = obj.carve();

                    assertThat(actual).hasToString(expected.toString());
                }

                @Test
                void 方向がreturnの名前と型を1つ持つインスタンスを返す() {
                    Operation expected = new Operation(new Name("getNumber"));
                    List<Parameter> params = new ArrayList<>();
                    params.add(new Parameter(new Name("number")));
                    params.get(0).setType(new Type("int"));
                    params.get(0).setDirection(new Return());
                    expected.setParameters(params);

                    obj.parse("getNumber(return number : int)");
                    Operation actual = obj.carve();

                    assertThat(actual).hasToString(expected.toString());
                }

                @Test
                void 方向と名前と型を4つ持つインスタンスを返す() {
                    Operation expected = new Operation(new Name("getNumber"));
                    List<Parameter> params = new ArrayList<>();
                    params.add(new Parameter(new Name("arg1")));
                    params.get(0).setType(new Type("char"));
                    params.get(0).setDirection(new In(true));
                    params.add(new Parameter(new Name("arg2")));
                    params.get(1).setType(new Type("double"));
                    params.get(1).setDirection(new Out());
                    params.add(new Parameter(new Name("arg3")));
                    params.get(2).setType(new Type("float"));
                    params.get(2).setDirection(new InOut());
                    params.add(new Parameter(new Name("arg4")));
                    params.get(3).setType(new Type("byte"));
                    params.get(3).setDirection(new Return());
                    expected.setParameters(params);

                    obj.parse("getNumber(in arg1 : char, out arg2 : double, inout arg3 : float, return arg4 : byte)");
                    Operation actual = obj.carve();

                    assertThat(actual).hasToString(expected.toString());
                }
            }

            @Nested
            class 名前と型と多重度の場合 {

                @BeforeEach
                void setup() {
                    obj = new OperationSculptor();
                }

                @Test
                void 名前と型と多重度を1つ持つインスタンスを返す() {
                    Operation expected = new Operation(new Name("setNumber"));
                    List<Parameter> params = new ArrayList<>();
                    params.add(new Parameter(new Name("text")));
                    params.get(0).setType(new Type("char"));
                    params.get(0).setMultiplicityRange(new MultiplicityRange(new Bounder("*")));
                    expected.setParameters(params);

                    obj.parse("setNumber(text : char [*])");
                    Operation actual = obj.carve();

                    assertThat(actual).hasToString(expected.toString());
                }

                @Test
                void 名前と型と多重度を3つ持つインスタンスを返す() {
                    Operation expected = new Operation(new Name("setNumber"));
                    List<Parameter> params = new ArrayList<>();
                    params.add(new Parameter(new Name("arg1")));
                    params.get(0).setType(new Type("char"));
                    params.get(0).setMultiplicityRange(new MultiplicityRange(new Bounder("*")));
                    params.add(new Parameter(new Name("arg2")));
                    params.get(1).setType(new Type("char"));
                    params.get(1).setMultiplicityRange(new MultiplicityRange(
                            new Bounder(new OneIdentifier(1)), new Bounder("*")));
                    params.add(new Parameter(new Name("arg3")));
                    params.get(2).setType(new Type("char"));
                    params.get(2).setMultiplicityRange(new MultiplicityRange(
                            new Bounder(new OneIdentifier(0)), new Bounder(new OneIdentifier(1))));
                    expected.setParameters(params);

                    obj.parse("setNumber(arg1 : char [*], arg2 : char [1..*], arg3 : char [0..1])");
                    Operation actual = obj.carve();

                    assertThat(actual).hasToString(expected.toString());
                }
            }

            @Nested
            class 名前と型と既定値では {

                @Nested
                class 項が1つの場合 {

                    @BeforeEach
                    void setup() {
                        obj = new OperationSculptor();
                    }

                    @Test
                    void 名前と型と既定値を1つ持つインスタンスを返す() {
                        Operation expected = new Operation(new Name("setNumber"));
                        List<Parameter> params = new ArrayList<>();
                        params.add(new Parameter(new Name("number")));
                        params.get(0).setType(new Type("int"));
                        params.get(0).setDefaultValue(new DefaultValue(new OneIdentifier(0)));
                        expected.setParameters(params);

                        obj.parse("setNumber(number : int = 0)");
                        Operation actual = obj.carve();

                        assertThat(actual).isEqualToComparingFieldByFieldRecursively(expected);
                    }

                    @Test
                    void 名前と型と既定値を3つ持つインスタンスを返す() {
                        Operation expected = new Operation(new Name("setNumber"));
                        List<Parameter> params = new ArrayList<>();
                        params.add(new Parameter(new Name("arg1")));
                        params.get(0).setType(new Type("int"));
                        params.get(0).setDefaultValue(new DefaultValue(new Monomial("-", new OneIdentifier(1))));
                        params.add(new Parameter(new Name("arg2")));
                        params.get(1).setType(new Type("double"));
                        params.get(1).setDefaultValue(new DefaultValue(new OneIdentifier("5.0")));
                        params.add(new Parameter(new Name("arg3")));
                        params.get(2).setType(new Type("bool"));
                        params.get(2).setDefaultValue(new DefaultValue(new Monomial("not", new OneIdentifier("true"))));
                        expected.setParameters(params);

                        obj.parse("setNumber(arg1 : int = -1, arg2 : double = 5.0, arg3 : bool = not true)");
                        Operation actual = obj.carve();

                        assertThat(actual).isEqualToComparingFieldByFieldRecursively(expected);
                    }
                }

                @Nested
                class 項が2つの場合 {

                    @BeforeEach
                    void setup() {
                        obj = new OperationSculptor();
                    }

                    @Test
                    void 名前と型と既定値を1つ持つインスタンスを返す() {
                        Operation expected = new Operation(new Name("setNumber"));
                        List<Parameter> params = new ArrayList<>();
                        params.add(new Parameter(new Name("number")));
                        params.get(0).setType(new Type("int"));
                        params.get(0).setDefaultValue(new DefaultValue(new Binomial("+",
                                new OneIdentifier("number"), new OneIdentifier(1))));
                        expected.setParameters(params);

                        obj.parse("setNumber(number : int = number + 1)");
                        Operation actual = obj.carve();

                        assertThat(actual).isEqualToComparingFieldByFieldRecursively(expected);
                    }

                    @Test
                    void 名前と型と既定値を3つ持つインスタンスを返す() {
                        Operation expected = new Operation(new Name("setNumber"));
                        List<Parameter> params = new ArrayList<>();
                        params.add(new Parameter(new Name("arg1")));
                        params.get(0).setType(new Type("int32"));
                        params.get(0).setDefaultValue(new DefaultValue(new Binomial(">=",
                                new OneIdentifier("number"), new OneIdentifier("10"))));
                        params.add(new Parameter(new Name("arg2")));
                        params.get(1).setType(new Type("double"));
                        params.get(1).setDefaultValue(new DefaultValue(new Binomial(".",
                                new OneIdentifier("instances"), new MethodCall("method", new OneIdentifier("arg")))));
                        params.add(new Parameter(new Name("arg3")));
                        params.get(2).setType(new Type("bool"));
                        params.get(2).setDefaultValue(new DefaultValue(
                                new Binomial(".", new OneIdentifier("instances"), new OneIdentifier("instance"))));
                        expected.setParameters(params);

                        obj.parse("setNumber(arg1 : int32 = number >= 10, arg2 : double = instances.method(arg), arg3 : bool = instances.instance)");
                        Operation actual = obj.carve();

                        assertThat(actual).isEqualToComparingFieldByFieldRecursively(expected);
                    }
                }

                @Nested
                class メソッドの場合 {

                    @BeforeEach
                    void setup() {
                        obj = new OperationSculptor();
                    }

                    @Test
                    void 名前と型と既定値を1つ持つインスタンスを返す() {
                        Operation expected = new Operation(new Name("setNumber"));
                        List<Parameter> params = new ArrayList<>();
                        params.add(new Parameter(new Name("number")));
                        params.get(0).setType(new Type("int"));
                        params.get(0).setDefaultValue(new DefaultValue(new MethodCall("methodName")));
                        expected.setParameters(params);

                        obj.parse("setNumber(number : int = methodName())");
                        Operation actual = obj.carve();

                        assertThat(actual).isEqualToComparingFieldByFieldRecursively(expected);
                    }

                    @Test
                    void 名前と型と既定値を3つ持つインスタンスを返す() {
                        Operation expected = new Operation(new Name("setNumber"));
                        List<Parameter> params = new ArrayList<>();
                        params.add(new Parameter(new Name("arg1")));
                        params.get(0).setType(new Type("int32"));
                        params.get(0).setDefaultValue(new DefaultValue(
                                new MethodCall("methodName",
                                        new OneIdentifier(0),
                                        new OneIdentifier("instance"),
                                        new Monomial("-", new OneIdentifier(1)))));
                        params.add(new Parameter(new Name("arg2")));
                        params.get(1).setType(new Type("double"));
                        params.get(1).setDefaultValue(new DefaultValue(new Binomial(".",
                                new MethodCall("methods", new OneIdentifier("with"), new OneIdentifier("arg")),
                                new MethodCall("method",
                                        new OneIdentifier("1.0"),
                                        new Monomial("-", new OneIdentifier("2.0")),
                                        new Monomial("+", new OneIdentifier("3.0")),
                                        new MethodCall("forMethod",
                                                new MethodCall("ofMethod",
                                                        new MethodCall("inMethod", new OneIdentifier(0))))))));
                        params.add(new Parameter(new Name("arg3")));
                        params.get(2).setType(new Type("bool"));
                        params.get(2).setDefaultValue(new DefaultValue(new Binomial(".",
                                new Binomial(".",
                                        new Binomial(".",
                                                new MethodCall("method", new OneIdentifier("arg1")),
                                                new MethodCall("forMethod", new OneIdentifier("arg2"))),
                                        new MethodCall("ofMethod", new OneIdentifier("arg3"))),
                                new MethodCall("inMethod", new OneIdentifier("arg4")))));
                        expected.setParameters(params);

                        obj.parse("setNumber(arg1 : int32 = methodName(0, instance, -1), arg2 : double = methods(with, arg).method(1.0, -2.0, +3.0, forMethod(ofMethod(inMethod(0)))), arg3 : bool = method(arg1).forMethod(arg2).ofMethod(arg3).inMethod(arg4))");
                        Operation actual = obj.carve();

                        assertThat(actual).isEqualToComparingFieldByFieldRecursively(expected);
                    }
                }

                @Nested
                class カッコで囲っている式の場合 {

                    @BeforeEach
                    void setup() {
                        obj = new OperationSculptor();
                    }

                    @Test
                    void 名前と型と既定値を1つ持つインスタンスを返す() {
                        Operation expected = new Operation(new Name("setNumber"));
                        List<Parameter> params = new ArrayList<>();
                        params.add(new Parameter(new Name("number")));
                        params.get(0).setType(new Type("int"));
                        params.get(0).setDefaultValue(new DefaultValue(new ExpressionWithParen(new OneIdentifier("withParen"))));
                        expected.setParameters(params);

                        obj.parse("setNumber(number : int = (withParen))");
                        Operation actual = obj.carve();

                        assertThat(actual).isEqualToComparingFieldByFieldRecursively(expected);
                    }

                    @Test
                    void 名前と型と既定値を3つ持つインスタンスを返す() {
                        Operation expected = new Operation(new Name("setNumber"));
                        List<Parameter> params = new ArrayList<>();
                        params.add(new Parameter(new Name("arg1")));
                        params.get(0).setType(new Type("f32"));
                        params.get(0).setDefaultValue(new DefaultValue(
                                new ExpressionWithParen(new Binomial("/",
                                        new MethodCall("methodName",
                                                new OneIdentifier(0),
                                                new OneIdentifier("instance"),
                                                new Monomial("-", new OneIdentifier(1))),
                                        new OneIdentifier(2)))));
                        params.add(new Parameter(new Name("arg2")));
                        params.get(1).setType(new Type("i64"));
                        params.get(1).setDefaultValue(new DefaultValue(new Binomial("*",
                                new ExpressionWithParen(
                                        new Binomial("+",
                                                new OneIdentifier("instance"),
                                                new Binomial(".",
                                                        new MethodCall("methods", new OneIdentifier("with"), new OneIdentifier("arg")),
                                                        new MethodCall("method",
                                                                new OneIdentifier("1.0"),
                                                                new Monomial("-", new OneIdentifier("2.0")),
                                                                new Monomial("+", new OneIdentifier("3.0")),
                                                                new MethodCall("forMethod",
                                                                        new MethodCall("ofMethod",
                                                                                new MethodCall("inMethod", new OneIdentifier(0)))))))),
                                new OneIdentifier("300000"))));
                        params.add(new Parameter(new Name("arg3")));
                        params.get(2).setType(new Type("bool"));
                        params.get(2).setDefaultValue(new DefaultValue(new Binomial(".",
                                new Binomial(".",
                                        new Binomial(".",
                                                new MethodCall("method", new OneIdentifier("arg1")),
                                                new MethodCall("forMethod",
                                                        new Binomial("/",
                                                                new Binomial("*",
                                                                        new ExpressionWithParen(
                                                                                new Binomial("+",
                                                                                        new OneIdentifier("upperBase"),
                                                                                        new OneIdentifier("lowerBase"))),
                                                                        new OneIdentifier("height")),
                                                                new OneIdentifier(2)))),
                                        new MethodCall("ofMethod", new OneIdentifier("arg3"))),
                                new MethodCall("inMethod", new OneIdentifier("arg4")))));
                        expected.setParameters(params);

                        obj.parse("setNumber(arg1 : f32 = (methodName(0, instance, -1) / 2), arg2 : i64 = (instance + methods(with, arg).method(1.0, -2.0, +3.0, forMethod(ofMethod(inMethod(0))))) * 300000, arg3 : bool = method(arg1).forMethod((upperBase + lowerBase) * height / 2).ofMethod(arg3).inMethod(arg4))");
                        Operation actual = obj.carve();

                        assertThat(actual).isEqualToComparingFieldByFieldRecursively(expected);
                    }
                }
            }

            @Nested
            class 名前と型とプロパティの場合 {

                @BeforeEach
                void setup() {
                    obj = new OperationSculptor();
                }

                @Test
                void 名前と型とプロパティを1つ持つインスタンスを返す() {
                    Operation expected = new Operation(new Name("setNumber"));
                    List<Parameter> params = new ArrayList<>();
                    params.add(new Parameter(new Name("number")));
                    params.get(0).setType(new Type("int"));
                    params.get(0).addProperty(new ReadOnly());
                    expected.setParameters(params);

                    obj.parse("setNumber(number : int {readOnly})");
                    Operation actual = obj.carve();

                    assertThat(actual).hasToString(expected.toString());
                }

                @Test
                void 名前と型とプロパティを3つ持つインスタンスを返す() {
                    Operation expected = new Operation(new Name("setNumber"));
                    List<Parameter> params = new ArrayList<>();
                    params.add(new Parameter(new Name("arg1")));
                    params.get(0).setType(new Type("int32"));
                    params.get(0).addProperty(new Redefines(new Binomial(".", new OneIdentifier("Class"), new OneIdentifier("field"))));
                    params.add(new Parameter(new Name("arg2")));
                    params.get(1).setType(new Type("double"));
                    params.get(1).addProperty(new Union());
                    params.get(1).addProperty(new Subsets(new OneIdentifier("arg")));
                    params.get(1).addProperty(new Unique());
                    params.add(new Parameter(new Name("arg3")));
                    params.get(2).setType(new Type("bool"));
                    params.get(2).addProperty(new Unique());
                    params.get(2).addProperty(new Ordered());
                    params.get(2).addProperty(new Redefines(new Binomial(".",
                            new Binomial(".",
                                    new Binomial(".",
                                            new MethodCall("method", new OneIdentifier("arg1")),
                                            new MethodCall("forMethod",
                                                    new Binomial("/",
                                                            new Binomial("*",
                                                                    new ExpressionWithParen(
                                                                            new Binomial("+",
                                                                                    new OneIdentifier("upperBase"),
                                                                                    new OneIdentifier("lowerBase"))),
                                                                    new OneIdentifier("height")),
                                                            new OneIdentifier(2)))),
                                    new MethodCall("ofMethod", new OneIdentifier("arg3"))),
                            new MethodCall("inMethod", new OneIdentifier("arg4")))));
                    params.get(2).addProperty(new Subsets(new Binomial("*",
                            new ExpressionWithParen(
                                    new Binomial("+",
                                            new OneIdentifier("instance"),
                                            new Binomial(".",
                                                    new MethodCall("methods", new OneIdentifier("with"), new OneIdentifier("arg")),
                                                    new MethodCall("method",
                                                            new OneIdentifier("1.0"),
                                                            new Monomial("-", new OneIdentifier("2.0")),
                                                            new Monomial("+", new OneIdentifier("3.0")),
                                                            new MethodCall("forMethod",
                                                                    new MethodCall("ofMethod",
                                                                            new MethodCall("inMethod", new OneIdentifier(0)))))))),
                            new OneIdentifier("300000"))));
                    params.get(2).addProperty(new Union());
                    params.get(2).addProperty(new ReadOnly());
                    expected.setParameters(params);

                    obj.parse("setNumber(arg1 : int32 {redefines Class.field}, arg2 : double {union, subsets arg, unique}, arg3 : bool {unique, ordered, redefines method(arg1).forMethod((upperBase + lowerBase) * height / 2).ofMethod(arg3).inMethod(arg4), subsets (instance + methods(with, arg).method(1.0, -2.0, +3.0, forMethod(ofMethod(inMethod(0))))) * 300000, union, readOnly})");
                    Operation actual = obj.carve();

                    assertThat(actual).hasToString(expected.toString());
                }
            }
        }

        @Nested
        class 名前とプロパティの場合 {

            @BeforeEach
            void setup() {
                obj = new OperationSculptor();
            }

            @Test
            void プロパティが1つのインスタンスを返す() {
                Operation expected = new Operation(new Name("getNumber"));
                expected.addProperty(new Redefines(new OneIdentifier("getNumber")));

                obj.parse("getNumber() {redefines getNumber}");
                Operation actual = obj.carve();

                assertThat(actual).isEqualToComparingFieldByFieldRecursively(expected);
            }

            @Test
            void プロパティが4つのインスタンスを返す() {
                Operation expected = new Operation(new Name("getNumber"));
                expected.addProperty(new Redefines(new Binomial(".",
                        new OneIdentifier("Classes"),
                        new MethodCall("method"))));
                expected.addProperty(new Query());
                expected.addProperty(new Ordered());
                expected.addProperty(new Unique());

                obj.parse("getNumber() {redefines Classes.method(), query, ordered, unique}");
                Operation actual = obj.carve();

                assertThat(actual).isEqualToComparingFieldByFieldRecursively(expected);
            }
        }
    }

    @Nested
    class 不正な操作文の場合 {

        @BeforeEach
        void setup() {
            obj = new OperationSculptor();
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