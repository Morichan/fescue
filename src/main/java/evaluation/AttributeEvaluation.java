package evaluation;

import parser.ClassFeatureParser;

/**
 * <p> 属性における要素の評価クラス </p>
 *
 * <p> 簡単な使い方を次に示します。</p>
 *
 * <pre>
 *     {@code
 *     AttributeEvaluation evaluation = new AttributeEvaluation();
 *     evaluation.setText("- attribute : int");
 *     evaluation.walk();
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
     *     これは、多重度における下限と上限の指定の箇所をパースする際に、ANTLR4の仕様により半角スペースが入っていない場合はうまくパースできないためです。
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
     *     {@link #setText(String)}を実行する前にこのメソッドを実行した場合は{@link IllegalStateException}を投げます。
     * </p>
     *
     * @return 属性文 {@code null}なし
     */
    @Override
    public String getText() {
        if (attribute == null) throw new IllegalStateException();
        return attribute;
    }



    /**
     * <p> 字句解析と構文解析を行い、構文解析木を走査します。 </p>
     *
     * <p>
     *     属性文を設定していない場合は{@link IllegalArgumentException}を投げます。
     * </p>
     *
     * <p>
     *     次の場合は例外を投げます。
     * </p>
     *
     * <ul>
     *     <li>属性文を設定していない場合（{@link #setText(String)}参照） : {@link IllegalArgumentException}</li>
     *     <li>設定した属性文が予約語と同じ文字列の場合 : {@link ClassFeatureParser.PropertyContext#exception}</li>
     * </ul>
     */
    @Override
    public void walk() {
        initIfIsSameBetweenNameAndKeyword();
        if (attribute == null) throw new IllegalArgumentException();

        ClassFeatureParser parser = generateParser(attribute);
        FeatureEvalListener listener = walk(parser.property());
        context = listener.getProperty();

        confirmExtractingName();
    }

    /**
     * <p> 字句解析および構文解析結果のコンテキストを取得します。 </p>
     *
     * <p>
     *     {@link #walk()}を実行する前にこのメソッドを実行すると{@link IllegalStateException}を投げます。
     * </p>
     *
     * @return 字句解析および構文解析結果のコンテキスト
     */
    public ClassFeatureParser.PropertyContext getContext() {
        if (context == null) throw new IllegalStateException();
        return context;
    }



    /**
     * <p> 属性名が抽出できるかどうか確認します。 </p>
     *
     * <p>
     *     次の場合は例外を投げます。
     * </p>
     *
     * <ul>
     *     <li>属性文を設定していない場合（{@link #setText(String)}参照） : {@link IllegalArgumentException}</li>
     *     <li>設定した属性文が予約語と同じ文字列の場合 : {@link ClassFeatureParser.PropertyContext#exception}</li>
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
        if (name == null) throw new IllegalArgumentException();
    }
}
