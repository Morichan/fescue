package evaluation;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.InputMismatchException;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import parser.ClassFeatureLexer;
import parser.ClassFeatureParser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p> クラスの属性文または操作文の評価クラス </p>
 *
 * <p>
 *     抽象クラスですが、このクラス内で定義している抽象メソッドはありません。
 *     このクラスを実装する場合は、Evaluationインタフェースで定義している抽象メソッドを実装してください。
 * </p>
 */
abstract public class FeatureEvaluation implements Evaluation {

    /**
     * 属性または操作の名前が文法ファイルの予約語と同じ場合は真を持つ真偽値
     */
    private boolean isSameBetweenNameAndKeyword = false;

    /**
     * <p> {@link ClassFeatureParser.PropertiesContext#exception}または{@link ClassFeatureParser.OperationContext#exception}が持つ{@link InputMismatchException}インスタンス </p>
     *
     * <p>
     *     もし持っていない場合は{@code null}を持ちます。
     * </p>
     */
    private InputMismatchException inputMismatchException;

    /**
     * 名前が予約語と同じ文字列かどうかを判定し、同じ場合は{@link ClassFeatureParser.PropertyContext#exception}を返します。
     */
    protected void checkIfNameIsSamePrimitiveType() {
        if (isSameBetweenNameAndKeyword) throw inputMismatchException;
    }

    /**
     * <p> {@link #checkIfNameIsSamePrimitiveType()}で必要な変数2つ（{@link #isSameBetweenNameAndKeyword}と{@link #inputMismatchException}）を初期化します。 </p>
     *
     * <p>
     *     このメソッドは{@link #walk()}の最初に使ってください。
     * </p>
     */
    protected void initIfIsSameBetweenNameAndKeyword() {
        inputMismatchException = null;
        isSameBetweenNameAndKeyword = false;
    }

    /**
     * <p> 例外インスタンスを設定します。 </p>
     *
     * <p>
     *     {@code try-catch}文で利用してください。
     * </p>
     *
     * @param e {@code try-catch}文でキャッチした{@link InputMismatchException}インスタンス
     */
    protected void setInputMismatchException(InputMismatchException e) {
        inputMismatchException = e;
        isSameBetweenNameAndKeyword = true;
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
    protected String insertSpaceBothEndsOfRangeOperator(String text) {
        Pattern p = Pattern.compile("\\.\\.");
        Matcher m = p.matcher(text);
        if (m.find()) text = m.replaceAll(" .. ");
        return text;
    }

    /**
     * 構文解析機を生成します。
     *
     * @param parsedTarget 構文解析対象の文字列
     * @return 構文解析結果
     */
    protected ClassFeatureParser generateParser(String parsedTarget) {
        ClassFeatureLexer lexer = new ClassFeatureLexer(CharStreams.fromString(parsedTarget));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        return new ClassFeatureParser(tokens);
    }

    /**
     * <p> 取得した走査対象の構文木を走査します。 </p>
     *
     * <p>
     *     走査対象の構文木には、{@link #generateParser(String)}で取得した構文解析結果の任意のトークンを頂点とする構文木を設定してください。
     * </p>
     *
     * @param tree 走査対象の構文木
     * @return 走査済みリスナ
     */
    protected FeatureEvalListener walk(ParseTree tree) {
        ParseTreeWalker walker = new ParseTreeWalker();
        FeatureEvalListener listener = new FeatureEvalListener();
        walker.walk(listener, tree);

        return listener;
    }
}
