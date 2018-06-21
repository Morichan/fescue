package sculptor;

import evaluation.OperationEvaluation;
import feature.Operation;
import feature.direction.*;
import feature.multiplicity.Bounder;
import feature.multiplicity.MultiplicityRange;
import feature.name.Name;
import feature.parameter.Parameter;
import feature.property.*;
import feature.type.Type;
import feature.value.DefaultValue;
import feature.value.expression.*;
import feature.value.expression.symbol.Symbol;
import feature.visibility.Visibility;
import org.antlr.v4.runtime.ParserRuleContext;
import parser.ClassFeatureParser;

import java.util.ArrayList;
import java.util.List;

/**
 * <p> 操作彫刻家 </p>
 *
 * <p>
 *     操作文を入力することで、{@link feature.Operation}クラスのインスタンス化を行います。
 * </p>
 *
 * <pre>
 *     {@code
 *     import sculptor.OperationSculptor;
 *     import feature.Operation;
 *
 *     class Main {
 *         // 使い方
 *         public static void main(String[] args) {
 *             OperationSculptor sculptor = new OperationSculptor();
 *             Operation operation;
 *
 *             // 操作文を構文解析します
 *             sculptor.parse("+ setNumber(number : int) : void");
 *             // 構文解析結果をOperationクラスのインスタンスとして出力します
 *             operation = sculptor.carve();
 *
 *             // 全体像を出力します
 *             System.out.println(operation); // "+ setNumber(number : int) : void"
 *             // 各項目を出力します
 *             System.out.println(operation.getName()); // "setNumber"
 *             System.out.println(operation.getReturnType()); // "void"
 *         }
 *     }
 *     }
 * </pre>
 */
public class OperationSculptor {

    private OperationEvaluation evaluation;
    private ClassFeatureParser.OperationContext operation;

    /**
     * <p> 構文解析を行う。 </p>
     *
     * <p>
     *     操作文を入力すると構文解析を行います。
     *     ここでいう構文解析とは、字句解析と構文解析を含みます。
     * </p>
     *
     * <p>
     *     {@code null}および{@code ""}（空文字）を入力すると{@link IllegalArgumentException}を投げます。
     * </p>
     *
     * @param operationText 操作文 <br> {@code null}と{@code ""}（空文字）不可
     */
    public void parse(String operationText) {
        if (operationText == null || operationText.isEmpty()) throw new IllegalArgumentException();

        evaluation = new OperationEvaluation();
        evaluation.setText(operationText);
        evaluation.walk();

        operation = evaluation.getContext();
    }

    /**
     * <p> 操作文コンテキストを取得します。 </p>
     *
     * <p>
     *     コンテキストを取得したい場合はこのメソッドを利用してください。
     *     テストコード以外ではあまり使わないと思います。
     * </p>
     *
     * @return 操作文コンテキスト
     */
    public ParserRuleContext getContext() {
        if (operation == null) throw new IllegalStateException();
        return operation;
    }

    /**
     * <p> 操作文コンテキストから{@link feature.Operation}インスタンスを形成します。 </p>
     *
     * @return 操作文コンテキストから生成した {@link Operation}インスタンス
     */
    public Operation carve() {
        Operation feature = new Operation(new Name("operation"));

        for (int i = 0; i < operation.getChildCount(); i++) {
            ParserRuleContext ctx = (ParserRuleContext) operation.getChild(i);

            if (ctx instanceof ClassFeatureParser.VisibilityContext) {
                feature.setVisibility(Visibility.choose(ctx.getText()));

            } else if (ctx instanceof ClassFeatureParser.NameContext) {
                feature.setName(new Name(ctx.getText()));

            } else if (ctx instanceof ClassFeatureParser.ReturnTypeContext) {
                if (ctx.getChild(0) instanceof ClassFeatureParser.TypeContext) {
                    feature.setReturnType(new Type(ctx.getChild(0).getChild(1).getText()));
                } else { // if (ctx.getChild(1).getText().equals("void")) {
                    feature.setReturnType(new Type(ctx.getChild(1).getText()));
                }

            } else if (ctx instanceof ClassFeatureParser.ParameterListContext) {
                if (ctx.getChildCount() > 2)
                    feature.setParameters(extractParameters((ClassFeatureParser.ParameterListContext) ctx));

            } else { // if (ctx instanceof ClassFeatureParser.OperPropertiesContext) {
                feature.setProperties(extractOperationProperties((ClassFeatureParser.OperPropertiesContext) ctx));
            }
        }

        return feature;
    }



