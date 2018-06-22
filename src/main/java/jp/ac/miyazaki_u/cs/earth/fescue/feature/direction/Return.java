package jp.ac.miyazaki_u.cs.earth.fescue.feature.direction;

/**
 * <p> 戻り値クラス </p>
 *
 * <p>
 *     方向における戻り値 ({@code "return"}) クラスです。
 * </p>
 */
public class Return implements Direction {

    /**
     * <p> 戻り値方向の文字列を出力する設定を行います。 </p>
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
     *     戻り値方向の文字列を出力しない設定は行えないため、{@code false}を設定した場合は例外を投げます。
     * </p>
     */
    @Deprecated
    @Override
    public void setOuted(boolean isOuted) {
        if (! isOuted) throw new IllegalCallerException();
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
