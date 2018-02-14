package feature.property;

import feature.value.expression.Expression;

/**
 * <p> Subsetsクラス </p>
 *
 * <p>
 *     プロパティにおけるSubsetsクラスです。
 * </p>
 */
public class Subsets implements Property {

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
    public Subsets(Expression expression) {
        if (expression == null) throw new IllegalArgumentException();
        propertyName = expression;
    }

    /**
     * <p> subsetsプロパティの文字列を取得します。 </p>
     *
     * @return {@code "subsets " + }{@link #propertyName}
     */
    @Override
    public String toString() {
        return "subsets " + propertyName;
    }
}
