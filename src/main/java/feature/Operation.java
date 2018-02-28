package feature;

import feature.name.Name;
import feature.parameter.Parameter;
import feature.property.Property;
import feature.type.Type;
import feature.visibility.Visibility;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.StringJoiner;

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
    private List<Parameter> parameters = new ArrayList<>();
    private Type returnType;
    private List<Property> properties = new ArrayList<>();

    /**
     * <p> 名前設定コンストラクタ </p>
     *
     * <p>
     *     名前を最初に設定します。
     *     設定時に{@code null}判定と空文字判定を行い、真の場合は{@link IllegalArgumentException}を投げます（{@link #checkIllegalArgument(Object)}参照）。
     * </p>
     *
     * @param name プロパティ名<br>{@code null}および{@code ""}（空文字）不可
     */
    public Operation(Name name) {
        checkIllegalArgument(name);
        this.name = name;
    }

    /**
     * <p> 名前を設定します。 </p>
     *
     * <p>
     *     設定する前に{@code null}判定を行い、{@code null}の場合は{@link IllegalArgumentException}を投げます（{@link #checkIllegalArgument(Object)}参照）。
     * </p>
     *
     * @param name 名前<br>{@code null}不可
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
     * @param visibility 可視性<br>{@code null}不可
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
     * <p> パラメータを追加します。 </p>
     *
     * <p>
     *     追加する前に{@code null}判定を行い、{@code null}の場合は{@link IllegalArgumentException}を投げます（{@link #checkIllegalArgument(Object)}参照）。
     * </p>
     * @param parameter パラメータ<br>{@code null}不可
     */
    public void addParameter(Parameter parameter) {
        checkIllegalArgument(parameter);
        parameters.add(parameter);
    }

    /**
     * <p> 任意のパラメータを取得します。 </p>
     *
     * <p>
     *     取得する前に、設定値のインデックスをリストとして保持しているかどうかを判定します。
     *     保持していない場合は{@link IllegalStateException}を投げます（{@link #checkIllegalState(Object)}参照）。
     * </p>
     *
     * @param index パラメータリストのインデックス
     * @return パラメータ<br>{@code null}なし
     * @throws IllegalStateException パラメータが存在しないことを示す要素不所持例外
     */
    public Parameter getParameter(int index) throws IllegalStateException {
        try {
            return parameters.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalStateException();
        }
    }

    /**
     * <p> パラメータのリストを設定します。 </p>
     *
     * <p>
     *     設定する前に{@code null}判定を行い、{@code null}の場合は{@link IllegalArgumentException}を投げます（{@link #checkIllegalArgument(Object)}参照）。
     * </p>
     *
     * @param parameters パラメータのリスト<br>{@code null}不可
     */
    public void setParameters(List<Parameter> parameters) {
        checkIllegalArgument(parameters);
        this.parameters = parameters;
    }

    /**
     * <p> パラメータのリストを取得します。 </p>
     *
     * <p>
     *     取得する前に、パラメータのリストの要素数として1つ以上保持しているかどうかを判定します。
     *     保持していない場合は{@link IllegalStateException}を投げます（{@link #checkIllegalState(Object)}参照）。
     * </p>
     *
     * @return パラメータのリスト<br>{@code null}なし
     * @throws IllegalStateException パラメータのリストが存在しないことを示す要素不所持例外
     */
    public List<Parameter> getParameters() throws IllegalStateException {
        if (parameters.size() == 0) throw new IllegalStateException();
        return parameters;
    }

    /**
     * <p> 戻り値の型を設定します。 </p>
     *
     * <p>
     *     設定する前に{@code null}判定を行い、{@code null}の場合は{@link IllegalArgumentException}を投げます（{@link #checkIllegalArgument(Object)}参照）。
     * </p>
     *
     * @param type 戻り値の型<br>{@code null}不可
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
     * <p> プロパティを追加します。 </p>
     *
     * <p>
     *     追加する前に{@code null}判定を行い、{@code null}の場合は{@link IllegalArgumentException}を投げます（{@link #checkIllegalArgument(Object)}参照）。
     * </p>
     * @param property プロパティ<br>{@code null}不可
     */
    public void addProperty(Property property) {
        checkIllegalArgument(property);
        properties.add(property);
    }

    /**
     * <p> 任意のプロパティを取得します。 </p>
     *
     * <p>
     *     取得する前に、設定値のインデックスをリストとして保持しているかどうかを判定します。
     *     保持していない場合は{@link IllegalStateException}を投げます（{@link #checkIllegalState(Object)}参照）。
     * </p>
     *
     * @param index プロパティリストのインデックス
     * @return プロパティ<br>{@code null}なし
     * @throws IllegalStateException プロパティが存在しないことを示す要素不所持例外
     */
    public Property getProperty(int index) throws IllegalStateException {
        try {
            return properties.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalStateException();
        }
    }

    /**
     * <p> プロパティのリストを設定します。 </p>
     *
     * <p>
     *     設定する前に{@code null}判定を行い、{@code null}の場合は{@link IllegalArgumentException}を投げます（{@link #checkIllegalArgument(Object)}参照）。
     * </p>
     *
     * @param properties プロパティのリスト<br>{@code null}不可
     */
    public void setProperties(List<Property> properties) {
        checkIllegalArgument(properties);
        this.properties = properties;
    }

    /**
     * <p> プロパティのリストを取得します。 </p>
     *
     * <p>
     *     取得する前に、プロパティのリストの要素数として1つ以上保持しているかどうかを判定します。
     *     保持していない場合は{@link IllegalStateException}を投げます（{@link #checkIllegalState(Object)}参照）。
     * </p>
     *
     * @return プロパティのリスト<br>{@code null}なし
     * @throws IllegalStateException プロパティのリストが存在しないことを示す要素不所持例外
     */
    public List<Property> getProperties() throws IllegalStateException {
        if (properties.size() == 0) throw new IllegalStateException();
        return properties;
    }

    /**
     * <p> 操作の文字列を取得します。 </p>
     *
     * <p>
     *     文字列はUML2.0仕様書に準拠します。
     * </p>
     *
     * @return 操作の文字列<br>{@code null}および{@code ""}なし
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        if (visibility != null) {
            sb.append(visibility);
            sb.append(" ");
        }

        sb.append(name);

        if (parameters.size() > 0) {
            StringJoiner sj = new StringJoiner(", ");
            for (Parameter param : parameters) sj.add(param.toString());

            sb.append("(");
            sb.append(sj);
            sb.append(")");
        } else {
            sb.append("()");
        }

        if (returnType != null) {
            sb.append(" : ");
            sb.append(returnType);
        }

        if (properties.size() > 0) {
            StringJoiner sj = new StringJoiner(", ");
            for (Property prop : properties) sj.add(prop.toString());

            sb.append(" {");
            sb.append(sj);
            sb.append("}");
        }

        return sb.toString();
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