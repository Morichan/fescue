package usage;

import evaluation.FeatureEvalListener;
import evaluation.FeatureEvaluation;
import org.antlr.v4.runtime.InputMismatchException;
import parser.ClassFeatureParser;

import java.util.ArrayList;
import java.util.List;

/**
 * <p> 属性における要素の抽出クラス </p>
 *
 * <p> 簡単な使い方を次に示します。</p>
 *
 * <pre>
 *     {@code
 *     AttributeEvaluation evaluation = new AttributeEvaluation();
 *     evaluation.setText("- attribute : int");
 *     evaluation.walk();
 *
 *     String name = evaluation.extractName();
 *     String visibility = evaluation.extractVisibility();
 *     // ...
 *     }
 * </pre>
 *
 * <p>
 *     最初に属性文をセットし、走査してから各項目を抽出してください。
 *     順番を変えると例外として{@link IllegalArgumentException}や{@link org.antlr.v4.runtime.InputMismatchException}を投げます。
 * </p>
 */
public class AttributeEvaluation extends FeatureEvaluation {

    /**
     * 属性文コンテキスト
     */
    private ClassFeatureParser.PropertyContext context;

    /**
     * 属性文
     */
    private String attribute;

    /**
     * <p> 属性文を設定します。 </p>
     *
     * <p>
     *     {@code "\\.\\."}という文字列（2つ連続したドット）が存在した場合、その両端に半角スペースを挿入します。
     *     これは、多重度における下限と上限の指定の箇所をパースする際に、半角スペースが入っていない場合はうまくパースできないためです。
     *     詳しくは{@link #insertSpaceBothEndsOfRangeOperator(String)}を参照してください。
     * </p>
     *
     * @param text 設定する属性文 {@code null}不可（{@link #insertSpaceBothEndsOfRangeOperator(String)}で{@link IllegalArgumentException}を投げる。）
     */
    @Override
    public void setText(String text) {
        attribute = insertSpaceBothEndsOfRangeOperator(text);
    }

    /**
     * <p> 属性文を取得します。 </p>
     *
     * <p>
     *     {@link #setText(String)}を実行する前にこのメソッドを実行した場合は{@code null}を返します。
     * </p>
     *
     * @return 属性文 {@code null}の可能性あり
     */
    @Override
    public String getText() {
        return attribute;
    }



    /**
     * <p> 字句解析と構文解析を行い、構文解析木を走査します。 </p>
     *
     * <p>
     *     属性文を設定していない場合は{@link IllegalArgumentException}を投げます。
     * </p>
     */
    @Override
    public void walk() {
        initIfIsSameBetweenNameAndKeyword();
        if (attribute == null) throw new IllegalArgumentException();

        ClassFeatureParser parser = generateParser(attribute);
        FeatureEvalListener listener = walk(parser.property());
        context = listener.getProperty();

        try {
            extractName();
        } catch (InputMismatchException e) {
            setInputMismatchException(e);
        } catch (IllegalArgumentException e) {
            System.out.println("ERROR! Please Set Attribute!");
        }
    }



    /**
     * <p> 属性名を抽出します。 </p>
     *
     * <p>
     *     次の場合は例外を投げます。
     *     <ul>
     *         <li>属性文を設定していない場合（{@link #setText(String)}参照） : {@link IllegalArgumentException}</li>
     *         <li>設定した属性文が予約語と同じ文字列の場合 : {@link ClassFeatureParser.PropertyContext#exception}</li>
     *     </ul>
     *
     *     また、処理の最後に{@code null}判定を行っているため（真の場合は上記の1番目の操作を行う）、戻り値が{@code null}の可能性は恐らくありません。
     * </p>
     *
     * @return 属性名
     */
    public String extractName() {
        String name = null;

        for (int i = 0; i < context.getChildCount(); i++) {
            if (context.getChild(i) instanceof ClassFeatureParser.NameContext) {
                name = context.getChild(i).getText();
                break;
            }
            if (context.exception != null) throw context.exception;
        }
        if (name == null) throw new IllegalArgumentException();

        return name;
    }

