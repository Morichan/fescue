package io.github.morichan.fescue.feature.value;

import io.github.morichan.fescue.feature.value.expression.Expression;

/**
 * <p> 既定値クラス </p>
 *
 * <p>
 *     使い方を次に示します。
 * </p>
 *
 * <pre>
 *     {@code
 *     DefaultValue valueOfNumber = new DefaultValue(new OneIdentifier(1));
 *     System.out.println(valueOfNumber); // "1"
 *
 *     DefaultValue valueOfExpression = new DefaultValue(new Binomial("+", new OneIdentifier("instance"), new MethodCall("callTwo")));
 *     System.out.println(valueOfExpression); // "instance + callTwo()"
 *     }
 * </pre>
 */
public class DefaultValue {

    private Expression value;

    /**
     * <p> 既定値コンストラクタ </p>
     *
     * <p>
     *     既定値における式の{@link Expression}インスタンスを設定します。
     * </p>
     *
     * @param expression 既定値
     */
    public DefaultValue(Expression expression) {
        if (expression == null) throw new IllegalArgumentException();
        value = expression;
    }

    /**
     * <p> 式を取得します。 </p>
     *
     * @return 式<br>{@code null}なし
     */
    public Expression getExpression() {
        return value;
    }

    /**
     * <p> 既定値の文字列を取得します。 </p>
     *
     * @return 既定値の文字列<br>{@code null}および{@code ""}なし
     */
    @Override
    public String toString() {
        return value.toString();
    }
}
