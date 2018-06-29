package io.github.morichan.fescue.feature.value.expression.symbol;

/**
 * <p> 不等号クラス </p>
 *
 * <p>
 *     演算子における不等号{@code "!="}クラスです。
 * </p>
 */
public class NotEqual extends Symbol {

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
     * <p> 不等号演算子の文字列を取得します。 </p>
     *
     * @return {@code "!="}
     */
    @Override
    public String toString() {
        return "!=";
    }
}
