package feature.name;

/**
 * <p> 名前クラス </p>
 *
 * <p>
 *     使い方を次に示します。
 * </p>
 *
 * <pre>
 *     {@code
 *     Name name = new Name("initializedName");
 *
 *     System.out.println(name); // "initializedName"
 *     }
 * </pre>
 */
public class Name {
    private String nameText;
    private boolean isSetName = false;

    /**
     * <p> デフォルトコンストラクタ </p>
     *
     * <p>
     *     @deprecated
     *     現在は使ってもいいですが、推奨しません。
     *     {@link #Name(String)}をご利用ください。
     * </p>
     */
    @Deprecated
    public Name() {}

    /**
     * <p> 名前設定コンストラクタ </p>
     *
     * <p>
     *     名前の文字列を最初に設定します。
     *     設定時に{@code null}判定と空文字判定を行い、真の場合は{@link IllegalArgumentException}を投げます（{@link #checkStateBy(String)}参照）。
     * </p>
     *
     * @param text 名前の文字列<br>{@code null}および{@code ""}（空文字）不可
     */
    public Name(String text) {
        checkStateBy(text);

        nameText = text;
        isSetName = true;
    }

    /**
     * <p> 名前を設定します。 </p>
     *
     * <p>
     *     名前の文字列を最初に設定します。
     *     設定時に{@code null}判定と空文字判定を行い、真の場合は{@link IllegalArgumentException}を投げます（{@link #checkStateBy(String)}参照）。
     * </p>
     *
     * @param text 名前の文字列<br>{@code null}および{@code ""}（空文字）不可
     */
    public void setNameText(String text) {
        checkStateBy(text);

        nameText = text;
        isSetName = true;
    }

    /**
     * <p> 名前の文字列を取得します。 </p>
     *
     * <p>
     *     取得時にインスタンスの状態判定（名前の文字列が正常である場合は真を返す真偽値判定）を行います。
     *     名前の文字列が設定されていない等の異常状態である場合は{@link IllegalStateException}を投げます（{@link #checkState()}参照）。
     * </p>
     *
     * @return 名前の文字列<br>{@code null}および{@code ""}（空文字）無し
     */
    public String getNameText() {
        checkState();

        return nameText;
    }

    /**
     * <p> 名前の文字列を取得します。 </p>
     *
     * <p>
     *     {@link #getNameText()}を呼び出しています。
     * </p>
     *
     * @return 名前の文字列<br>{@code null}および{@code ""}（空文字）無し（{@link #getNameText()}参照）
     */
    @Override
    public String toString() {
        return getNameText();
    }

    /**
     * 入力した文字列が{@code null}または{@code ""}（空文字）の場合は{@link IllegalArgumentException}を投げるインスタンス状態判定を行います。
     *
     * @param text 任意の文字列
     */
    private void checkStateBy(String text) {
        if (text == null || text.length() <= 0) {
            isSetName = false;
            throw new IllegalArgumentException();
        }
    }

    /**
     * インスタンス状態が異常状態である場合は{@link IllegalStateException}を投げるインスタンス状態判定を行います。
     */
    private void checkState() {
        if (! isSetName) throw new IllegalStateException();
    }
}
