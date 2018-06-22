package jp.ac.miyazaki_u.cs.earth.fescue.feature.multiplicity;

/**
 * <p> 多重度クラス </p>
 *
 * <p>
 *     使い方を次に示します。
 * </p>
 *
 * <pre>
 *     {@code
 *     MultiplicityRange upperOnly = new MultiplicityRange(new Bounder("*"));
 *     System.out.println(upperOnly); // "*"
 *
 *     MultiplicityRange lowerAndUpper = new MultiplicityRange(
 *             new Bounder(new OneIdentifier(0)), new Bounder(new OneIdentifier(1)));
 *     System.out.println(lowerAndUpper); // "0..1"
 *     }
 * </pre>
 */
public class MultiplicityRange {

    private Bounder lower;
    private Bounder upper;

    private boolean isUpperOnly;

    /**
     * <p> 上限のみのコンストラクタ </p>
     *
     * <p>
     *     上限を{@link Bounder}インスタンスで設定します。
     *     {@code null}を設定した場合は{@link IllegalArgumentException}を投げます。
     * </p>
     *
     * @param upperBound 上限<br>{@code null}不可
     */
    public MultiplicityRange(Bounder upperBound) {
        if (upperBound == null) throw new IllegalArgumentException();
        upper = upperBound;
        isUpperOnly = true;
    }

    /**
     * <p> 下限と上限のコンストラクタ </p>
     *
     * <p>
     *     下限と上限を{@link Bounder}インスタンスで設定します。
     *     上限に{@code null}を設定した場合は{@link IllegalArgumentException}を投げます。
     *     下限に{@code null}を設定した場合は{@link #MultiplicityRange(Bounder)}を実行した場合と同様の動作を行います。
     * </p>
     *
     * @param lowerBound 下限<br>{@code null}可（{@link #MultiplicityRange(Bounder)}と同様の動作）
     * @param upperBound 上限<br>{@code null}不可
     */
    public MultiplicityRange(Bounder lowerBound, Bounder upperBound) {
        if (upperBound == null) throw new IllegalArgumentException();
        lower = lowerBound;
        upper = upperBound;
        isUpperOnly = lower == null;
    }

    /**
     * <p> 上限のみの場合は真を返す真偽値判定を行います。 </p>
     *
     * @return 上限のみの場合は真を返す真偽値判定
     */
    public boolean isUpperOnly() {
        return isUpperOnly;
    }

    /**
     * <p> 多重度の文字列を取得します。 </p>
     *
     * <p>
     *     上限のみの場合は、{@code "upper"}のように、上限のみの文字列をそのまま返します。
     *     下限と上限を持つ場合は、{@code "lower..upper"}のように、上限と下限の間に範囲演算子{@code ".."}を追加した文字列を返します。
     * </p>
     *
     * @return 多重度の文字列<br>{@code null}および{@code ""}なし
     */
    @Override
    public String toString() {
        if (isUpperOnly) return upper.toString();
        else return lower + ".." + upper;
    }
}
