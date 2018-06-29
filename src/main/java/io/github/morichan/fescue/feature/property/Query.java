package io.github.morichan.fescue.feature.property;

/**
 * <p> Queryクラス </p>
 *
 * <p>
 *     プロパティにおけるQueryクラスです。
 * </p>
 */
public class Query implements Property {

    /**
     * <p> queryプロパティの文字列を取得します。 </p>
     *
     * @return {@code "query"}<br>{@code null}および空文字なし
     */
    @Override
    public String toString() {
        return "query";
    }
}
