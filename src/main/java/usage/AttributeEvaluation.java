package usage;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import parser.ClassesLexer;
import parser.ClassesParser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p> 属性における要素の抽出クラス </p>
 *
 * <p> 簡単な使い方を次に示します。</p>
 *
 * <pre>
 *     {@code
 *     AttributeEvaluation attribute = new AttributeEvaluation();
 *
 *     attribute.setAttribute("- attribute : int");
 *     attribute.walk();
 *
 *     String name = attribute.extractName();
 *     String visibility = attribute.extractVisibility();
 *     // ...
 *     }
 * </pre>
 *
 * <p>
 *     最初に属性文をセットし、走査してから各項目を抽出してください。
 *     順番を変えると{@link IllegalArgumentException}や{@link org.antlr.v4.runtime.InputMismatchException}を投げます。
 * </p>
 */
public class AttributeEvaluation {
    private ClassesEvalListener listener;
    private ClassesParser.PropertyContext context;

    private ClassesLexer lexer;
    private CommonTokenStream tokens;
    private ClassesParser parser;
    private ParseTree tree;
    private ParseTreeWalker walker;

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
     * @param text 設定する属性文 {@code null}可だが{@link #walk()}で{@link IllegalArgumentException}を投げる。
     */
    public void setAttribute(String text) {
        attribute = insertSpaceBothEndsOfRangeOperator(text);
    }

    /**
     * 属性文を取得します。
     *
     * @return 属性文 {@code null}の可能性あり
     */
    public String getAttribute() {
        return attribute;
    }

    /**
     * <p> 属性名を抽出します。 </p>
     *
     * <p>
     *     次の場合は例外を投げます。
     *     <ul>
     *         <li>属性文を設定していない場合（{@link #setAttribute(String)}参照） : {@link ClassesParser.PropertyContext#exception}</li>
     *         <li>属性文がプリミティブ型と同じ文字列の場合 : {@link IllegalArgumentException}</li>
     *     </ul>
     *     また、戻り値が{@code null}の可能性は恐らくありません。
     * </p>
     *
     * @return 属性名
     */
    public String extractName() {
        String name = null;

        for (int i = 0; i < context.getChildCount(); i++) {
            if (context.getChild(i) instanceof ClassesParser.NameContext) {
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
     *         <li> 属性名を含んでいない場合 </li>
     *     </ul>
     * </p>
     *
     * @return 可視性 {@code null}の可能性あり
     */
    public String extractVisibility() {
        String visibility = null;

        for (int i = 0; i < context.getChildCount(); i++) {
            if (context.getChild(i) instanceof ClassesParser.VisibilityContext) {
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
     *         <li> 属性名を含んでいない場合 </li>
     *     </ul>
     * </p>
     *
     * @return 派生 {@code null}の可能性あり
     */
    public String extractDivided() {
        String divided = null;

        for (int i = 0; i < context.getChildCount(); i++) {
            if (context.getChild(i) instanceof ClassesParser.DividedContext) {
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
     *         <li> 属性名を含んでいない場合 </li>
     *     </ul>
     *
     *     また、属性名がプリミティブ型と同じ文字列の場合は{@link org.antlr.v4.runtime.InputMismatchException}を投げます（{@link #extractName()}参照）。
     * </p>
     *
     * @return 型 {@code null}の可能性あり
     */
    public String extractPropType() {
        String propType = null;

        for (int i = 0; i < context.getChildCount(); i++) {
            if (context.getChild(i) instanceof ClassesParser.PropTypeContext) {
                propType = context.getChild(i).getChild(1).getText();
                break;
            }
        }
        extractName(); // if (attributeName == null) throw new InputMismatchException();

        return propType;
    }

    /**
     * <p> 多重度における下限を抽出します。 </p>
     *
     * <p>
     *     次の場合は{@code null}を返します。
     *     <ul>
     *         <li> 属性文を設定していない場合 </li>
     *         <li> 属性名を含んでいない場合 </li>
     *         <li> 下限が存在しない場合 </li>
     *     </ul>
     *
     *     また、属性名がプリミティブ型と同じ文字列の場合は{@link org.antlr.v4.runtime.InputMismatchException}を投げます（{@link #extractName()}参照）。
     * </p>
     *
     * @return 多重度における下限
     */
    public String extractMultiplicityRangeLower() {
        String multiplicityRangeLower = null;

        for (int i = 0; i < context.getChildCount(); i++) {
            if (context.getChild(i) instanceof ClassesParser.MultiplicityRangeContext) {
                if (context.getChild(i).getChild(1) instanceof ClassesParser.LowerContext) {
                    multiplicityRangeLower = formatMultiplicityRangeExpression((ClassesParser.LowerContext) context.getChild(i).getChild(1));
                    break;
                }
            }
        }
        extractName(); // if (attributeName == null) throw new InputMismatchException();

        return multiplicityRangeLower;
    }

    /**
     * <p> 多重度における上限を抽出します。 </p>
     *
     * <p>
     *     次の場合は{@code null}を返します。
     *     <ul>
     *         <li> 属性文を設定していない場合 </li>
     *         <li> 属性名を含んでいない場合 </li>
     *     </ul>
     *
     *     また、属性名がプリミティブ型と同じ文字列の場合は{@link org.antlr.v4.runtime.InputMismatchException}を投げます（{@link #extractName()}参照）。
     * </p>
     *
     * @return 多重度における上限
     */
    public String extractMultiplicityRangeUpper() {
        String multiplicityRangeUpper = null;

        for (int i = 0; i < context.getChildCount(); i++) {
            if (context.getChild(i) instanceof ClassesParser.MultiplicityRangeContext) {
                if (context.getChild(i).getChild(1) instanceof ClassesParser.UpperContext) {
                    multiplicityRangeUpper = formatMultiplicityRangeExpression((ClassesParser.UpperContext) context.getChild(i).getChild(1));
                } else if (context.getChild(i).getChild(3) instanceof ClassesParser.UpperContext) {
                    multiplicityRangeUpper = formatMultiplicityRangeExpression((ClassesParser.UpperContext) context.getChild(i).getChild(3));
                }
                break;
            }
        }
        extractName(); // if (attributeName == null) throw new InputMismatchException();

        return multiplicityRangeUpper;
    }

    /**
     * <p> 既定値を抽出します。 </p>
     *
     * <p>
     *     次の場合は{@code null}を返します。
     *     <ul>
     *         <li> 属性文を設定していない場合 </li>
     *         <li> 属性名を含んでいない場合 </li>
     *     </ul>
     *
     *     また、属性名がプリミティブ型と同じ文字列の場合は{@link org.antlr.v4.runtime.InputMismatchException}を投げます（{@link #extractName()}参照）。
     * </p>
     *
     * @return 既定値
     */
    public String extractDefaultValue() {
        String defaultValue = null;

        for (int i = 0; i < context.getChildCount(); i++) {
            if (context.getChild(i) instanceof ClassesParser.DefaultValueContext) {
                if (context.getChild(i).getChild(1).getChild(1) instanceof ClassesParser.CreatorContext) {
                    defaultValue = "new ";
                    for (int j = 0; j < context.getChild(i).getChild(1).getChild(1).getChildCount(); j++) {
                        defaultValue += context.getChild(i).getChild(1).getChild(1).getChild(j).getText();
                    }
                } else {
                    defaultValue = formatExpression((ClassesParser.ExpressionContext) context.getChild(i).getChild(1));
                }
                break;
            }
        }
        extractName(); // if (attributeName == null) throw new InputMismatchException();

        return defaultValue;
    }



    /**
     * <p> 字句解析と構文解析を行い、構文解析木を走査します。 </p>
     *
     * <p>
     *     属性文を設定していない場合は{@link IllegalArgumentException}を投げます（{@link #setAttribute(String)}参照）。
     * </p>
     */
    public void walk() {
        if (attribute == null) throw new IllegalArgumentException();

        lexer = new ClassesLexer(CharStreams.fromString(attribute));
        tokens = new CommonTokenStream(lexer);
        parser = new ClassesParser(tokens);
        tree = parser.property();
        walker = new ParseTreeWalker();
        listener = new ClassesEvalListener();
        walker.walk(listener, tree);
        context = listener.getProperty();
    }



    /**
     * <p> {@code "\\.\\."}という文字列（2つ連続したドット）が存在した場合、その両端に半角スペースを挿入します。 </p>
     *
     * <p>
     *     正確には、文法における多重度の下限と上限の指定（[0..1]のようなもの）内での、真ん中の2つのドット（以降は範囲演算子と呼びます）の両端に半角スペースを挿入します。
     *     これは、範囲演算子の箇所をパースする際に、半角スペースが入っていない場合はうまくパースできないためです。
     *     手法としては、単純な置換（{@code matcher("\\.\\.") -> replaceAll(" .. ")}）を用いています。
     *     既に半角スペースが入っていたとしても、新たに半角スペースを挿入しますが、パースに問題はありません。
     * </p>
     *
     * @param text 設定する属性文 {@code null}可だが{@link #walk()}で{@link IllegalArgumentException}を投げる。
     * @return もし {@code "\\.\\."}が存在する場合はその両端に半角スペースを挿入した文字列、存在しない場合は入力した文字列
     */
    private String insertSpaceBothEndsOfRangeOperator(String text) {
        Pattern p = Pattern.compile("\\.\\.");
        Matcher m = p.matcher(text);
        if (m.find()) text = m.replaceAll(" .. ");
        return text;
    }

    /**
     * <p> 式における文章を整形します。 </p>
     *
     * <p>
     *     次の場合、間にスペースを挿入します。
     *     <ul>
     *         <li></li>
     *     </ul>
     * </p>
     * @param ctx
     * @return
     */
    private String formatExpression(ClassesParser.ExpressionContext ctx) {
        String text;

        if (ctx.getChildCount() == 2) {
            if (ctx.getChild(1) instanceof ClassesParser.ExpressionContext && (ctx.getChild(0).getText().equals("!") || ctx.getChild(0).getText().equals("not"))) {
                text = ctx.getChild(0).getText() + " " + formatExpression((ClassesParser.ExpressionContext) ctx.getChild(1));
            } else if (ctx.getChild(1) instanceof ClassesParser.ExpressionContext) {
                text = ctx.getChild(0).getText() + formatExpression((ClassesParser.ExpressionContext) ctx.getChild(1));
            } else {
                text = ctx.getText();
            }

        } else if (ctx.getChildCount() == 3) {
            if (ctx.getChild(0) instanceof ClassesParser.ExpressionContext && ctx.getChild(2) instanceof ClassesParser.ExpressionContext) {
                text = formatExpression((ClassesParser.ExpressionContext) ctx.getChild(0)) + " " + ctx.getChild(1).getText() + " " + formatExpression((ClassesParser.ExpressionContext) ctx.getChild(2));
            } else if (ctx.getChild(1) instanceof ClassesParser.ExpressionContext) {
                text = ctx.getChild(0).getText() + formatExpression((ClassesParser.ExpressionContext) ctx.getChild(1)) + ctx.getChild(2).getText();
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
     *         <li> value-specificationの場合は"()"で囲んだ文字列を返します（{@link #formatMultiplicityRangeExpression(ClassesParser.ValueSpecificationContext)}を参照）。 </li>
     *         <li> 数値の場合は数値の文字列を返します。 </li>
     *     </ul>
     * </p>
     *
     * @param ctx 多重度における下限のコンテキスト
     * @return 多重度における下限の文章
     */
    private String formatMultiplicityRangeExpression(ClassesParser.LowerContext ctx) {
        String text;

        if (ctx.getChild(0) instanceof ClassesParser.ValueSpecificationContext) {
            text = formatMultiplicityRangeExpression((ClassesParser.ValueSpecificationContext) ctx.getChild(0));
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
     *         <li> value-specificationの場合は"()"で囲んだ文字列を返します（{@link #formatMultiplicityRangeExpression(ClassesParser.ValueSpecificationContext)}を参照）。 </li>
     *         <li> {@code "*"}の場合はそのものを返します。 </li>
     *         <li> 数値の場合は数値の文字列を返します。 </li>
     *     </ul>
     * </p>
     *
     * @param ctx 多重度における上限のコンテキスト
     * @return 多重度における上限の文章
     */
    private String formatMultiplicityRangeExpression(ClassesParser.UpperContext ctx) {
        String text;

        if (ctx.getChild(0) instanceof ClassesParser.ValueSpecificationContext) {
            text = formatMultiplicityRangeExpression((ClassesParser.ValueSpecificationContext) ctx.getChild(0));
        } else {
            text = ctx.getText();
        }

        return text;
    }

    /**
     * <p> 多重度における下限または上限のvalue-specificationの文章を整形します。 </p>
     *
     * <p>
     *     {@link #formatMultiplicityRangeExpression(ClassesParser.LowerContext)}または{@link #formatMultiplicityRangeExpression(ClassesParser.UpperContext)}で利用します。
     *     単語を{@code " "}で区切った文を{@code ", "}で区切った文章に整形し、それを{@code "()"}で囲んだ文字列を返します。
     * </p>
     *
     * @param ctx 多重度における下限または上限のvalue-specificationコンテキスト
     * @return 単語を{@code " "}で区切った文を{@code ", "}で区切った文章に整形し{@code "()"}で囲んだ文字列
     */
    private String formatMultiplicityRangeExpression(ClassesParser.ValueSpecificationContext ctx) {
        String text = "(";
        List<String> expressions = new ArrayList<>();
        List<String> expression = new ArrayList<>();

        // 括弧 "(" と括弧閉じ ")" を無視
        for (int i = 1; i < ctx.getChildCount() - 1; i++) {
            if (ctx.getChild(i) instanceof ClassesParser.ExpressionContext) {
                expression.add(formatExpression((ClassesParser.ExpressionContext) ctx.getChild(i)));
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
