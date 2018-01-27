package feature.value.expression;

abstract public class Expression {

    protected Identifier identifier;
    protected Expression first;
    protected Expression second;

    public void set(Identifier id) {
        identifier = id;
    }

    public void set(Expression firstExpression, Expression secondExpression) {
        first = firstExpression;
        second = secondExpression;
    }

    @Override
    abstract public String toString();
}
