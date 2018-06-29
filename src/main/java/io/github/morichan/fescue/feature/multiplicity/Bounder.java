package io.github.morichan.fescue.feature.multiplicity;

import io.github.morichan.fescue.feature.value.expression.Expression;

/**
 * <p> 上限クラス </p>
 *
 * <p>
 *     使い方を次に示します。
 * </p>
 *
 * <pre>
 *     {@code
 *     Bounder upperOfExpression = new Bounder(new OneIdentifier(1));
 *     System.out.println(upperOfExpression); // "1"
 *
 *     Bounder upperOfAsterisk = new Bounder("*");
 *     System.out.println(upperOfExpression); // "*"
 *     }
 * </pre>
 */
public class Bounder {

    private Expression expression;
    private String bounderText;

    /**
     * <p> 式による上限コンストラクタ </p>
     *
     * <p>
     *     上限を式の{@link Expression}インスタンスで設定します。
     * </p>
     *
     * @param exp 式<br>別のコンストラクタがオブジェクトを引数に取るため{@code null}不可（{@link #Bounder(String)}参照）
     */
    public Bounder(Expression exp) {
        expression = exp;
    }

    /**
     * <p> 文字列による上限コンストラクタ </p>
     *
     * <p>
     *     上限を文字列の{@link Expression}インスタンスで設定します。
     * </p>
     *
     * @param text 文字列<br>別のコンストラクタがオブジェクトを引数に取るため{@code null}不可（{@link #Bounder(Expression)}参照）
     */
    public Bounder(String text) {
        if (text.length() <= 0) throw new IllegalArgumentException();
        bounderText = text;
    }

    /**
     * <p> 式を取得します。 </p>
     *
     * <p>
     *     式が{@code null}の場合（例えば{@link #Bounder(String)}によるインスタンス生成後にこのメソッドを実行した場合）には、{@link IllegalStateException}を投げます。
     * </p>
     *
     * @return 式<br>{@code null}なし<br>式が{@code null}の場合は{@link IllegalStateException}
     */
    public Expression getExpression() {
        if (expression == null) throw new IllegalStateException();
        return expression;
    }

    /**
     * <p> 上限の文字列を取得します。 </p>
     *
     * <p>
     *     上限のインスタンス生成には必ず{@link #Bounder(String)}または{@link #Bounder(Expression)}を利用する必要があります。
     *     2つのコンストラクタはどちらも{@link Object}型を引数に取るため、{@code null}を引数としたコンストラクタはコンパイルエラーとなります。
     *     また、{@link Expression}は{@code null}不可、{@link #Bounder(String)}の引数は空文字不可のため、必ず何か文字列を含んでいます。
     * </p>
     *
     * @return 既定値の文字列<br>{@code null}および{@code ""}なし
     */
    @Override
    public String toString() {
        String text;

        if (bounderText == null) text = expression.toString();
        else text = bounderText;

        return text;
    }
}
