package feature.value.expression.symbol;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p> 演算子抽象クラス </p>
 *
 * <p>
 *     演算子を定義する抽象クラスです。
 *     実装の際には{@link #toString()}を実装してください。
 *     また、演算子を複数定義する場合は{@link #adjustSymbolStrings()}と{@link #is(String)}メソッドをオーバーライドしてください。
 * </p>
 */
abstract public class Symbol {

    private final static Map<String, Symbol> string2symbol = new HashMap<>() {{
        put("+", new Add());
        put("-", new Sub());
        put("*", new Multi());
        put("/", new Divide());
        put("%", new Mod());
        put("<=", new LessEqual());
        put(">=", new GreaterEqual());
        put("<", new Less());
        put(">", new Greater());
        put("==", new Equal());
        put("!=", new NotEqual());
        put("&&", new And());
        put("and", new And());
        put("AND", new And());
        put("||", new Or());
        put("or", new Or());
        put("OR", new Or());
        put("!", new Not());
        put("not", new Not());
        put("NOT", new Not());
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
