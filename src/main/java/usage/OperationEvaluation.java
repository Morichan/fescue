package usage;

public class OperationEvaluation extends ClassEvaluation {
    private String operation;

    /**
     * 操作文を設定します。
     *
     * @param text 設定する操作文
     */
    @Override
    public void setText(String text) {
        operation = text;
    }

    /**
     * <p> 操作文を取得します。 </p>
     *
     * <p>
     *     {@link #setText(String)}を実行する前にこのメソッドを実行した場合は{@code null}を返します。
     * </p>
     *
     * @return 操作文 {@code null}の可能性あり
     */
    @Override
    public String getText() {
        return operation;
    }

    @Override
    public void walk() {
    }
}
