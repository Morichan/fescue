package feature.value.expression;

public class PlusExpression implements Expression {
    private Expression first;
    private Expression second;

    public PlusExpression(Expression first, Expression second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public String toString() {
        return first + " + " + second;
    }
}
