package io.github.morichan.fescue.feature.direction;

/**
 * <p> 出力クラス </p>
 *
 * <p>
 *     方向における出力 ({@code "out"}) クラスです。
 * </p>
 */
public class Out implements Direction {

    /**
     * <p> 出力方向の文字列を出力する設定を行います。 </p>
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
     *     出力方向の文字列を出力しない設定は行えないため、{@code false}を設定した場合は例外を投げます。
     * </p>
     */
    @Deprecated
    @Override
    public void setOuted(boolean isOuted) {
        if (! isOuted) throw new IllegalCallerException();
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
