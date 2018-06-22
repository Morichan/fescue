package jp.ac.miyazaki_u.cs.earth.fescue.feature.value.expression.symbol;

/**
 * <p> 大なりイコールクラス </p>
 *
 * <p>
 *     演算子における大なりイコール{@code ">="}クラスです。
 * </p>
 */
public class GreaterEqual extends Symbol {

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
     * <p> 大なりイコール演算子の文字列を取得します。 </p>
     *
     * @return {@code ">="}
     */
    @Override
    public String toString() {
        return ">=";
    }
}