    /**
     * <p> 可視性を抽出します。 </p>
     *
     * <p>
     *     次の場合は{@code null}を返します。
     *     <ul>
     *         <li> 属性文を設定していない場合 </li>
     *         <li> 設定した属性文に属性名を含んでいない場合 </li>
     *     </ul>
     * </p>
     *
     * @return 可視性 {@code null}の可能性あり
     */
    public String extractVisibility() {
        String visibility = null;

        for (int i = 0; i < context.getChildCount(); i++) {
            if (context.getChild(i) instanceof ClassFeatureParser.VisibilityContext) {
                visibility = context.getChild(i).getText();
                break;
            }
        }

        return visibility;
    }

    /**
     * <p> 派生を抽出します。 </p>
     *
     * <p>
     *     次の場合は{@code null}を返します。
     *     <ul>
     *         <li> 属性文を設定していない場合 </li>
     *         <li> 設定した属性文に属性名を含んでいない場合 </li>
     *     </ul>
     * </p>
     *
     * @return 派生 {@code null}の可能性あり
     */
    public String extractDivided() {
        String divided = null;

        for (int i = 0; i < context.getChildCount(); i++) {
            if (context.getChild(i) instanceof ClassFeatureParser.DividedContext) {
                divided = context.getChild(i).getText();
                break;
            }
        }

        return divided;
    }

    /**
     * <p> 型を抽出します。 </p>
     *
     * <p>
     *     次の場合は{@code null}を返します。
     *     <ul>
     *         <li> 属性文を設定していない場合 </li>
     *         <li> 設定した属性文に属性名を含んでいない場合 </li>
     *     </ul>
     *
     *     また、設定した属性名が予約語と同じ文字列の場合は{@link org.antlr.v4.runtime.InputMismatchException}を投げます（{@link #extractName()}参照）。
     * </p>
     *
     * @return 型 {@code null}の可能性あり
     */
    public String extractPropType() {
        String propType = null;

        for (int i = 0; i < context.getChildCount(); i++) {
            if (context.getChild(i) instanceof ClassFeatureParser.PropTypeContext) {
                propType = context.getChild(i).getChild(0).getChild(1).getText();
                break;
            }
        }
        checkIfNameIsSamePrimitiveType();

        return propType;
    }

    /**
     * <p> 多重度における下限を抽出します。 </p>
     *
     * <p>
     *     次の場合は{@code null}を返します。
     *     <ul>
     *         <li> 属性文を設定していない場合 </li>
     *         <li> 設定した属性文に属性名を含んでいない場合 </li>
     *         <li> 下限が存在しない場合 </li>
     *     </ul>
     *
     *     また、設定した属性名が予約語と同じ文字列の場合は{@link org.antlr.v4.runtime.InputMismatchException}を投げます（{@link #extractName()}参照）。
     * </p>
     *
     * @return 多重度における下限
     */
    public String extractMultiplicityRangeLower() {
        String multiplicityRangeLower = null;

        for (int i = 0; i < context.getChildCount(); i++) {
            if (context.getChild(i) instanceof ClassFeatureParser.MultiplicityRangeContext) {
                if (context.getChild(i).getChild(1) instanceof ClassFeatureParser.LowerContext) {
                    multiplicityRangeLower = formatMultiplicityRangeExpression((ClassFeatureParser.LowerContext) context.getChild(i).getChild(1));
                    break;
                }
            }
        }
        checkIfNameIsSamePrimitiveType();

        return multiplicityRangeLower;
    }

    /**
     * <p> 多重度における上限を抽出します。 </p>
     *
     * <p>
     *     次の場合は{@code null}を返します。
     *     <ul>
     *         <li> 属性文を設定していない場合 </li>
     *         <li> 設定した属性文に属性名を含んでいない場合 </li>
     *     </ul>
     *
     *     また、設定した属性名が予約語と同じ文字列の場合は{@link org.antlr.v4.runtime.InputMismatchException}を投げます（{@link #extractName()}参照）。
     * </p>
     *
     * @return 多重度における上限
     */
    public String extractMultiplicityRangeUpper() {
        String multiplicityRangeUpper = null;

        for (int i = 0; i < context.getChildCount(); i++) {
            if (context.getChild(i) instanceof ClassFeatureParser.MultiplicityRangeContext) {
                if (context.getChild(i).getChild(1) instanceof ClassFeatureParser.UpperContext) {
                    multiplicityRangeUpper = formatMultiplicityRangeExpression((ClassFeatureParser.UpperContext) context.getChild(i).getChild(1));
                } else {
                    multiplicityRangeUpper = formatMultiplicityRangeExpression((ClassFeatureParser.UpperContext) context.getChild(i).getChild(3));
                }
                break;
            }
        }
        checkIfNameIsSamePrimitiveType();

        return multiplicityRangeUpper;
    }

