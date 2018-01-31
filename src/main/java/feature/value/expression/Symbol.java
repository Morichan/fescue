package feature.value.expression;

import com.ibm.icu.text.PluralFormat;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p> 演算子クラス </p>
 *
 * <p>
 *     使い方を次に示します。
 * </p>
 *
 * <pre>
 *     {@code
 *     Symbol symbol = Symbol.choose("+");
 *
 *     // オブジェクトでの比較
 *     if (symbol == Symbol.Add) System.out.println("Symbol is Add");
 *
 *     // 文字列での比較
 *     if (symbol.is("+")) System.out.println("Symbol is " + symbol);
 *     }
 * </pre>
 */
public enum Symbol {
    Add {
        @Override
        public String toString() {
            return "+";
        }
    },
    Sub {
        @Override
        public String toString() {
            return "-";
        }
    },
    Multi {
        @Override
        public String toString() {
            return "*";
        }
    },
    Divide {
        @Override
        public String toString() {
            return "/";
        }
    },
    Mod {
        @Override
        public String toString() {
            return "%";
        }
    },
    LessEqual {
        @Override
        public String toString() {
            return "<=";
        }
    },
    GreaterEqual {
        @Override
        public String toString() {
            return ">=";
        }
    },
    Less {
        @Override
        public String toString() {
            return "<";
        }
    },
    Greater {
        @Override
        public String toString() {
            return ">";
        }
    },
    Equal {
        @Override
        public String toString() {
            return "==";
        }
    },
    NotEqual {
        @Override
        public String toString() {
            return "!=";
        }
    },

    And {
        private List<String> symbolStrings = Arrays.asList("&&", "and", "AND");
        private String symbolText = symbolStrings.get(0);

        /**
         * <p> インスタンス状態が最初に設定した状態であれば真を返す真偽値判定を行います。 </p>
         *
         * <p>
         *     インスタンス状態であるかどうかを文字列で判定できます。
         *     {@link Symbol#is(String)}をオーバーライドしていますが、ここでは実引数である{@code text}の文字列を{@link #symbolText}に格納しています。
         * </p>
         *
         * @param text インスタンス状態であるかどうかの文字列
         * @return インスタンス状態が判定値と等しい場合は真を返す真偽値
         */
        @Override
        public boolean is(String text) {
            boolean isThisSymbol = false;
            if (adjustSymbolStrings().contains(text)) {
                isThisSymbol = true;
                symbolText = text;
            }
            return isThisSymbol;
        }

        /**
         * <p> 演算子の文字列のリストを返します。 </p>
         *
         * <p>
         *     インスタンス状態における演算子が一意に求まらないため、複数の要素を持つリストを返します。
         * </p>
         *
         * @return インスタンス状態における演算子
         */
        @Override
        public List<String> adjustSymbolStrings() {
            return symbolStrings;
        }

        /**
         * <p> 演算子の文字列を取得します。 </p>
         *
         * <p>
         *     {@link #is(String)}を実行後にこのメソッドを呼出した場合は、{@link #is(String)}で受取った文字列を返します。
         *     もし{@link #is(String)}を実行せずにこのメソッドを呼出した場合は、{@link #symbolStrings}の0番目の要素を返します。
         * </p>
         *
         * @return 演算子の文字列 {@code null}および{@code ""}（空文字）無し
         */
        @Override
        public String toString() {
            return symbolText;
        }
    },

    Or {
        private List<String> symbolStrings = Arrays.asList("||", "or", "OR");
        private String symbolText = symbolStrings.get(0);

        /**
         * <p> インスタンス状態が最初に設定した状態であれば真を返す真偽値判定を行います。 </p>
         *
         * <p>
         *     インスタンス状態であるかどうかを文字列で判定できます。
         *     {@link Symbol#is(String)}をオーバーライドしていますが、ここでは実引数である{@code text}の文字列を{@link #symbolText}に格納しています。
         * </p>
         *
         * @param text インスタンス状態であるかどうかの文字列
         * @return インスタンス状態が判定値と等しい場合は真を返す真偽値
         */
        @Override
        public boolean is(String text) {
            boolean isThisSymbol = false;
            if (adjustSymbolStrings().contains(text)) {
                isThisSymbol = true;
                symbolText = text;
            }
            return isThisSymbol;
        }

        /**
         * <p> 演算子の文字列のリストを返します。 </p>
         *
         * <p>
         *     インスタンス状態における演算子が一意に求まらないため、複数の要素を持つリストを返します。
         * </p>
         *
         * @return インスタンス状態における演算子
         */
        @Override
        public List<String> adjustSymbolStrings() {
            return symbolStrings;
        }

        /**
         * <p> 演算子の文字列を取得します。 </p>
         *
         * <p>
         *     {@link #is(String)}を実行後にこのメソッドを呼出した場合は、{@link #is(String)}で受取った文字列を返します。
         *     もし{@link #is(String)}を実行せずにこのメソッドを呼出した場合は、{@link #symbolStrings}の0番目の要素を返します。
         * </p>
         *
         * @return 演算子の文字列 {@code null}および{@code ""}（空文字）無し
         */
        @Override
        public String toString() {
            return symbolText;
        }
    },

