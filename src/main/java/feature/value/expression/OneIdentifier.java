package feature.value.expression;

public class OneIdentifier implements Expression {
    private Identifier identifier;

    public OneIdentifier(int number) {
        identifier = new Identifier(number);
    }

    public OneIdentifier(String text) {
        identifier = new Identifier(text);
    }

    @Override
    public String toString() {
        return identifier.toString();
    }
}
