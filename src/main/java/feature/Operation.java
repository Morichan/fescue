package feature;

import feature.name.Name;
import feature.type.Type;
import feature.visibility.Visibility;

/**
 * <p> 操作クラス </p>
 *
 * <p>
 *     使い方を次に示します。
 * </p>
 *
 * <pre>
 *     {@code
 *     Operation operation = new Operation();
 *
 *     operation.setName(new Name("operationName"));
 *     operation.setVisibility(Visibility.choose("+"));
 *
 *     // ...
 *
 *     Name operationName = operation.getName();
 *     Visibility operationVisibility = operation.getVisibility();
 *
 *     System.out.println(operationName); // "operationName"
 *     System.out.println(operationVisibility); // "+"
 *     }
 * </pre>
 */
public class Operation {
    private Name name;
    private Visibility visibility;
    private Type returnType;

    /**
     * <p> 名前を設定します。 </p>
     *
     * <p>
     *     設定する前に{@code null}判定を行い、{@code null}の場合は{@link IllegalArgumentException}を投げます（{@link #checkIllegalArgument(Object)}参照）。
     * </p>
     *
     * @param name 名前 {@code null}不可
     */
    public void setName(Name name) {
        checkIllegalArgument(name);
        this.name = name;
    }

    /**
     * <p> 名前を取得します。 </p>
     *
     * <p>
     *     取得する前に、フィールドとして名前インスタンスを保持しているかどうかを判定します。
     *     保持していない場合は{@link IllegalStateException}を投げます（{@link #checkIllegalState(Object)}参照）。
     * </p>
     *
     * @return 名前
     * @throws IllegalStateException 名前が存在しないことを示す要素不所持例外
     */
    public Name getName() {
        checkIllegalState(name);
        return name;
    }

    /**
     * <p> 可視性を設定します。 </p>
     *
     * <p>
     *     設定する前に{@code null}判定を行い、{@code null}の場合は{@link IllegalArgumentException}を投げます（{@link #checkIllegalArgument(Object)}参照）。
     * </p>
     *
     * @param visibility 可視性 {@code null}不可
     */
    public void setVisibility(Visibility visibility) {
        checkIllegalArgument(visibility);
        this.visibility = visibility;
    }

    /**
     * <p> 可視性を取得します。 </p>
     *
     * <p>
     *     取得する前に、フィールドとして可視性インスタンスを保持しているかどうかを判定します。
     *     保持していない場合は{@link IllegalStateException}を投げます（{@link #checkIllegalState(Object)}参照）。
     * </p>
     *
     * @return 可視性
     * @throws IllegalStateException 可視性が存在しないことを示す要素不所持例外
     */
    public Visibility getVisibility() throws IllegalStateException {
        checkIllegalState(visibility);
        return visibility;
    }

    /**
     * <p> 戻り値の型を設定します。 </p>
     *
     * <p>
     *     設定する前に{@code null}判定を行い、{@code null}の場合は{@link IllegalArgumentException}を投げます（{@link #checkIllegalArgument(Object)}参照）。
     * </p>
     *
     * @param type 戻り値の型 {@code null}不可
     */
    public void setReturnType(Type type) {
        checkIllegalArgument(type);
        this.returnType = type;
    }

    /**
     * <p> 戻り値の型を取得します。 </p>
     *
     * <p>
     *     取得する前に、フィールドとして戻り値の型インスタンスを保持しているかどうかを判定します。
     *     保持していない場合は{@link IllegalStateException}を投げます（{@link #checkIllegalState(Object)}参照）。
     * </p>
     *
     * @return 戻り値の型
     * @throws IllegalStateException 戻り値の型が存在しないことを示す要素不所持例外
     */
    public Type getReturnType() throws IllegalStateException {
        checkIllegalState(returnType);
        return returnType;
    }



    /**
     * <p> 外部から入力するオブジェクトの{@code null}判定を行います。 </p>
     *
     * <p>
     *     {@code null}の場合は{@link IllegalArgumentException}を投げます。
     * </p>
     *
     * @param object {@code null}判定を行いたいオブジェクト
     */
    private void checkIllegalArgument(Object object) {
        if (object == null) throw new IllegalArgumentException();
    }

    /**
     * <p> フィールドのオブジェクトを外部に出力する際に{@code null}判定を行います。 </p>
     *
     * <p>
     *     {@code null}の場合は{@link IllegalStateException}を投げます。
     * </p>
     *
     * @param object {@code null}判定を行いたいオブジェクト
     * @throws IllegalStateException 要素が存在しないことを示す要素不所持例外
     */
    private void checkIllegalState(Object object) throws IllegalStateException {
        if (object == null) throw new IllegalStateException();
    }
}