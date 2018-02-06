package feature.value.expression;

import feature.value.expression.symbol.Symbol;

/**
 * <p> 単項式クラス </p>
 *
 * <p>
 *     使い方を次に示します。
 * </p>
 *
 * <pre>
 *     {@code
 *     Monomial monomial = new monomial("-", new OneIdentifier(1));
 *
 *     System.out.println(monomial); // "- 1"
 *     }
 * </pre>
 */
public class Monomial implements Expression {

    private Symbol symbol;
    private Expression expression;

    /**
     * <p> 単項式コンストラクタ </p>
     *
     * <p>
     *     式における項の{@link Expression}インスタンスと、その演算子を文字列で設定します。
     * </p>
     *
     * @param symbolText 単項式の演算子
     * @param exp 単項式における式
     */
    public Monomial(String symbolText, Expression exp) {
        if (symbolText == null || exp == null) throw new IllegalArgumentException();
        symbol = Symbol.choose(symbolText);
        symbol.is(symbolText);
        expression = exp;
    }

    /**
     * <p> 単項式の文字列を取得します。 </p>
     *
     * @return 単項式の文字列 {@code null}および{@code ""}なし
     */
    @Override
    public String toString() {
        return symbol + " " + expression;
    }
}
