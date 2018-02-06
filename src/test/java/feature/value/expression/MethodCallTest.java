package feature.value.expression;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class MethodCallTest {

    MethodCall obj;

    @Nested
    class 引数を持たない場合 {

        @BeforeEach
        void setup() {
            obj = new MethodCall("methodName");
        }

        @Test
        void メソッド名を入力するとメソッド名を返す() {
            String expected = "methodName()";

            String actual = obj.toString();

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void メソッド名にnullを入力すると例外を投げる() {
            assertThatThrownBy(() -> obj = new MethodCall(null)).isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        void メソッド名に空文字を入力すると例外を投げる() {
            assertThatThrownBy(() -> obj = new MethodCall("")).isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Nested
    class 引数を持つ場合 {

        @Test
        void メソッド名と引数を1つ入力するとメソッドを文字列で返す() {
            String expected = "method(firstArgument)";

            obj = new MethodCall("method", new OneIdentifier("firstArgument"));
            String actual = obj.toString();

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void メソッド名と引数を2つ入力するとメソッドを文字列で返す() {
            String expected = "method(firstArgument, secondArgument)";

            obj = new MethodCall("method", new OneIdentifier("firstArgument"), new OneIdentifier("secondArgument"));
            String actual = obj.toString();

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void メソッド名と引数を3つ入力するとメソッドを文字列で返す() {
            String expected = "method(firstArgument, secondArgument, thirdArgument)";

            obj = new MethodCall("method", new OneIdentifier("firstArgument"), new OneIdentifier("secondArgument"), new OneIdentifier("thirdArgument"));
            String actual = obj.toString();

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void 引数に1つでもnullを含む場合は例外を投げる() {
            assertThatThrownBy(() ->
                    obj = new MethodCall("method",
                            new OneIdentifier("firstArgument"), new OneIdentifier("secondArgument"), null))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Nested
    class 複数の引数を持つ場合 {
        final List<Expression> args = Arrays.asList(
                new OneIdentifier("argument"),
                new Binomial(".", new OneIdentifier("ClassName"), new OneIdentifier("instance")),
                new Binomial(".", new OneIdentifier("ClassName"), new MethodCall("method")),
                new Binomial(".", new OneIdentifier("ClassName"), new MethodCall("method", new OneIdentifier("arg1"))),
                new Binomial(".", new OneIdentifier("ClassName"), new MethodCall("method",
                        new OneIdentifier("arg1"), new Binomial(".",
                        new OneIdentifier("instance"), new OneIdentifier("instance")))),
                new Binomial(".", new OneIdentifier("ClassName"), new MethodCall("method",
                        new OneIdentifier("arg1"), new Binomial(".",
                        new OneIdentifier("instance"), new OneIdentifier("instance")),
                        new MethodCall("method",
                                new OneIdentifier("arg1"), new Binomial(".",
                                new OneIdentifier("instance"), new OneIdentifier("instance")),
                                new MethodCall("thisMethod", new OneIdentifier("arg1"), new OneIdentifier("arg2"))))),
                new Binomial("+", new OneIdentifier(1), new OneIdentifier(2)),
                new Binomial(".", new MethodCall("method"),
                        new Binomial(".", new MethodCall("hasMethod"), new MethodCall("isMethod"))),
                new Binomial("*", new MethodCall("method"), new ExpressionWithParen(new Binomial("+",
                        new MethodCall("methodName", new OneIdentifier(1)), new OneIdentifier("\"text\"")))),
                new Binomial(".", new MethodCall("method"),
                        new Binomial(".", new OneIdentifier("hasInstance"), new MethodCall("isMethod")))
        );
        final List<String> argStrings = Arrays.asList(
                "argument",
                "ClassName.instance",
                "ClassName.method()",
                "ClassName.method(arg1)",
                "ClassName.method(arg1, instance.instance)",
                "ClassName.method(arg1, instance.instance, method(arg1, instance.instance, thisMethod(arg1, arg2)))",
                "1 + 2",
                "method().hasMethod().isMethod()",
                "method() * (methodName(1) + \"text\")",
                "method().hasInstance.isMethod()"
        );
        final int argCount = 10;

        @RepeatedTest(argCount)
        void メソッド名と複数の引数を入力するとメソッドを文字列で返す(RepetitionInfo info) {
            int maxCount = info.getCurrentRepetition();
            StringBuilder buffer = new StringBuilder();
            List<String> argTexts = new ArrayList<>();
            buffer.append("method(");
            for (int i = 0; i < maxCount; i++) {
                argTexts.add(argStrings.get(i));
            }
            buffer.append(String.join(", ", argTexts));
            buffer.append(")");
            String expected = buffer.toString();

            Expression[] methodArgs = new Expression[maxCount];
            for (int i = 0; i < maxCount; i++) {
                methodArgs[i] = args.get(i);
            }
            obj = new MethodCall("method", methodArgs);
            String actual = obj.toString();

            assertThat(actual).isEqualTo(expected);
        }
    }
}