package io.github.morichan.fescue.feature.property;

/**
 * <p> プロパティインタフェース </p>
 *
 * <p>
 *     プロパティを定義するインタフェースです。
 * </p>
 */
public interface Property {

    /**
     * <p> プロパティの文字列を取得します。 </p>
     *
     * @return プロパティの文字列<br>{@code null}および{@code ""}は実装に依存します。
     */
    @Override
    String toString();
}
