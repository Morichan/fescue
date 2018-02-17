package feature.value.expression.symbol;

/**
 * <p> 等号クラス </p>
 *
 * <p>
 *     演算子における等号{@code "=="}クラスです。
 * </p>
 */
public class Equal extends Symbol {

    /**
     * <p> 演算子の両端にスペースを保持している場合は真を返す真偽値判定を行う。 </p>
     *
     * @return 真
     */
    @Override
    public boolean isHadSpaceBothSides() {
        return true;
    }

    /**
     * <p> 等号演算子の文字列を取得します。 </p>
     *
     * @return {@code "=="}
     */
    @Override
    public String toString() {
        return "==";
    }
}
