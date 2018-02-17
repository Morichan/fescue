package feature.value.expression;

/**
 * <p> 1識別子クラス </p>
 *
 * <p>
 *     使い方を次に示します。
 * </p>
 *
 * <pre>
 *     {@code
 *     OneIdentifier oneNumber = new OneIdentifier(1)
 *     System.out.println(identifierOne); // "1"
 *
 *     OneIdentifier stringValue = new OneIdentifier("\"string text\"")
 *     System.out.println(stringValue); // "\"string text\""
 *
 *     OneIdentifier variable = new OneIdentifier("variableName")
 *     System.out.println(variable); // "variableName"
 *     }
 * </pre>
 */
public class OneIdentifier implements Expression {
    private Identifier identifier;

    /**
     * <p> 数値コンストラクタ </p>
     *
     * <p>
     *     数値の{@link Identifier}インスタンスを生成します。
     * </p>
     *
     * @param number 数値
     */
    public OneIdentifier(int number) {
        identifier = new Identifier(number);
    }

    /**
     * <p> 文字列または変数名コンストラクタ </p>
     *
     * <p>
     *     文字列または変数名の{@link Identifier}インスタンスを生成します。
     * </p>
     *
     * @param text 文字列または変数名
     */
    public OneIdentifier(String text) {
        identifier = new Identifier(text);
    }

    /**
     * <p> 識別子の文字列を取得します。 </p>
     *
     * @return 識別子の文字列<br>{@code null}および{@code ""}なし
     */
    @Override
    public String toString() {
        return identifier.toString();
    }
}
