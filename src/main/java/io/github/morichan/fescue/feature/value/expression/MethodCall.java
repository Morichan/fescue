package io.github.morichan.fescue.feature.value.expression;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p> メソッド呼出しクラス </p>
 *
 * <p>
 *     使い方を次に示します。
 * </p>
 *
 * <pre>
 *     {@code
 *     MethodCall methodWithoutArgs = new MethodCall("methodName");
 *     System.out.println(methodWithoutArgs); // "methodName()"
 *
 *     MethodCall methodWithArgs = new MethodCall("methodWithArgs", new OneIdentifier("instanceName"), new OneIdentifier(1);
 *     System.out.println(methodWithoutArgs); // "methodWithArgs(instanceName, 1)"
 *     }
 * </pre>
 */
public class MethodCall implements Expression {
    private Expression methodName;
    private List<Expression> methodArgs = new ArrayList<>();

    /**
     * <p> メソッド呼出しコンストラクタ </p>
     *
     * <p>
     *     式におけるメソッド名の文字列と、その引数を設定します。
     *     引数は0こ以上の複数を設定できます。
     * </p>
     *
     * <p>
     *     メソッド名に{@code null}または{@code ""}（空文字）を設定した場合は{@link IllegalArgumentException}を投げます。
     *     また、メソッドの引数に1つでも{@code null}を設定した場合は{@link IllegalArgumentException}を投げます。
     * </p>
     *
     * @param text 式におけるメソッド名<br>{@code null}および{@code ""}（空文字）不可
     * @param expressions メソッド引数（0こ以上の複数を設定可能）<br>{@code null}不可
     */
    public MethodCall(String text, Expression... expressions) {
        initMethodCall(text, expressions);
    }

    public MethodCall(String text, List<Expression> expressions) {
        initMethodCall(text, expressions.toArray(new Expression[expressions.size()]));
    }

    private void initMethodCall(String text, Expression... expressions) {
        for (Expression exp : expressions)
            if (exp == null)
                throw new IllegalArgumentException();

        methodName = new OneIdentifier(text);
        methodArgs.addAll(Arrays.asList(expressions));
    }

    /**
     * <p> メソッド呼出しの文字列を取得します。 </p>
     *
     * @return メソッド呼出しの文字列<br>{@code null}および{@code ""}なし
     */
    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        List<String> args = new ArrayList<>();

        buffer.append(methodName);
        buffer.append("(");
        for (Expression exp: methodArgs) {
            args.add(exp.toString());
        }
        buffer.append(String.join(", ", args));
        buffer.append(")");

        return buffer.toString();
    }
}
