package usage;

import parser.ClassesParser;

public class ClassesEvalListener extends parser.ClassesBaseListener {
    private ClassesParser.PropertyContext property = null;

    @Override
    public void enterProperty(ClassesParser.PropertyContext ctx) {
        property = ctx;
    }

    public ClassesParser.PropertyContext getProperty() {
        return property;
    }
}
