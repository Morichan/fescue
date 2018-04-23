package sculptor;

import evaluation.AttributeEvaluation;
import feature.Attribute;
import feature.multiplicity.Bounder;
import feature.multiplicity.MultiplicityRange;
import feature.name.Name;
import feature.type.Type;
import feature.value.DefaultValue;
import feature.value.expression.OneIdentifier;
import feature.visibility.Visibility;
import org.antlr.v4.runtime.ParserRuleContext;
import parser.ClassFeatureParser;

import java.util.concurrent.Callable;

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

    /**
     * <p> 属性文コンテキストから{@link Attribute}インスタンスを形成します。 </p>
     *
     * @return 属性文コンテキストから生成した {@link Attribute}インスタンス
     */
    public Attribute carve() {
        Attribute feature = new Attribute(new Name("attribute"));

        for (int i = 0; i < attribute.getChildCount(); i++) {
            ParserRuleContext ctx = (ParserRuleContext) attribute.getChild(i);

            if (ctx instanceof ClassFeatureParser.VisibilityContext) {
                feature.setVisibility(Visibility.choose(ctx.getText()));

            } else if (ctx instanceof ClassFeatureParser.NameContext) {
                feature.setName(new Name(ctx.getText()));

            } else if (ctx instanceof ClassFeatureParser.DividedContext) {
                feature.setDerived(true);

            } else if (ctx instanceof ClassFeatureParser.PropTypeContext) {
                feature.setType(new Type(ctx.getChild(0).getChild(1).getText()));

            } else if (ctx instanceof ClassFeatureParser.MultiplicityRangeContext) {
                if (ctx.getChild(1) instanceof ClassFeatureParser.UpperContext) {
                    feature.setMultiplicityRange(new MultiplicityRange(new Bounder(ctx.getChild(1).getText())));
                } else {
                    feature.setMultiplicityRange(new MultiplicityRange(
                            new Bounder(ctx.getChild(1).getText()), new Bounder(ctx.getChild(3).getText())));
                }

            } else if (ctx instanceof ClassFeatureParser.DefaultValueContext) {
                feature.setDefaultValue(new DefaultValue(new OneIdentifier(ctx.getChild(1).getText())));
            }
        }

        return feature;
    }
}
