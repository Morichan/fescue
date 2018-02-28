package feature.direction;

/**
 * <p> 入力クラス </p>
 *
 * <p>
 *     方向における入力 ({@code "in" or ""}) クラスです。
 * </p>
 */
public class In implements Direction {

    private boolean isOuted = false;

    /**
     * デフォルトコンストラクタ
     */
    public In() {}

    /**
     * <p> 入力方向の文字列を出力するかどうかの真偽値を設定するコンストラクタ </p>
     *
     * <p>
     *     {@link #setOuted(boolean)}を呼出しています。
     * </p>
     *
     * @param isOuted 入力方向の文字列を出力する場合は真である真偽値
     */
    public In(boolean isOuted) {
        setOuted(isOuted);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isOuted() {
        return isOuted;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setOuted(boolean isOuted) {
        this.isOuted = isOuted;
    }

    /**
     * <p> 入力方向の文字列を取得します。 </p>
     *
     * <p>
     *     {@link #isOuted}が真の場合は{@code "in"}を、偽の場合は{@code ""}（空文字）を返します。
     * </p>
     *
     * @return {@code "in" or ""}
     */
    @Override
    public String toString() {
        return isOuted ? "in" : "";
    }
}