    /**
     * <p> 操作文におけるパラメータコンテキストから{@link Parameter}インスタンスリストを形成します。 </p>
     *
     * @return 操作文におけるパラメータコンテキストから生成した {@link Operation}インスタンスリスト
     */
    private List<Parameter> extractParameters(ClassFeatureParser.ParameterListContext ctx) {
        List<Parameter> parameters = new ArrayList<>();

        for (int i = 1; i < ctx.getChildCount(); i += 2) {
            Parameter param = new Parameter(new Name("parameter"));
            for (int j = 0; j < ctx.getChild(i).getChildCount(); j++) {
                ParserRuleContext paramItem = (ParserRuleContext) ctx.getChild(i).getChild(j);

                if (paramItem instanceof ClassFeatureParser.ParameterNameContext) {
                    param.setName(new Name(paramItem.getText()));

                } else if (paramItem instanceof ClassFeatureParser.TypeExpressionContext) {
                    param.setType(new Type(paramItem.getChild(0).getChild(1).getText()));

                } else if (paramItem instanceof ClassFeatureParser.DirectionContext) {
                    if (paramItem.getText().equals("in")) param.setDirection(new In(true));
                    else if (paramItem.getText().equals("out")) param.setDirection(new Out());
                    else if (paramItem.getText().equals("inout")) param.setDirection(new InOut());
                    else param.setDirection(new Return());

                } else if (paramItem instanceof ClassFeatureParser.MultiplicityRangeContext) {
                    if (paramItem.getChild(1) instanceof ClassFeatureParser.UpperContext) {
                        param.setMultiplicityRange(new MultiplicityRange(new Bounder(new OneIdentifier(paramItem.getChild(1).getText()))));
                    } else {
                        param.setMultiplicityRange(new MultiplicityRange(
                                new Bounder(new OneIdentifier(paramItem.getChild(1).getText())), new Bounder(new OneIdentifier(paramItem.getChild(3).getText()))));
                    }

                } else if (paramItem instanceof ClassFeatureParser.DefaultValueContext) {
                    param.setDefaultValue(new DefaultValue(
                            createExpression((ClassFeatureParser.ExpressionContext) paramItem.getChild(1))));

                } else { // if (paramItem instanceof ClassFeatureParser.ParamPropertiesContext) {
                    param.setProperties(extractParamProperties((ClassFeatureParser.PropertiesContext) paramItem.getChild(0)));
                }
            }
            parameters.add(param);
        }

        return parameters;
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
     *     このメソッドは{@link AttributeSculptor#createExpression(ClassFeatureParser.ExpressionContext)}と完全に一緒です。
     * </p>
     *
     * @param ctx 式コンテキスト <br> {@code null}については{@link NullPointerException}を投げるはず
     * @return 式インスタンス <br> {@code null}の可能性なし
     */
    private Expression createExpression(ClassFeatureParser.ExpressionContext ctx) {
        Expression expression;

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
     *     このメソッドは{@link AttributeSculptor#extractExpressionsFromArguments(ClassFeatureParser.ArgumentsContext)}と完全に一緒です。
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
     * <p> パラメータにおけるプロパティインスタンスリストを生成します。 </p>
     *
     * <p>
     *     パラメータにおけるプロパティの文字列で判断し、その文字列に対応するインスタンスをリスト化します。
     *     式が付随するプロパティについては{@link #extractExpressionFromProperty(ClassFeatureParser.PropertyNameContext)}を用います。
     * </p>
     *
     * <p>
     *     このメソッドは{@link AttributeSculptor#extractProperties(ClassFeatureParser.PropertiesContext)}と完全に一緒です。
     * </p>
     *
     * @param ctx パラメータにおけるプロパティコンテキスト <br> {@code null}については{@link NullPointerException}を投げるはず
     * @return プロパティインスタンスリスト <br> リストの要素数が{@code 0}の可能性あり
     */
    private List<Property> extractParamProperties(ClassFeatureParser.PropertiesContext ctx) {
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
     * <p> パラメータにおけるプロパティの式を抽出します。 </p>
     *
     * <p>
     *     パラメータにおけるプロパティの式を{@link #createExpression(ClassFeatureParser.ExpressionContext)}を用いて抽出します。
     * </p>
     *
     * <p>
     *     このメソッドは{@link AttributeSculptor#extractExpressionFromProperty(ClassFeatureParser.PropertyNameContext)}と完全に一緒です。
     *     また処理内容は{@link #extractExpressionFromProperty(ClassFeatureParser.OperNameContext)}と完全に一緒です。
     * </p>
     *
     * @param ctx パラメータにおけるプロパティコンテキスト <br> {@code null}については{@link NullPointerException}を投げるはず
     * @return 式インスタンス <br> {@code null}の可能性なし
     */
    private Expression extractExpressionFromProperty(ClassFeatureParser.PropertyNameContext ctx) {
        if (ctx.getChild(0) instanceof ClassFeatureParser.ExpressionContext) {
            return createExpression((ClassFeatureParser.ExpressionContext) ctx.getChild(0));
        } else {
            return new OneIdentifier(ctx.getChild(0).getChild(0).getText());
        }
    }

    /**
     * <p> 操作におけるプロパティインスタンスリストを生成します。 </p>
     *
     * <p>
     *     操作におけるプロパティの文字列で判断し、その文字列に対応するインスタンスをリスト化します。
     *     式が付随するプロパティについては{@link #extractExpressionFromProperty(ClassFeatureParser.OperNameContext)}を用います。
     * </p>
     *
     * @param ctx 操作におけるプロパティコンテキスト <br> {@code null}については{@link NullPointerException}を投げるはず
     * @return プロパティインスタンスリスト <br> リストの要素数が{@code 0}の可能性あり
     */
    private List<Property> extractOperationProperties(ClassFeatureParser.OperPropertiesContext ctx) {
        List<Property> properties = new ArrayList<>();

        for (int i = 1; i < ctx.getChildCount(); i += 2) {
            String propertyString = ctx.getChild(i).getChild(0).getText();
            if (propertyString.equals("redefines")) {
                properties.add(new Redefines(
                        extractExpressionFromProperty((ClassFeatureParser.OperNameContext) ctx.getChild(i).getChild(1))));
            } else if (propertyString.equals("query")) {
                properties.add(new Query());
            } else if (propertyString.equals("ordered")) {
                properties.add(new Ordered());
            } else { // if (propertyString.equals("unique")) {
                properties.add(new Unique());
            }
        }

        return properties;
    }

    /**
     * <p> 操作におけるプロパティの式を抽出します。 </p>
     *
     * <p>
     *     操作におけるプロパティの式を{@link #createExpression(ClassFeatureParser.ExpressionContext)}を用いて抽出します。
     * </p>
     *
     * <p>
     *     このメソッドにおける処理内容は{@link AttributeSculptor#extractExpressionFromProperty(ClassFeatureParser.PropertyNameContext)}
     *     および{@link #extractExpressionFromProperty(ClassFeatureParser.PropertyNameContext)}と完全に一緒です。
     * </p>
     *
     * @param ctx 操作におけるプロパティコンテキスト <br> {@code null}については{@link NullPointerException}を投げるはず
     * @return 式インスタンス <br> {@code null}の可能性なし
     */
    private Expression extractExpressionFromProperty(ClassFeatureParser.OperNameContext ctx) {
        if (ctx.getChild(0) instanceof ClassFeatureParser.ExpressionContext) {
            return createExpression((ClassFeatureParser.ExpressionContext) ctx.getChild(0));
        } else {
            return new OneIdentifier(ctx.getChild(0).getChild(0).getText());
        }
    }
}
