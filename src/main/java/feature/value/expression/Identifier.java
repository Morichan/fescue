package feature.value.expression;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p> 式の1トークンである数値または文字列または変数名（識別子）クラス </p>
 *
 * <p>
 *     使い方を次に示します。
 * </p>
 *
 * <pre>
 *     {@code
 *     Identifier numberIdentifier = new Identifier(10);
 *     System.out.println(numberIdentifier); // "10"
 *
 *     Identifier stringIdentifier = new Identifier("\"string text\"");
 *     System.out.println(stringIdentifier); // "\"string text\""
 *
 *     Identifier variableNameIdentifier = new Identifier("variableName");
 *     System.out.println(variableNameIdentifier); // "variableName"
 *     }
 * </pre>
 */
public class Identifier {
    private String identifier;
    private boolean isValue = true;

    private Pattern stringEnclosedBySingleQuotation = Pattern.compile("^'(.)*'$");
    private Pattern stringEnclosedByDoubleQuotation = Pattern.compile("^\"(.)*\"$");

    /**
     * <p> デフォルトコンストラクタ </p>
     *
     * <p>
     *     @deprecated
     *     初期化時に値を入れないため、推奨しません。
     *     {@link #Identifier(int)}または{@link #Identifier(String)}をご利用ください。
     * </p>
     */
    @Deprecated
    public Identifier() {}

    /**
     * <p> 数値を設定するコンストラクタ </p>
     *
     * <p>
     *     内部的には{@link #set(int)}を呼出しています。
     * </p>
     *
     * @param number 設定する数値
     */
    public Identifier(int number) {
        set(number);
    }

    /**
     * <p> 変数名または文字列を設定するコンストラクタ </p>
     *
     * <p>
     *     内部的には{@link #set(String)}を呼出しています。
     * </p>
     *
     * @param text 設定する変数名または文字列 {@code null}および{@code ""}（空文字）不可
     */
    public Identifier(String text) {
        set(text);
    }

    /**
     * <p> 数値を設定します。 </p>
     *
     * <p>
     *     数値を文字列に設定し、値かどうかを真に設定します。
     * </p>
     *
     * @param number 設定する数値
     */
    public void set(int number) {
        this.identifier = Integer.toString(number);
        isValue = true;
    }

    /**
     * <p> 変数名または文字列を設定します。 </p>
     *
     * <p>
     *     文字列とは、{@code '}（シングルクオーテーション）または{@code "}（ダブルクオーテーション）で囲んだ文字列のことです（例、{@code 'c', "stringName"}）。
     *     文字列か変数名かの確認には{@link #isValue(String)}を利用しています。
     * </p>
     *
     * <p>
     *     最初に入力した文字列の{@code null}の場合は真を返す真偽値判定と、空文字の場合は真を返す真偽値判定を行います。
     *     どちらか一方が真の場合は{@link IllegalArgumentException}を投げます。
     * </p>
     *
     * @param text 設定する変数名または文字列 {@code null}および{@code ""}（空文字）不可
     */
    public void set(String text) {
        if (text == null || text.length() <= 0) throw new IllegalArgumentException();
        this.identifier = text;
        isValue = isValue(identifier);
    }

    /**
     * 値の場合は真を示す真偽値を返します。
     *
     * @return 値の場合は真を示す真偽値
     */
    public boolean isValue() {
        return isValue;
    }

    /**
     * <p> 識別子の文字列を取得します。 </p>
     *
     * <p>
     *     {@link #isValue}の{@code null}の場合は真を返す真偽値判定を行います。
     *     真の場合は{@link IllegalStateException}を投げます。
     * </p>
     *
     * @return 識別子の文字列 {@code null}および{@code ""}（空文字）無し
     */
    @Override
    public String toString() {
        if (identifier == null) throw new IllegalStateException();
        return identifier;
    }



    /**
     * <p> 変数名か文字列かを判定します。 </p>
     *
     * <p>
     *     文字列とは、{@code '}（シングルクオーテーション）または{@code "}（ダブルクオーテーション）で囲んだ文字列のことです（例、{@code 'c', "stringName"}）。
     *     正規表現を用いて囲まれている場合は真を返します。
     * </p>
     *
     * @param text 変数名か文字列かを判定する文字列
     * @return 文字列であれば真を返す真偽値
     */
    boolean isValue(String text) {
        Matcher singleQuotationMatcher = stringEnclosedBySingleQuotation.matcher(text);
        Matcher doubleQuotationMatcher = stringEnclosedByDoubleQuotation.matcher(text);

        return singleQuotationMatcher.matches() || doubleQuotationMatcher.matches();
    }
}
