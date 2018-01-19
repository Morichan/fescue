package feature.visibility;

/**
 * <p> 可視性クラス </p>
 *
 * <p>
 *     使い方を次に示します。
 * </p>
 *
 * <pre>
 *     {@code
 *     Visibility visibility = Visibility.Private;
 *
 *     // オブジェクトでの比較
 *     if (visibility == Visibility.Private) System.out.println("Visibility is Private");
 *
 *     // 文字列での比較
 *     if (visibility.is("-")) System.out.println("Visibility is Private");
 *     }
 * </pre>
 */
public enum Visibility {
    Public {
        @Override
        public boolean is(String visibility) {
            boolean isPublic = false;
            if (visibility.equals("+")) isPublic = true;
            return isPublic;
        }
    },
    Private {
        @Override
        public boolean is(String visibility) {
            boolean isPrivate = false;
            if (visibility.equals("-")) isPrivate = true;
            return isPrivate;
        }
    },
    Package {
        @Override
        public boolean is(String visibility) {
            boolean isPackage = false;
            if (visibility.equals("~")) isPackage = true;
            return isPackage;
        }
    },
    Protected {
        @Override
        public boolean is(String visibility) {
            boolean isProtected = false;
            if (visibility.equals("#")) isProtected = true;
            return isProtected;
        }
    },
    Undefined {
        @Override
        public boolean is(String visibility) {
            return false;
        }
    },
    ;

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
     * <p> インスタンス状態を文字列から選択する。 </p>
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
        Visibility visibility;

        if (visibilityText == null) throw new IllegalStateException();

        switch (visibilityText) {
            case "+":
                visibility = Public;
                break;

            case "-":
                visibility = Private;
                break;

            case "~":
                visibility = Package;
                break;

            case "#":
                visibility = Protected;
                break;

            default:
                throw new IllegalStateException();
        }

        return visibility;
    }
}