    /**
     * <p> 既定値を抽出します。 </p>
     *
     * <p>
     *     次の場合は{@code null}を返します。
     *     <ul>
     *         <li> 属性文を設定していない場合 </li>
     *         <li> 設定した属性文に属性名を含んでいない場合 </li>
     *     </ul>
     *
     *     また、属性名が予約語と同じ文字列の場合は{@link org.antlr.v4.runtime.InputMismatchException}を投げます（{@link #extractName()}参照）。
     * </p>
     *
     * @return 既定値
     */
    public String extractDefaultValue() {
        String defaultValue = null;

        for (int i = 0; i < context.getChildCount(); i++) {
            if (context.getChild(i) instanceof ClassFeatureParser.DefaultValueContext) {
                if (context.getChild(i).getChild(1).getChild(1) instanceof ClassFeatureParser.CreatorContext) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("new ");
                    for (int j = 0; j < context.getChild(i).getChild(1).getChild(1).getChildCount(); j++) {
                        sb.append(context.getChild(i).getChild(1).getChild(1).getChild(j).getText());
                    }
                    defaultValue = new String(sb);
                } else {
                    defaultValue = formatExpression((ClassFeatureParser.ExpressionContext) context.getChild(i).getChild(1));
                }
                break;
            }
        }
        checkIfNameIsSamePrimitiveType();

