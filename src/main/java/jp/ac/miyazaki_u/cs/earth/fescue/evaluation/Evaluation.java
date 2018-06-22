package jp.ac.miyazaki_u.cs.earth.fescue.evaluation;

/**
 * 評価クラスインタフェース
 */
public interface Evaluation {
    /**
     * <p> 構文木生成対象の文字列を設定します。 </p>
     *
     * <p>
     *     設定前に、文字列への処理などを記述してください。
     * </p>
     *
     * @param text 構文木生成対象の文字列
     */
    void setText(String text);

    /**
     * <p> 構文木生成対象の文字列を取得します。 </p>
     *
     * <p>
     *     基本的に、{@link #setText(String)}で設定した文字列をそのまま返します。
     *     {@link #setText(String)}で文字列の前処理などを行った場合、処理を行う前の文字列を返すか、処理を行った文字列を返すかは、実装に依存します。
     *     また、{@link #setText(String)}を行う前にこのメソッドを実行した場合の戻り値についても、実装に依存します。
     * </p>
     *
     * @return {@link #setText(String)}で設定した文字列
     */
    String getText();

    /**
     * <p> 構文木の生成および生成した構文木を走査します。 </p>
     *
     * <p>
     *     処理内容は、次のようなものとします。
     * </p>
     *
     * <pre>
     *     {@code
     *     // 自動生成した構文解析機のレキサに、字句解析対象の文字列を設定します。
     *     GeneratedLexer lexer = new GeneratedLexer(CharStreams.fromString(attribute));
     *     // 先ほど作ったレキサを元に、トークンストリームを作ります。
     *     CommonTokenStream tokens = new CommonTokenStream(lexer);
     *     // 自動生成した構文解析機のパーサに、トークンストリームを設定します。
     *     GeneratedParser jp.ac.miyazaki_u.cs.earth.jp.ac.miyazaki_u.cs.earth.fescue.parser = new GeneratedParser(tokens);
     *     // パーサの任意のコンテキストを頂点とする構文木を取得します。
     *     ParseTree tree = jp.ac.miyazaki_u.cs.earth.jp.ac.miyazaki_u.cs.earth.fescue.parser.anyContext();
     *     // 構文木走査器を作ります。
     *     ParseTreeWalker walker = new ParseTreeWalker();
     *     // GeneratedBaseListenerクラスを継承したリスナを作ります。
     *     EvalListener listener = new EvalListener();
     *     // 構文木を走査し、リスナに任意のトークン到達時または離脱時の処理を実行します。
     *     walker.walk(listener, tree);
     *     }
     * </pre>
     */
    void walk();
}
