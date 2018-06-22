package jp.ac.miyazaki_u.cs.earth.fescue.feature.value.expression;

/**
 * <p> 括弧で囲んだ式クラス </p>
 *
 * <p>
 *     使い方を次に示します。
 * </p>
 *
 * <pre>
 *     {@code
 *     ExpressionWithParen onePlusTwo = new ExpressionWithParen(new Binomial("+", new OneIdentifier(2), new OneIdentifier(3))));
 *
 *     System.out.println(onePlusTwo); // "(1 + 2)"
 *     }
 * </pre>
 */
public class ExpressionWithParen implements Expression {
    private Expression expression;

    /**
     * <p> 式コンストラクタ </p>
     *
     * <p>
     *     {@link Expression}インスタンスを生成します。
     * </p>
     *
     * @param exp 式
     */
    public ExpressionWithParen(Expression exp) {
        expression = exp;
    }

    /**
     * <p> 式の文字列を取得します。 </p>
     *
     * @return 式の文字列<br>{@code null}および{@code ""}なし
     */
    @Override
    public String toString() {
        return "(" + expression.toString() + ")";
    }
}
