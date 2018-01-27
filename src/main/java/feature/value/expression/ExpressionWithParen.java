package feature.value.expression;

public class ExpressionWithParen extends Expression {

    @Override
    public String toString() {
        return "(" + identifier.toString() + ")";
    }
}
