package feature.value.expression;

public class ExpressionWithParen implements Expression {
    private Expression expression;

    public ExpressionWithParen(Expression exp) {
        expression = exp;
    }

    @Override
    public String toString() {
        return "(" + expression.toString() + ")";
    }
}
