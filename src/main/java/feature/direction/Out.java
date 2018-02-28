package feature.direction;

/**
 * <p> 出力クラス </p>
 *
 * <p>
 *     方向における出力 ({@code "out"}) クラスです。
 * </p>
 */
public class Out implements Direction {

    private boolean isOuted = true;

    /**
     * 出力方向の文字列を出力する設定を行います。
     */
    @Override
    public void isOuted() {
        isOuted = true;
    }

    /**
     * 出力方向の文字列を出力しない設定は行えないため、例外を投げます。
     */
    @Override
    public void isNotOuted() {
        throw new IllegalCallerException();
    }

    /**
     * <p> 出力方向の文字列を取得します。 </p>
     *
     * @return {@code "out"}
     */
    @Override
    public String toString() {
        return "out";
    }
}