    Not {
        private List<String> symbolStrings = Arrays.asList("!", "not", "NOT");
        private String symbolText = symbolStrings.get(0);

        /**
         * <p> インスタンス状態が最初に設定した状態であれば真を返す真偽値判定を行います。 </p>
         *
         * <p>
         *     インスタンス状態であるかどうかを文字列で判定できます。
         *     {@link Symbol#is(String)}をオーバーライドしていますが、ここでは実引数である{@code text}の文字列を{@link #symbolText}に格納しています。
         * </p>
         *
         * @param text インスタンス状態であるかどうかの文字列
         * @return インスタンス状態が判定値と等しい場合は真を返す真偽値
         */
        @Override
        public boolean is(String text) {
            boolean isThisSymbol = false;
            if (adjustSymbolStrings().contains(text)) {
                isThisSymbol = true;
                symbolText = text;
            }
            return isThisSymbol;
        }

        /**
         * <p> 演算子の文字列のリストを返します。 </p>
         *
         * <p>
         *     インスタンス状態における演算子が一意に求まらないため、複数の要素を持つリストを返します。
         * </p>
         *
         * @return インスタンス状態における演算子
         */
        @Override
        public List<String> adjustSymbolStrings() {
            return symbolStrings;
        }

        /**
         * <p> 演算子の文字列を取得します。 </p>
         *
         * <p>
         *     {@link #is(String)}を実行後にこのメソッドを呼出した場合は、{@link #is(String)}で受取った文字列を返します。
         *     もし{@link #is(String)}を実行せずにこのメソッドを呼出した場合は、{@link #symbolStrings}の0番目の要素を返します。
         * </p>
         *
         * @return 演算子の文字列 {@code null}および{@code ""}（空文字）無し
         */
        @Override
        public String toString() {
            return symbolText;
        }
    },
    ;



    final static Map<String, Symbol> string2symbol = new HashMap<>() {{
        put("+", Add);
        put("-", Sub);
        put("*", Multi);
        put("/", Divide);
        put("%", Mod);
        put("<=", LessEqual);
        put(">=", GreaterEqual);
        put("<", Less);
        put(">", Greater);
        put("==", Equal);
        put("!=", NotEqual);
        put("&&", And);
        put("and", And);
        put("AND", And);
        put("||", Or);
        put("or", Or);
        put("OR", Or);
        put("!", Not);
        put("not", Not);
        put("NOT", Not);
    }};

    /**
     * <p> インスタンス状態が最初に設定した状態であれば真を返す真偽値判定を行います。 </p>
     *
     * <p>
     *     インスタンス状態であるかどうかを文字列で判定できます。
     * </p>
     *
     * @param text インスタンス状態であるかどうかの文字列
     * @return インスタンス状態が判定値と等しい場合は真を返す真偽値
     */
    public boolean is(String text) {
        boolean isThisSymbol = false;
        if (adjustSymbolStrings().contains(text)) isThisSymbol = true;
        return isThisSymbol;
    }

    /**
     * <p> 演算子の文字列のリストを返します。 </p>
     *
     * <p>
     *     インスタンス状態における演算子が一意に求まる場合は、1要素のみのリストを返します。
     *     もし複数の演算子を持つインスタンス状態の場合は、このメソッドをオーバーライドしてください。
     * </p>
     *
     * @return インスタンス状態における演算子
     */
    public List<String> adjustSymbolStrings() {
        return Arrays.asList(toString());
    }

    /**
     * <p> 演算子の文字列を取得します。 </p>
     *
     * @return 演算子の文字列 {@code null}および{@code ""}は実装に依存します。
     */
    @Override
    abstract public String toString();



    /**
     * <p> インスタンス状態を文字列から選択します。 </p>
     *
     * <p>
     *     インスタンス状態を{@link #string2symbol}から選択し、そのインスタンス状態を返します。
     *     {@link #string2symbol}に含まれていない文字列または{@code null}を入力すると{@link IllegalStateException}を投げます。
     * </p>
     *
     * @param symbolText インスタンス状態の文字列
     * @return 受取った文字列と等しいインスタンス状態
     */
    static public Symbol choose(String symbolText) {
        if (symbolText == null || !string2symbol.containsKey(symbolText)) throw new IllegalStateException();
        return string2symbol.get(symbolText);
    }
}
