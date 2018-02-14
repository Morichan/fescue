package feature.property;

import feature.value.expression.Expression;

/**
 * <p> Redefinesクラス </p>
 *
 * <p>
 *     プロパティにおけるRedefinesクラスです。
 * </p>
 */
public class Redefines implements Property {

    private Expression propertyName;

    /**
     * <p> 式コンストラクタ </p>
     *
     * <p>
     *     プロパティとして式を設定します。
     * </p>
     *
     * @param expression プロパティ名の式
     */
    public Redefines(Expression expression) {
        if (expression == null) throw new IllegalArgumentException();
        propertyName = expression;
    }

    /**
     * <p> redefinesプロパティの文字列を取得します。 </p>
     *
     * @return {@code "redefines " + }{@link #propertyName}<br>{@code null}および空文字なし
     */
    @Override
    public String toString() {
        return "redefines " + propertyName;
    }
}
