package io.github.morichan.fescue.sculptor;

import io.github.morichan.fescue.evaluation.AttributeEvaluation;
import io.github.morichan.fescue.feature.Attribute;
import io.github.morichan.fescue.feature.multiplicity.Bounder;
import io.github.morichan.fescue.feature.multiplicity.MultiplicityRange;
import io.github.morichan.fescue.feature.name.Name;
import io.github.morichan.fescue.feature.property.*;
import io.github.morichan.fescue.feature.type.Type;
import io.github.morichan.fescue.feature.value.DefaultValue;
import io.github.morichan.fescue.feature.value.expression.*;
import io.github.morichan.fescue.feature.value.expression.symbol.Symbol;
import io.github.morichan.fescue.feature.visibility.Visibility;
import org.antlr.v4.runtime.ParserRuleContext;
import io.github.morichan.fescue.parser.ClassFeatureParser;

import java.util.ArrayList;
import java.util.List;

/**
 * <p> 属性彫刻家 </p>
 *
 * <p>
 *     属性文を入力することで、{@link io.github.morichan.fescue.feature.Attribute}クラスのインスタンス化を行います。
 * </p>
 *
 * <pre>
 *     {@code
 *     import io.github.morichan.fescue.sculptor.AttributeSculptor;
 *     import io.github.morichan.fescue.feature.Attribute;
 *
 *     class Main {
 *         // 使い方
 *         public static void main(String[] args) {
 *             AttributeSculptor sculptor = new AttributeSculptor();
 *             Attribute attribute;
 *
 *             // 属性文を構文解析します
 *             sculptor.parse("- number : int");
 *             // 構文解析結果をAttributeクラスのインスタンスとして出力します
 *             attribute = sculptor.carve();
 *
 *             // 全体像を出力します
 *             System.out.println(attribute); // "- number : int"
 *             // 各項目を出力します
 *             System.out.println(attribute.getName()); // "number"
 *             System.out.println(attribute.getType()); // "int"
 *         }
 *     }
 *     }
 * </pre>
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
                    feature.setMultiplicityRange(new MultiplicityRange(new Bounder(new OneIdentifier(ctx.getChild(1).getText()))));
                } else {
                    feature.setMultiplicityRange(new MultiplicityRange(
                            new Bounder(new OneIdentifier(ctx.getChild(1).getText())), new Bounder(new OneIdentifier(ctx.getChild(3).getText()))));
                }

            } else if (ctx instanceof ClassFeatureParser.DefaultValueContext) {
                feature.setDefaultValue(new DefaultValue(
                        createExpression((ClassFeatureParser.ExpressionContext) ctx.getChild(1))));

            } else { // if (ctx instanceof ClassFeatureParser.PropModifiersContext) {
                feature.setProperties(extractProperties((ClassFeatureParser.PropertiesContext) ctx.getChild(0)));
            }
        }

        return feature;
    }



    /**
     * <p> 式インスタンスを生成します。 </p>
     *
     * <p>
     *     式インスタンスを再帰的に生成します。
     *     途中で{@link #extractExpressionsFromArguments(ClassFeatureParser.ArgumentsContext)}を使う可能性があります。
     *     ClassFeature.g4ファイルにおけるexpressionの項目を参考にしました。
     * </p>
     *
     * <p>
     *     このメソッドは{@link OperationSculptor#createExpression(ClassFeatureParser.ExpressionContext)}と完全に一緒です。
     * </p>
     *
     * @param ctx 式コンテキスト <br> {@code null}については{@link NullPointerException}を投げるはず
     * @return 式インスタンス <br> {@code null}の可能性なし
     */
    private Expression createExpression(ClassFeatureParser.ExpressionContext ctx) {
        Expression expression = null;

        if (ctx.getChildCount() == 1) {
            expression = new OneIdentifier(ctx.getText());

        } else if (ctx.getChildCount() == 2) {
            if (Symbol.isIncluded(ctx.getChild(0).getText())) {
                expression = new Monomial(ctx.getChild(0).getText(),
                        createExpression((ClassFeatureParser.ExpressionContext) ctx.getChild(1)));
            } else { // if (ctx.getChild(0).getClass() == ClassFeatureParser.ExpressionContext.class) {
                if (ctx.getChild(0).getChildCount() == 1) {
                    expression = new MethodCall(ctx.getChild(0).getText(),
                            extractExpressionsFromArguments((ClassFeatureParser.ArgumentsContext) ctx.getChild(1)));
                } else {
                    List<Expression> expressions = new ArrayList<>();
                    if (ctx.getChild(1).getChildCount() >= 3) {
                        for (int i = 0; i < ctx.getChild(1).getChild(1).getChildCount(); i += 2) {
                            expressions.add(createExpression((ClassFeatureParser.ExpressionContext) ctx.getChild(1).getChild(1).getChild(i)));
                        }
                    }
                    expression = new Binomial(ctx.getChild(0).getChild(1).getText(),
                            createExpression((ClassFeatureParser.ExpressionContext) ctx.getChild(0).getChild(0)),
                            new MethodCall(ctx.getChild(0).getChild(2).getText(), expressions));
                }
            }

        } else {
            if (ctx.getChild(1).getClass() == ClassFeatureParser.ExpressionContext.class) {
                expression = new ExpressionWithParen(createExpression((ClassFeatureParser.ExpressionContext) ctx.getChild(1)));
            } else if (ctx.getChild(1).getText().equals(".")) {
                // ANTLR文法ファイル上ではここに入るはずだが決して入ることはない
                // if (ctx.getChild(2).getClass() == ClassFeatureParser.ExplicitGenericInvocationSuffixContext.class) {
                //     expression = new Binomial(ctx.getChild(1).getText(),
                //             createExpression((ClassFeatureParser.ExpressionContext) ctx.getChild(0)),
                //             new MethodCall(ctx.getChild(2).getChild(0).getText(),
                //                     extractExpressionsFromArguments((ClassFeatureParser.ArgumentsContext) ctx.getChild(2).getChild(1))));
                // } else {
                    expression = new Binomial(ctx.getChild(1).getText(),
                            createExpression((ClassFeatureParser.ExpressionContext) ctx.getChild(0)),
                            new OneIdentifier(ctx.getChild(2).getText()));
                // }
            } else {
                expression = new Binomial(ctx.getChild(1).getText(),
                        createExpression((ClassFeatureParser.ExpressionContext) ctx.getChild(0)),
                        createExpression((ClassFeatureParser.ExpressionContext) ctx.getChild(2)));
            }
        }

        return expression;
    }

    /**
     * <p> メソッドにおける各引数の式リストを抽出します。 </p>
     *
     * <p>
     *     メソッドにおける各引数の式を{@link #createExpression(ClassFeatureParser.ExpressionContext)}を用いて再帰的に抽出します。
     * </p>
     *
     * <p>
     *     このメソッドは{@link OperationSculptor#extractExpressionsFromArguments(ClassFeatureParser.ArgumentsContext)}と完全に一緒です。
     * </p>
     *
     * @param ctx 引数コンテキスト <br> {@code null}については{@link NullPointerException}を投げるはず
     * @return 式インスタンスリスト <br> リストの要素数が{@code 0}の可能性あり
     */
    private List<Expression> extractExpressionsFromArguments(ClassFeatureParser.ArgumentsContext ctx) {
        List<Expression> expressions = new ArrayList<>();

        if (ctx.getChild(1).getClass() != ClassFeatureParser.ExpressionListContext.class) return expressions;

        ClassFeatureParser.ExpressionListContext ctxList = (ClassFeatureParser.ExpressionListContext) ctx.getChild(1);

        for(int i = 0; i < ctxList.getChildCount(); i += 2) {
            expressions.add(createExpression((ClassFeatureParser.ExpressionContext) ctxList.getChild(i)));
        }

        return expressions;
    }

    /**
     * <p> プロパティインスタンスリストを生成します。 </p>
     *
     * <p>
     *     プロパティの文字列で判断し、その文字列に対応するインスタンスをリスト化します。
     *     式が付随するプロパティについては{@link #extractExpressionFromProperty(ClassFeatureParser.PropertyNameContext)}を用います。
     * </p>
     *
     * <p>
     *     このメソッドは{@link OperationSculptor#extractParamProperties(ClassFeatureParser.PropertiesContext)}と完全に一緒です。
     * </p>
     *
     * @param ctx プロパティコンテキスト <br> {@code null}については{@link NullPointerException}を投げるはず
     * @return プロパティインスタンスリスト <br> リストの要素数が{@code 0}の可能性あり
     */
    private List<Property> extractProperties(ClassFeatureParser.PropertiesContext ctx) {
        List<Property> properties = new ArrayList<>();

        for (int i = 1; i < ctx.getChildCount(); i += 2) {
            String propertyString = ctx.getChild(i).getChild(0).getText();
            if (propertyString.equals("readOnly")) {
                properties.add(new ReadOnly());
            } else if (propertyString.equals("union")) {
                properties.add(new Union());
            } else if (propertyString.equals("subsets")) {
                properties.add(new Subsets(
                        extractExpressionFromProperty((ClassFeatureParser.PropertyNameContext) ctx.getChild(i).getChild(1))));
            } else if (propertyString.equals("redefines")) {
                properties.add(new Redefines(
                        extractExpressionFromProperty((ClassFeatureParser.PropertyNameContext) ctx.getChild(i).getChild(1))));
            } else if (propertyString.equals("ordered")) {
                properties.add(new Ordered());
            } else { // if (propertyString.equals("unique")) {
                properties.add(new Unique());
            }
        }

        return properties;
    }

    /**
     * <p> プロパティにおける式を抽出します。 </p>
     *
     * <p>
     *     プロパティにおける式を{@link #createExpression(ClassFeatureParser.ExpressionContext)}を用いて抽出します。
     * </p>
     *
     * <p>
     *     このメソッドは{@link OperationSculptor#extractExpressionFromProperty(ClassFeatureParser.PropertyNameContext)}と完全に一緒です。
     * </p>
     *
     * @param ctx プロパティコンテキスト <br> {@code null}については{@link NullPointerException}を投げるはず
     * @return 式インスタンス <br> {@code null}の可能性なし
     */
    private Expression extractExpressionFromProperty(ClassFeatureParser.PropertyNameContext ctx) {
        if (ctx.getChild(0) instanceof ClassFeatureParser.ExpressionContext) {
            return createExpression((ClassFeatureParser.ExpressionContext) ctx.getChild(0));
        } else {
            return new OneIdentifier(ctx.getChild(0).getChild(0).getText());
        }
    }
}
