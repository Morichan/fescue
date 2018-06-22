package jp.ac.miyazaki_u.cs.earth.fescue.feature.visibility;

import java.util.HashMap;
import java.util.Map;

/**
 * <p> 可視性クラス </p>
 *
 * <p>
 *     使い方を次に示します。
 * </p>
 *
 * <pre>
 *     {@code
 *     Visibility visibility = Visibility.choose("-");
 *
 *     // オブジェクトでの比較
 *     if (visibility == Visibility.Private) System.out.println("Visibility is Private");
 *
 *     // 文字列での比較
 *     if (visibility.is("-")) System.out.println("Visibility is " + visibility);
 *     }
 * </pre>
 */
public enum Visibility {

    /**
     * <p> パブリック列挙子 </p>
     *
     * <p>
     *     {@link #is(String)}をオーバーライドしています。
     *     {@code "+"}を入力した場合のみ真を返します。
     * </p>
     */
    Public {
        @Override
        public boolean is(String visibility) {
            boolean isPublic = false;
            if (visibility.equals("+")) isPublic = true;
            return isPublic;
        }

        @Override
        public String toString() {
            return "+";
        }
    },

    /**
     * <p> プライベート列挙子 </p>
     *
     * <p>
     *     {@link #is(String)}をオーバーライドしています。
     *     {@code "-"}を入力した場合のみ真を返します。
     * </p>
     */
    Private {
        @Override
        public boolean is(String visibility) {
            boolean isPrivate = false;
            if (visibility.equals("-")) isPrivate = true;
            return isPrivate;
        }

        @Override
        public String toString() {
            return "-";
        }
    },

    /**
     * <p> パッケージ列挙子 </p>
     *
     * <p>
     *     {@link #is(String)}をオーバーライドしています。
     *     {@code "~"}を入力した場合のみ真を返します。
     * </p>
     */
    Package {
        @Override
        public boolean is(String visibility) {
            boolean isPackage = false;
            if (visibility.equals("~")) isPackage = true;
            return isPackage;
        }

        @Override
        public String toString() {
            return "~";
        }
    },

    /**
     * <p> プロテクテッド列挙子 </p>
     *
     * <p>
     *     {@link #is(String)}をオーバーライドしています。
     *     {@code "#"}を入力した場合のみ真を返します。
     * </p>
     */
    Protected {
        @Override
        public boolean is(String visibility) {
            boolean isProtected = false;
            if (visibility.equals("#")) isProtected = true;
            return isProtected;
        }

        @Override
        public String toString() {
            return "#";
        }
    },

    /**
     * <p> 未定義列挙子 </p>
     *
     * <p>
     *     {@link #is(String)}をオーバーライドしています。
     *     常に偽を返します。
     * </p>
     */
    Undefined {
        @Override
        public boolean is(String visibility) {
            return false;
        }

        @Override
        public String toString() {
            throw new IllegalStateException();
        }
    },
    ;



    final static private Map<String, Visibility> string2visibility = new HashMap<>() {{
        put("+", Public);
        put("-", Private);
        put("~", Package);
        put("#", Protected);
    }};



    /**
     * <p> インスタンス状態が最初に設定した状態であれば真を返す真偽値判定を行う抽象メソッドです。 </p>
     *
     * <p>
     *     インスタンス状態であるかどうかを文字列で判定できます。
     *     入力判定を行う文字列は{@code "+", "-", "~", "#"}の4種類です。
     * </p>
     *
     * @param visibility インスタンス状態であるかどうかの文字列
     * @return インスタンス状態が判定値と等しい場合は真を返す真偽値
     */
    abstract public boolean is(String visibility);

    /**
     * <p> インスタンス状態を文字列から選択します。 </p>
     *
     * <p>
     *     インスタンス状態を文字列{@code "+", "-", "~", "#"}から選択し、そのインスタンス状態を返します。
     *     上記の文字列以外または{@code null}を入力すると{@link IllegalStateException}を投げます。
     * </p>
     *
     * @param visibilityText インスタンス状態の文字列
     * @return 受取った文字列と等しいインスタンス状態
     */
    static public Visibility choose(String visibilityText) {
        if (visibilityText == null || !string2visibility.containsKey(visibilityText)) throw new IllegalStateException();

        return string2visibility.get(visibilityText);
    }

    /**
     * <p> 可視性の文字列を取得します。 </p>
     *
     * <p>
     *     可視性が設定されていない場合は{@link IllegalStateException}を投げます。
     * </p>
     *
     * @return 可視性の文字列<br>{@code null}および{@code ""}（空文字）は実装に依存します。
     */
    @Override
    abstract public String toString();
}
