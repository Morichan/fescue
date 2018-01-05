package usage;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import parser.ClassesLexer;
import parser.ClassesParser;

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
                    defaultValue = context.getChild(i).getChild(1).getText();
                }
                break;
            }
        }
        extractName(); // if (attributeName == null) throw new InputMismatchException();

        return defaultValue;
    }

    /**
     * 多重度における下限を抽出します。
     *
     * @return 多重度における下限
     */
    public String extractMultiplicityRangeLower() {
        String multiplicityRangeLower = null;

        for (int i = 0; i < context.getChildCount(); i++) {
            if (context.getChild(i) instanceof ClassesParser.MultiplicityRangeContext) {
                if (context.getChild(i).getChild(1) instanceof ClassesParser.LowerContext) {
                    multiplicityRangeLower = context.getChild(i).getChild(1).getText();
                }
            }
        }

        return multiplicityRangeLower;
    }

    /**
     * 多重度における上限を抽出します。
     *
     * @return 多重度における上限
     */
    public String extractMultiplicityRangeUpper() {
        String multiplicityRangeUpper = null;

        for (int i = 0; i < context.getChildCount(); i++) {
            if (context.getChild(i) instanceof ClassesParser.MultiplicityRangeContext) {
                if (context.getChild(i).getChild(1) instanceof ClassesParser.UpperContext) {
                    multiplicityRangeUpper = context.getChild(i).getChild(1).getText();
                } else if (context.getChild(i).getChild(3) instanceof ClassesParser.UpperContext) {
                    multiplicityRangeUpper = context.getChild(i).getChild(3).getText();
                }
            }
        }

        return multiplicityRangeUpper;
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

    private String MultiplicityRangeExpression(ClassesParser.LowerContext ctx) {
        return "";
    }
}
