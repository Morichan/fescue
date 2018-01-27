package feature.value.expression;

public class PlusExpression extends Expression {

    @Override
    public String toString() {
        return first + " + " + second;
    }
}
