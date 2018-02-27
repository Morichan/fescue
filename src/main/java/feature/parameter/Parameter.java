package feature.parameter;

import feature.name.Name;
import feature.type.Type;

/**
 * <p> パラメータクラス </p>
 *
 * <p>
 *     使い方を次に示します。
 * </p>
 *
 * <pre>
 *     {@code
 *     Parameter parameter = new Parameter();
 *
 *     parameter.setName(new Name("parameterName"));
 *     parameter.setType(new Type("void"));
 *
 *     // ...
 *
 *     Name parameterName = attribute.getName();
 *     Type parameterType = attribute.getType();
 *
 *     System.out.println(parameterName); // "parameterName"
 *     System.out.println(parameterType); // "void"
 *     }
 * </pre>
 */
public class Parameter {
    private Name parameterName;
    private Type parameterType;

    /**
     * <p> パラメータ名を設定します。 </p>
     *
     * <p>
     *     設定する前に{@code null}判定を行い、{@code null}の場合は{@link IllegalArgumentException}を投げます（{@link #checkIllegalArgument(Object)}参照）。
     * </p>
     *
     * @param parameterName パラメータ名<br>{@code null}不可
     */
    public void setName(Name parameterName) {
        checkIllegalArgument(parameterName);
        this.parameterName = parameterName;
    }

    /**
     * <p> パラメータ名を取得します。 </p>
     *
     * <p>
     *     取得する前に、フィールドとして名前インスタンスを保持しているかどうかを判定します。
     *     保持していない場合は{@link IllegalStateException}を投げます（{@link #checkIllegalState(Object)}参照）。
     * </p>
     *
     * @return 名前<br>{@code null}なし
     * @throws IllegalStateException パラメータ名が存在しないことを示す要素不所持例外
     */
    public Name getName() throws IllegalStateException {
        checkIllegalState(parameterName);
        return parameterName;
    }

    /**
     * <p> パラメータの型を設定します。 </p>
     *
     * <p>
     *     設定する前に{@code null}判定を行い、{@code null}の場合は{@link IllegalArgumentException}を投げます（{@link #checkIllegalArgument(Object)}参照）。
     * </p>
     *
     * @param parameterType パラメータの型<br>{@code null}不可
     */
    public void setType(Type parameterType) {
        checkIllegalArgument(parameterType);
        this.parameterType = parameterType;
    }

    /**
     * <p> パラメータの型を取得します。 </p>
     *
     * <p>
     *     取得する前に、フィールドとして型インスタンスを保持しているかどうかを判定します。
     *     保持していない場合は{@link IllegalStateException}を投げます（{@link #checkIllegalState(Object)}参照）。
     * </p>
     *
     * @return パラメータの型<br>{@code null}なし
     * @throws IllegalStateException パラメータの型が存在しないことを示す要素不所持例外
     */
    public Type getType() throws IllegalStateException {
        checkIllegalState(parameterType);
        return parameterType;
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
