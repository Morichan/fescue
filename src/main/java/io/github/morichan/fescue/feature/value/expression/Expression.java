package io.github.morichan.fescue.feature.value.expression;

/**
 * <p> 式インタフェース </p>
 *
 * <p>
 *     式を定義するインタフェースです。
 * </p>
 */
public interface Expression {

    /**
     * <p> 式の文字列を取得します。 </p>
     *
     * @return 式の文字列<br>{@code null}および{@code ""}は実装に依存します。
     */
    @Override
    String toString();
}
