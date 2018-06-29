package io.github.morichan.fescue.feature.property;

/**
 * <p> ReadOnlyクラス </p>
 *
 * <p>
 *     プロパティにおけるReadOnlyクラスです。
 * </p>
 */
public class ReadOnly implements Property {

    /**
     * <p> readOnlyプロパティの文字列を取得します。 </p>
     *
     * @return {@code "readOnly"}<br>{@code null}および空文字なし
     */
    @Override
    public String toString() {
        return "readOnly";
    }
}
