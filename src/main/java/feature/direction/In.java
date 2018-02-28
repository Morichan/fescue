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
     * 入力方向の文字列を出力する設定を行います。
     */
    @Override
    public void isOuted() {
        isOuted = true;
    }

    /**
     * 入力方向の文字列を出力しない設定を行います。
     */
    @Override
    public void isNotOuted() {
        isOuted = false;
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
