package usage;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import parser.ClassesParser;

/**
 * 構文解析木の走査クラス
 *
 * {@link parser.ClassesBaseListener}およびそのクラスを自動生成するANTLRに依存します。
 */
public class ClassesEvalListener extends parser.ClassesBaseListener {
    private ClassesParser.PropertyContext property = null;
    private ClassesParser.OperationContext operation = null;

    /**
     * <p> 属性文に入った場合の走査結果を取得します。 </p>
     *
     * <p>
     *     実際には{@link ClassesParser.PropertyContext}インスタンスを取得しているだけです。
     *     {@link org.antlr.v4.runtime.tree.ParseTreeWalker#walk(ParseTreeListener, ParseTree)}で使われます。
     * </p>
     *
     * @param ctx 属性文コンテキスト
     */
    @Override
    public void enterProperty(ClassesParser.PropertyContext ctx) {
        property = ctx;
    }

    /**
     * <p> 操作文に入った場合の捜査結果を取得します。 </p>
     *
     * <p>
     *     実際には{@link ClassesParser.OperationContext}インスタンスを取得しているだけです。
     *     {@link org.antlr.v4.runtime.tree.ParseTreeWalker#walk(ParseTreeListener, ParseTree)}で使われます。
     * </p>
     *
     * @param ctx 操作文コンテキスト
     */
    @Override
    public void enterOperation(ClassesParser.OperationContext ctx) {
        operation = ctx;
    }

    /**
     * 属性文コンテキストを取得します。
     * {@link #enterProperty(ClassesParser.PropertyContext)}が実行されなかった場合は{@code null}を返します。
     *
     * @return 属性文コンテキスト {@code null}の可能性あり
     */
    public ClassesParser.PropertyContext getProperty() {
        return property;
    }

    /**
     * 操作文コンテキストを取得します。
     * {@link #enterOperation(ClassesParser.OperationContext)}が実行されなかった場合は{@code null}を返します。
     *
     * @return 操作文コンテキスト {@code null}の可能性あり
     */
    public ClassesParser.OperationContext getOperation() {
        return operation;
    }
}
