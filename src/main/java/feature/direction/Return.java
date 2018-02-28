package feature.direction;

/**
 * <p> 戻り値クラス </p>
 *
 * <p>
 *     方向における戻り値 ({@code "return"}) クラスです。
 * </p>
 */
public class Return implements Direction {

    private boolean isOuted = true;

    /**
     * 戻り値方向の文字列を出力する設定を行います。
     */
    @Override
    public void isOuted() {
        isOuted = true;
    }

    /**
     * 戻り値方向の文字列を出力しない設定は行えないため、例外を投げます。
     */
    @Override
    public void isNotOuted() {
        throw new IllegalCallerException();
    }

    /**
     * <p> 戻り値方向の文字列を取得します。 </p>
     *
     * @return {@code "return"}
     */
    @Override
    public String toString() {
        return "return";
    }
}
