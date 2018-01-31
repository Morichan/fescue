package feature.value.expression;

public class Binomial implements Expression {
    private Expression first;
    private Expression second;
    private String symbol = "+";

    public Binomial(Expression first, Expression second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public String toString() {
        return first + " " + symbol + " " + second;
    }
}
