package sculptor;

import evaluation.AttributeEvaluation;
import feature.Attribute;
import feature.name.Name;
import org.antlr.v4.runtime.ParserRuleContext;
import parser.ClassFeatureParser;

/**
 * <p> 属性彫刻家 </p>
 *
 * <p>
 *     属性文を入力することで、{@link feature.Attribute}クラスのインスタンス化を行います。
 * </p>
 */
public class AttributeSculptor {

    private AttributeEvaluation evaluation;
    private ClassFeatureParser.PropertyContext attribute;

    /**
     * <p> 構文解析を行う。 </p>
     *
     * <p>
     *     属性文を入力すると構文解析を行います。
     *     ここでいう構文解析とは、字句解析と構文解析を含みます。
     * </p>
     *
     * @param attributeText 属性文 <br> {@code null}不可
     */
    public void parse(String attributeText) {
        if (attributeText == null) throw new IllegalArgumentException();

        evaluation = new AttributeEvaluation();
        evaluation.setText(attributeText);
        evaluation.walk();

        attribute = evaluation.getContext();
    }

    /**
     * <p> 属性文コンテキストを取得します。 </p>
     *
     * <p>
     *     コンテキストを取得したい場合はこのメソッドを利用してください。
     *     テストコード以外ではあまり使わないと思います。
     * </p>
     *
     * @return 属性文コンテキスト
     */
    public ParserRuleContext getContext() {
        if (attribute == null) throw new IllegalStateException();
        return attribute;
    }

    public Attribute carve() {
        Attribute feature = new Attribute(new Name("attribute"));

        for (int i = 0; i < attribute.getChildCount(); i++) {
            if (attribute.getChild(i) instanceof ClassFeatureParser.NameContext) {
                feature.setName(new Name(attribute.getChild(i).getText()));
                break;
            }
        }

        return feature;
    }
}
