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
}
