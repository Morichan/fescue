package feature;

import feature.name.Name;
import feature.type.Type;
import feature.value.DefaultValue;
import feature.visibility.Visibility;

import java.util.NoSuchElementException;

/**
 * <p> 属性クラス </p>
 *
 * <p>
 *     使い方を次に示します。
 * </p>
 *
 * <pre>
 *     {@code
 *     Attribute attribute = new Attribute();
 *
 *     attribute.setName(new Name("attributeName"));
 *     attribute.setVisibility(Visibility.choose("-"));
 *
 *     // ...
 *
 *     Name attributeName = attribute.getName();
 *     Visibility attributeVisibility = attribute.getVisibility();
 *
 *     System.out.println(attributeName); // "attributeName"
 *     System.out.println(attributeVisibility); // "-"
 *     }
 * </pre>
 */
public class Attribute {
    private Name name;
    private Visibility visibility;
    private Type type;
    private boolean isDerived = false;
    private DefaultValue value;

    /**
     * <p> 名前を設定します。 </p>
     *
     * <p>
     *     設定する前に{@code null}判定を行い、{@code null}の場合は{@link IllegalStateException}を投げます（{@link #checkNullPointer(Object)}参照）。
     * </p>
     *
     * @param name 名前 {@code null}不可
     */
    public void setName(Name name) {
        checkNullPointer(name);
        this.name = name;
    }

    /**
     * <p> 名前を取得します。 </p>
     *
     * <p>
     *     取得する前に、フィールドとして名前インスタンスを保持しているかどうかを判定します。
     *     保持していない場合は{@link NoSuchElementException}を投げます（{@link #checkNoSuchElement(Object)}参照）。
     * </p>
     *
     * @return 名前
     * @throws NoSuchElementException 名前が存在しないことを示す要素不所持例外
     */
    public Name getName() throws NoSuchElementException {
        checkNoSuchElement(name);
        return name;
    }

    /**
     * <p> 可視性を設定します。 </p>
     *
     * <p>
     *     設定する前に{@code null}判定を行い、{@code null}の場合は{@link IllegalStateException}を投げます（{@link #checkNullPointer(Object)}参照）。
     * </p>
     *
     * @param visibility 可視性 {@code null}不可
     */
    public void setVisibility(Visibility visibility) {
        checkNullPointer(visibility);
        this.visibility = visibility;
    }

    /**
     * <p> 可視性を取得します。 </p>
     *
     * <p>
     *     取得する前に、フィールドとして可視性インスタンスを保持しているかどうかを判定します。
     *     保持していない場合は{@link NoSuchElementException}を投げます（{@link #checkNoSuchElement(Object)}参照）。
     * </p>
     *
     * @return 可視性
     * @throws NoSuchElementException 可視性が存在しないことを示す要素不所持例外
     */
    public Visibility getVisibility() throws NoSuchElementException {
        checkNoSuchElement(visibility);
        return visibility;
    }

    /**
     * <p> 型を設定します。 </p>
     *
     * <p>
     *     設定する前に{@code null}判定を行い、{@code null}の場合は{@link IllegalStateException}を投げます（{@link #checkNullPointer(Object)}参照）。
     * </p>
     *
     * @param type 型 {@code null}不可
     */
    public void setType(Type type) {
        checkNullPointer(type);
        this.type = type;
    }

    /**
     * <p> 型を取得します。 </p>
     *
     * <p>
     *     取得する前に、フィールドとして型インスタンスを保持しているかどうかを判定します。
     *     保持していない場合は{@link NoSuchElementException}を投げます（{@link #checkNoSuchElement(Object)}参照）。
     * </p>
     *
     * @return 型
     * @throws NoSuchElementException 型が存在しないことを示す要素不所持例外
     */
    public Type getType() throws NoSuchElementException {
        checkNoSuchElement(type);
        return type;
    }

    /**
     * <p> 派生を真偽値で設定します。 </p>
     *
     * <p>
     *     文字列で設定する場合は{@link #setDerived(String)}を実行してください。
     * </p>
     *
     * @param isDerived 派生の場合は真を示す真偽値
     */
    public void setDerived(boolean isDerived) {
        this.isDerived = isDerived;
    }

    /**
     * <p> 派生を文字列で設定します。 </p>
     *
     * <p>
     *     設定する前に{@code null}判定および派生の文字列判定を行い、{@code null}の場合または{@code "/"}以外の文字列を入力した場合は{@link IllegalArgumentException}を投げます。
     *     真偽値で設定する場合は{@link #setDerived(boolean)}を実行してください。
     * </p>
     *
     * @param derivedText 派生の文字列 {@code null}不可
     */
    public void setDerived(String derivedText) {
        if (derivedText == null || ! derivedText.equals("/")) throw new IllegalArgumentException();
        isDerived = true;
    }

    /**
     * <p> 派生の場合は真を返す真偽値を取得します。 </p>
     *
     * <p>
     *     何も設定していない場合は偽を返します。
     * </p>
     *
     * @return 派生の場合は真を示す真偽値
     */
    public boolean isDerived() {
        return isDerived;
    }

    /**
     * <p> 既定値を設定します。 </p>
     *
     * <p>
     *     設定する前に{@code null}判定を行い、{@code null}の場合は{@link IllegalStateException}を投げます（{@link #checkNullPointer(Object)}参照）。
     * </p>
     *
     * @param defaultValue 既定値 {@code null}不可
     */
    public void setDefaultValue(DefaultValue defaultValue) {
        checkNullPointer(defaultValue);
        this.value = defaultValue;
    }

    /**
     * <p> 既定値を取得します。 </p>
     *
     * <p>
     *     取得する前に、フィールドとして既定値インスタンスを保持しているかどうかを判定します。
     *     保持していない場合は{@link NoSuchElementException}を投げます（{@link #checkNoSuchElement(Object)}参照）。
     * </p>
     *
     * @return 既定値
     * @throws NoSuchElementException 既定値が存在しないことを示す要素不所持例外
     */
    public DefaultValue getDefaultValue() throws NoSuchElementException {
        checkNoSuchElement(value);
        return value;
    }



    /**
     * <p> 外部から入力するオブジェクトの{@code null}判定を行います。 </p>
     *
     * <p>
     *     {@code null}の場合は{@link IllegalStateException}を投げます。
     * </p>
     *
     * @param object {@code null}判定を行いたいオブジェクト
     */
    private void checkNullPointer(Object object) {
        if (object == null) throw new IllegalStateException();
    }

    /**
     * <p> フィールドのオブジェクトを外部に出力する際に{@code null}判定を行います。 </p>
     *
     * <p>
     *     {@code null}の場合は{@link IllegalStateException}を投げます。
     * </p>
     *
     * @param object {@code null}判定を行いたいオブジェクト
     * @throws NoSuchElementException 要素が存在しないことを示す要素不所持例外
     */
    private void checkNoSuchElement(Object object) throws NoSuchElementException {
        if (object == null) throw new NoSuchElementException();
    }
}