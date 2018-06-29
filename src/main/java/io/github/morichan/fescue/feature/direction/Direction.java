package io.github.morichan.fescue.feature.direction;

/**
 * <p> 方向インタフェース </p>
 *
 * <p>
 *     方向を定義するインタフェースです。
 * </p>
 */
public interface Direction {

    /**
     * <p> 方向の文字列を出力する場合は真を返す真偽値判定を行います。 </p>
     *
     * @return 方向の文字列を出力する場合は真を返す真偽値
     */
    boolean isOuted();

    /**
     * <p> 方向の文字列を出力かどうかの真偽値を設定します。 </p>
     *
     * @param isOuted 方向の文字列を出力する場合は真である真偽値
     */
    void setOuted(boolean isOuted);

    /**
     * <p> 方向の文字列を取得します。 </p>
     *
     * @return 方向の文字列<br>{@code null}および{@code ""}は実装に依存します。
     */
    @Override
    String toString();
}
