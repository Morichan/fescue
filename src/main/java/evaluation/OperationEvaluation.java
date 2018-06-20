package evaluation;

import parser.ClassFeatureParser;

/**
 * <p> 操作における要素の評価クラス </p>
 *
 * <p> 簡単な使い方を次に示します。</p>
 *
 * <pre>
 *     {@code
 *     OperationEvaluation evaluation = new OperationEvaluation();
 *     evaluation.setText("+ operation() : int");
 *     evaluation.walk();
 *
 *     ClassFeatureParser.OperationContext context = evaluation.getContext();
 *     // ...
 *     }
 * </pre>
 *
 * <p>
 *     最初に操作文をセットし、走査してからコンテキストを取得してください。
 *     順番を変えると例外として{@link IllegalArgumentException}や{@link org.antlr.v4.runtime.InputMismatchException}を投げます。
 * </p>
 */
public class OperationEvaluation extends FeatureEvaluation {

    /**
     * 操作文コンテキスト
     */
    private ClassFeatureParser.OperationContext context;

    /**
     * 操作文
     */
    private String operation;

    /**
     * <p> 操作文を設定します。 </p>
     *
     * <p>
     *     {@code "\\.\\."}という文字列（2つ連続したドット）が存在した場合、その両端に半角スペースを挿入します。
     *     これは、多重度における下限と上限の指定の箇所をパースする際に、ANTLR4の仕様により半角スペースが入っていない場合はうまくパースできないためです。
     *     詳しくは{@link #insertSpaceBothEndsOfRangeOperator(String)}を参照してください。
     * </p>
     *
     * @param text 設定する操作文 <br>
     *     {@code null}不可（{@link #insertSpaceBothEndsOfRangeOperator(String)}で{@link IllegalArgumentException}を投げます。）
     */
    @Override
    public void setText(String text) {
        operation = insertSpaceBothEndsOfRangeOperator(text);
    }

    /**
     * <p> 操作文を取得します。 </p>
     *
     * <p>
     *     {@link #setText(String)}を実行する前にこのメソッドを実行した場合は{@link IllegalStateException}を投げます。
     * </p>
     *
     * @return 属性文 <br> {@code null}なし
     */
    @Override
    public String getText() {
        if (operation == null) throw new IllegalStateException();
        return operation;
    }

    /**
     * <p> 字句解析と構文解析を行い、構文解析木を走査します。 </p>
     *
     * <p>
     *     次の場合は例外を投げます。
     * </p>
     *
     * <ul>
     *     <li>操作文を設定していない場合（{@link #setText(String)}参照） : {@link IllegalArgumentException}</li>
     *     <li>設定した操作文が予約語と同じ文字列の場合 : {@link ClassFeatureParser.OperationContext#exception}</li>
     * </ul>
     */
    @Override
    public void walk() {
        initIfIsSameBetweenNameAndKeyword();
        if (operation == null) throw new IllegalArgumentException();

        ClassFeatureParser parser = generateParser(operation);
        FeatureEvalListener listener = walk(parser.operation());
        context = listener.getOperation();

        confirmExtractingName();
    }

    public ClassFeatureParser.OperationContext getContext() {
        if (context == null) throw new IllegalStateException();
        return context;
    }



    /**
     * <p> 操作名が抽出できるかどうか確認します。 </p>
     *
     * <p>
     *     次の場合は例外を投げます。
     * </p>
     *
     * <ul>
     *     <li>操作文を設定していない場合（{@link #setText(String)}参照） : {@link IllegalArgumentException}</li>
     *     <li>設定した操作文が操作文としての形式と正しくない場合 : {@link ClassFeatureParser.OperationContext#exception}</li>
     * </ul>
     */
    private void confirmExtractingName() {
        String name = null;

        for (int i = 0; i < context.getChildCount(); i++) {
            if (context.getChild(i) instanceof ClassFeatureParser.NameContext) {
                name = context.getChild(i).getText();
                break;
            }
            if (context.exception != null) throw context.exception;
        }
        if (name == null || name.equals("<missing IDENTIFIER>")) throw new IllegalArgumentException();
    }
}
