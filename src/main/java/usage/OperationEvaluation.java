package usage;

import org.antlr.v4.runtime.InputMismatchException;
import parser.ClassesParser;

public class OperationEvaluation extends ClassEvaluation {
    private ClassesParser.OperationContext context;
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
        initIfIsSameBetweenNameAndKeyword();
        if (operation == null) throw new IllegalArgumentException();

        ClassesParser parser = generateParser(operation);
        ClassesEvalListener listener = walk(parser.operation());
        context = listener.getOperation();

        try {
            extractName();
        } catch (InputMismatchException e) {
            setInputMismatchException(e);
        } catch (IllegalArgumentException e) {
            System.out.println("ERROR! Please Set Operation!");
        }
    }

    public String extractName() {
        String name = null;

        for (int i = 0; i < context.getChildCount(); i++) {
            if (context.getChild(i) instanceof ClassesParser.NameContext) {
                name = context.getChild(i).getText();
                break;
            }
            if (context.exception != null) throw context.exception;
        }
        if (name == null) throw new IllegalArgumentException();

        return name;
    }
}
