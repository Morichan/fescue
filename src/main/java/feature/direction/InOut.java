package feature.direction;

/**
 * <p> 入出力クラス </p>
 *
 * <p>
 *     方向における入出力 ({@code "inout"}) クラスです。
 * </p>
 */
public class InOut implements Direction {

    private boolean isOuted = true;

    /**
     * 入出力方向の文字列を出力する設定を行います。
     */
    @Override
    public void isOuted() {
        isOuted = true;
    }

    /**
     * 入出力方向の文字列を出力しない設定は行えないため、例外を投げます。
     */
    @Override
    public void isNotOuted() {
        throw new IllegalCallerException();
    }

    /**
     * <p> 入出力方向の文字列を取得します。 </p>
     *
     * @return {@code "inout"}
     */
    @Override
    public String toString() {
        return "inout";
    }
}
