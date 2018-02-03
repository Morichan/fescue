package feature.value.expression;

import feature.value.expression.symbol.Symbol;

/**
 * <p> 2項式クラス </p>
 *
 * <p>
 *     使い方を次に示します。
 * </p>
 *
 * <pre>
 *     {@code
 *     Binomial binomial = new Binomial("*",
 *             new OneIdentifier(1),
 *             new ExpressionWithParen(new Binomial("+", new OneIdentifier(2), new OneIdentifier(3))));
 *
 *     System.out.println(binomial); // "1 * (2 + 3)"
 *     }
 * </pre>
 */
public class Binomial implements Expression {
    private Expression first;
    private Expression second;
    private Symbol symbol;
    private boolean isHadSpaceBothSides;

    /**
     * <p> 2項式コンストラクタ </p>
     *
     * <p>
     *     式における2項の{@link Expression}インスタンスと、その演算子を文字列で設定します。
     * </p>
     *
     * @param symbolText 2項式の演算子
     * @param first 2項式の最初の式
     * @param second 2項式の次の式
     */
    public Binomial(String symbolText, Expression first, Expression second) {
        if (symbolText == null || first == null || second == null) throw new IllegalArgumentException();
        this.first = first;
        this.second = second;
        this.symbol = Symbol.choose(symbolText);
        symbol.is(symbolText);
        isHadSpaceBothSides = this.symbol.isHadSpaceBothSides();
    }

    /**
     * <p> 2項式の文字列を取得します。 </p>
     *
     * @return 2項式の文字列 {@code null}および{@code ""}なし
     */
    @Override
    public String toString() {
        if (isHadSpaceBothSides) return first + " " + symbol + " " + second;
        return first + symbol.toString() + second;
    }
}