        return defaultValue;
    }

    /**
     * <p> プロパティを抽出します。 </p>
     *
     * <p>
     *     次の場合は{@code null}を返します。
     *     <ul>
     *         <li> 属性文を設定していない場合 </li>
     *         <li> 設定した属性文に属性名を含んでいない場合 </li>
     *     </ul>
     *
     *     また、属性名が予約語と同じ文字列の場合は{@link org.antlr.v4.runtime.InputMismatchException}を投げます（{@link #extractName()}参照）。
     * </p>
     *
     * @return プロパティ
     */
    public String extractPropModifier() {
        String propModifier = null;

        for (int i = 0; i < context.getChildCount(); i++) {
            if (context.getChild(i) instanceof ClassFeatureParser.PropModifiersContext) {
                List<String> modifiers = new ArrayList<>();
                for (int j = 0; j < context.getChild(i).getChild(0).getChildCount(); j++) {
                    if (context.getChild(i).getChild(0).getChild(j) instanceof ClassFeatureParser.PropModifierContext) {
                        if (context.getChild(i).getChild(0).getChild(j).getChildCount() == 2) {
                            modifiers.add(context.getChild(i).getChild(0).getChild(j).getChild(0).getText() + " " + context.getChild(i).getChild(0).getChild(j).getChild(1).getText());
                        } else {
                            modifiers.add(context.getChild(i).getChild(0).getChild(j).getText());
                        }
                    }
                }
                propModifier = String.join(", ", modifiers);
                break;
            }
        }
        checkIfNameIsSamePrimitiveType();

        return propModifier;
    }



    /**
     * <p> 式の文章を整形します。 </p>
     *
     * <p>
     *     次の場合、トークン間にスペースを挿入します。
     *     <ul>
     *         <li> {@code numberOrExpression ([+*%/-] | '<=' | '>=' | '>' | '<' | '==' | '!=' | '&&' | 'and' | 'AND' | '||' | 'or' | 'OR') numberOrExpression} </li>
     *         <li> {@code ('!' | 'not' | 'NOT') numberOrExpression} </li>
     *     </ul>
     * </p>
     *
     * @param ctx 式のコンテキスト
     * @return 式の文章
     */
    private String formatExpression(ClassFeatureParser.ExpressionContext ctx) {
        String text;

        if (ctx.getChildCount() == 2) {
            if (ctx.getChild(1) instanceof ClassFeatureParser.ExpressionContext && (ctx.getChild(0).getText().equals("!") || ctx.getChild(0).getText().equals("not") || ctx.getChild(0).getText().equals("NOT"))) {
                text = ctx.getChild(0).getText() + " " + formatExpression((ClassFeatureParser.ExpressionContext) ctx.getChild(1));
            } else if (ctx.getChild(1) instanceof ClassFeatureParser.ExpressionContext) {
                text = ctx.getChild(0).getText() + formatExpression((ClassFeatureParser.ExpressionContext) ctx.getChild(1));
            } else {
                text = ctx.getText();
            }

        } else if (ctx.getChildCount() == 3) {
            if (ctx.getChild(0) instanceof ClassFeatureParser.ExpressionContext && ctx.getChild(2) instanceof ClassFeatureParser.ExpressionContext) {
                text = formatExpression((ClassFeatureParser.ExpressionContext) ctx.getChild(0)) + " " + ctx.getChild(1).getText() + " " + formatExpression((ClassFeatureParser.ExpressionContext) ctx.getChild(2));
            } else if (ctx.getChild(1) instanceof ClassFeatureParser.ExpressionContext) {
                text = ctx.getChild(0).getText() + formatExpression((ClassFeatureParser.ExpressionContext) ctx.getChild(1)) + ctx.getChild(2).getText();
            } else {
                text = ctx.getText();
            }

        } else {
            text = ctx.getText();
        }

        return text;
    }

    /**
     * <p> 多重度における下限の文章を整形します。 </p>
     *
     * <p>
     *     <ul>
     *         <li> value-specificationの場合は"()"で囲んだ文字列を返します（{@link #formatMultiplicityRangeExpression(ClassFeatureParser.ValueSpecificationContext)}を参照）。 </li>
     *         <li> 数値の場合は数値の文字列を返します。 </li>
     *     </ul>
     * </p>
     *
     * @param ctx 多重度における下限のコンテキスト
     * @return 多重度における下限の文章
     */
    private String formatMultiplicityRangeExpression(ClassFeatureParser.LowerContext ctx) {
        String text;

        if (ctx.getChild(0) instanceof ClassFeatureParser.ValueSpecificationContext) {
            text = formatMultiplicityRangeExpression((ClassFeatureParser.ValueSpecificationContext) ctx.getChild(0));
        } else {
            text = ctx.getText();
        }

        return text;
    }

    /**
     * <p> 多重度における上限の文章を整形します。 </p>
     *
     * <p>
     *     <ul>
     *         <li> value-specificationの場合は"()"で囲んだ文字列を返します（{@link #formatMultiplicityRangeExpression(ClassFeatureParser.ValueSpecificationContext)}を参照）。 </li>
     *         <li> {@code "*"}の場合はそのものを返します。 </li>
     *         <li> 数値の場合は数値の文字列を返します。 </li>
     *     </ul>
     * </p>
     *
     * @param ctx 多重度における上限のコンテキスト
     * @return 多重度における上限の文章
     */
    private String formatMultiplicityRangeExpression(ClassFeatureParser.UpperContext ctx) {
        String text;

        if (ctx.getChild(0) instanceof ClassFeatureParser.ValueSpecificationContext) {
            text = formatMultiplicityRangeExpression((ClassFeatureParser.ValueSpecificationContext) ctx.getChild(0));
        } else {
            text = ctx.getText();
        }

        return text;
    }

    /**
     * <p> 多重度における下限または上限のvalue-specificationの文章を整形します。 </p>
     *
     * <p>
     *     {@link #formatMultiplicityRangeExpression(ClassFeatureParser.LowerContext)}または{@link #formatMultiplicityRangeExpression(ClassFeatureParser.UpperContext)}で利用します。
     *     単語を{@code " "}で区切った文を{@code ", "}で区切った文章に整形し、それを{@code "()"}で囲んだ文字列を返します。
     * </p>
     *
     * @param ctx 多重度における下限または上限のvalue-specificationコンテキスト
     * @return 単語を{@code " "}で区切った文を{@code ", "}で区切った文章に整形し{@code "()"}で囲んだ文字列
     */
    private String formatMultiplicityRangeExpression(ClassFeatureParser.ValueSpecificationContext ctx) {
        String text = "(";
        List<String> expressions = new ArrayList<>();
        List<String> expression = new ArrayList<>();

        // 括弧 "(" と括弧閉じ ")" を無視
        for (int i = 1; i < ctx.getChildCount() - 1; i++) {
            if (ctx.getChild(i) instanceof ClassFeatureParser.ExpressionContext) {
                expression.add(formatExpression((ClassFeatureParser.ExpressionContext) ctx.getChild(i)));
            } else {
                expressions.add(String.join(" ", expression));
                expression.clear();
            }
        }
        expressions.add(String.join(" ", expression));
        text += String.join(", ", expressions) + ")";

        return text;
    }
}
