package evaluation;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import parser.ClassFeatureParser;

/**
 * <p> 構文解析木の評価クラス </p>
 *
 * <p>
 *     {@link parser.ClassFeatureBaseListener}およびそのクラスを自動生成するANTLRに依存します。
 * </p>
 */
public class FeatureEvalListener extends parser.ClassFeatureBaseListener {
    /**
     * 属性文コンテキスト
     */
    private ClassFeatureParser.PropertyContext property = null;

    /**
     * 操作文コンテキスト
     */
    private ClassFeatureParser.OperationContext operation = null;

    /**
     * <p> 属性文に入った場合の走査結果を取得します。 </p>
     *
     * <p>
     *     実際には{@link ClassFeatureParser.PropertyContext}インスタンスを取得しているだけです。
     *     {@link org.antlr.v4.runtime.tree.ParseTreeWalker#walk(ParseTreeListener, ParseTree)}で使われます。
     * </p>
     *
     * @param ctx 属性文コンテキスト
     */
    @Override
    public void enterProperty(ClassFeatureParser.PropertyContext ctx) {
        property = ctx;
    }

    /**
     * <p> 操作文に入った場合の捜査結果を取得します。 </p>
     *
     * <p>
     *     実際には{@link ClassFeatureParser.OperationContext}インスタンスを取得しているだけです。
     *     {@link org.antlr.v4.runtime.tree.ParseTreeWalker#walk(ParseTreeListener, ParseTree)}で使われます。
     * </p>
     *
     * @param ctx 操作文コンテキスト
     */
    @Override
    public void enterOperation(ClassFeatureParser.OperationContext ctx) {
        operation = ctx;
    }

    /**
     * 属性文コンテキストを取得します。
     * {@link #enterProperty(ClassFeatureParser.PropertyContext)}が実行されなかった場合は{@code null}を返します。
     *
     * @return 属性文コンテキスト <br> {@code null}の可能性あり
     */
    public ClassFeatureParser.PropertyContext getProperty() {
        return property;
    }

    /**
     * 操作文コンテキストを取得します。
     * {@link #enterOperation(ClassFeatureParser.OperationContext)}が実行されなかった場合は{@code null}を返します。
     *
     * @return 操作文コンテキスト <br> {@code null}の可能性あり
     */
    public ClassFeatureParser.OperationContext getOperation() {
        return operation;
    }
}
