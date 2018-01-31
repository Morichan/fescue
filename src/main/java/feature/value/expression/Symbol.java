package feature.value.expression;

import java.util.Arrays;
import java.util.List;

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

        @Override
        public boolean is(String text) {
            boolean isThisSymbol = false;
            if (adjustSymbolStrings().contains(text)) {
                isThisSymbol = true;
                symbolText = text;
            }
            return isThisSymbol;
        }

        @Override
        public List<String> adjustSymbolStrings() {
            return symbolStrings;
        }

        @Override
        public String toString() {
            return symbolText;
        }
    },
    Or {
        private List<String> symbolStrings = Arrays.asList("||", "or", "OR");
        private String symbolText = symbolStrings.get(0);

        @Override
        public boolean is(String text) {
            boolean isThisSymbol = false;
            if (adjustSymbolStrings().contains(text)) {
                isThisSymbol = true;
                symbolText = text;
            }
            return isThisSymbol;
        }

        @Override
        public List<String> adjustSymbolStrings() {
            return symbolStrings;
        }

        @Override
        public String toString() {
            return symbolText;
        }
    },
    Not {
        private List<String> symbolStrings = Arrays.asList("!", "not", "NOT");
        private String symbolText = symbolStrings.get(0);

        @Override
        public boolean is(String text) {
            boolean isThisSymbol = false;
            if (adjustSymbolStrings().contains(text)) {
                isThisSymbol = true;
                symbolText = text;
            }
            return isThisSymbol;
        }

        @Override
        public List<String> adjustSymbolStrings() {
            return symbolStrings;
        }

        @Override
        public String toString() {
            return symbolText;
        }
    },
    ;

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
     *     もし複数の演算子を持つインスタンス状態の場合は、@overrideしてください。
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
     * @return 演算子の文字列 {@code null}および{@code ""}（空文字）無し
     */
    @Override
    abstract public String toString();
}
