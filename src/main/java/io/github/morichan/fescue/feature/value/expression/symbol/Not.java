package io.github.morichan.fescue.feature.value.expression.symbol;

import java.util.Arrays;
import java.util.List;

/**
 * <p> 否定クラス </p>
 *
 * <p>
 *     演算子における否定クラスです。
 *     状態が複数（{@code "!", "not", "NOT"}）存在します。
 * </p>
 */
public class Not extends Symbol {

    private List<String> symbolStrings = Arrays.asList("!", "not", "NOT");
    private String symbolText = symbolStrings.get(0);

    /**
     * <p> インスタンス状態が最初に設定した状態であれば真を返す真偽値判定を行います。 </p>
     *
     * <p>
     *     インスタンス状態であるかどうかを文字列で判定できます。
     *     {@link Symbol#is(String)}をオーバーライドしていますが、ここでは実引数である{@code text}の文字列を{@link #symbolText}に格納しています。
     * </p>
     *
     * @param text インスタンス状態であるかどうかの文字列
     * @return インスタンス状態が判定値と等しい場合は真を返す真偽値
     */
    @Override
    public boolean is(String text) {
        boolean isThisSymbol = false;
        if (adjustSymbolStrings().contains(text)) {
            isThisSymbol = true;
            symbolText = text;
        }
        return isThisSymbol;
    }

    /**
     * <p> 演算子の文字列のリストを返します。 </p>
     *
     * <p>
     *     インスタンス状態における演算子が一意に求まらないため、複数の要素を持つリストを返します。
     * </p>
     *
     * @return インスタンス状態における演算子
     */
    @Override
    public List<String> adjustSymbolStrings() {
        return symbolStrings;
    }

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
     * <p> 演算子の文字列を取得します。 </p>
     *
     * <p>
     *     {@link #is(String)}を実行後にこのメソッドを呼出した場合は、{@link #is(String)}で受取った文字列を返します。
     *     もし{@link #is(String)}を実行せずにこのメソッドを呼出した場合は、{@link #symbolStrings}の0番目の要素を返します。
     * </p>
     *
     * @return 演算子の文字列<br>{@code null}および{@code ""}（空文字）無し
     */
    @Override
    public String toString() {
        return symbolText;
    }
}
