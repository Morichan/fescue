package feature.direction;

/**
 * <p> 入出力クラス </p>
 *
 * <p>
 *     方向における入出力 ({@code "inout"}) クラスです。
 * </p>
 */
public class InOut implements Direction {

    /**
     * <p> 入出力方向の文字列を出力する設定を行います。 </p>
     *
     * <p>
     *     変更はできないため、常に真を返します。
     * </p>
     *
     * @return {@code true}
     */
    @Override
    public boolean isOuted() {
        return true;
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     *     @deprecated
     *     入出力方向の文字列を出力しない設定は行えないため、{@code false}を設定した場合は例外を投げます。
     * </p>
     */
    @Deprecated
    @Override
    public void setOuted(boolean isOuted) {
        if (! isOuted) throw new IllegalCallerException();
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
