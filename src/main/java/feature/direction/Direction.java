package feature.direction;

/**
 * <p> 方向インタフェース </p>
 *
 * <p>
 *     方向を定義するインタフェースです。
 * </p>
 */
public interface Direction {

    /**
     * 方向の文字列を出力する設定を行います。
     */
    void isOuted();

    /**
     * 方向の文字列を出力しない設定を行います。
     */
    void isNotOuted();

    /**
     * <p> 方向の文字列を取得します。 </p>
     *
     * @return 方向の文字列<br>{@code null}および{@code ""}は実装に依存します。
     */
    @Override
    String toString();
}
