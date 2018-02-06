package feature.value.expression;

/**
 * <p> 式抽象クラス </p>
 *
 * <p>
 *     式を定義する抽象クラスです。
 *     実装の際には{@link #toString()}を実装してください。
 * </p>
 */
public interface Expression {

    /**
     * <p> 式の文字列を取得します。 </p>
     *
     * @return 式の文字列 {@code null}および{@code ""}は実装に依存します。
     */
    @Override
    String toString();
}
