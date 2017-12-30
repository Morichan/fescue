package usage;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import parser.ClassesLexer;
import parser.ClassesParser;

public class AttributeEvaluation {
    private ClassesEvalListener listener;
    private ClassesParser.PropertyContext context;

    private ClassesLexer lexer;
    private CommonTokenStream tokens;
    private ClassesParser parser;
    private ParseTree tree;
    private ParseTreeWalker walker;

    private String attribute;

    public void setAttribute(String text) {
        attribute = text;
    }

    public String getAttribute() {
        return attribute;
    }

    public String extractClassName() {
        String name = "";
        for (int i = 0; i < context.getChildCount(); i++) {
            if (context.getChild(i) instanceof ClassesParser.NameContext) {
                name = context.getChild(i).getText();
                break;
            }
        }
        if (name.length() <= 0) throw new IllegalArgumentException();
        return name;
    }

    public String extractVisibility() {
        String visibility = "";
        for (int i = 0; i < context.getChildCount(); i++) {
            if (context.getChild(i) instanceof ClassesParser.VisibilityContext) {
                visibility = context.getChild(i).getText();
                break;
            }
        }
        return visibility;
    }

    public String extractDivided() {
        String divided = "";
        for (int i = 0; i < context.getChildCount(); i++) {
            if (context.getChild(i) instanceof ClassesParser.DividedContext) {
                divided = context.getChild(i).getText();
                break;
            }
        }
        return divided;
    }

    public String extractPropType() {
        String propType = "";
        for (int i = 0; i < context.getChildCount(); i++) {
            if (context.getChild(i) instanceof ClassesParser.PropTypeContext) {
                propType = context.getChild(i).getChild(1).getText();
                break;
            }
        }
        return propType;
    }

    public void walk() {
        if(attribute == null) throw new IllegalArgumentException();

        lexer = new ClassesLexer(CharStreams.fromString(attribute));
        tokens = new CommonTokenStream(lexer);
        parser = new ClassesParser(tokens);
        tree = parser.property();
        walker = new ParseTreeWalker();
        listener = new ClassesEvalListener();
        walker.walk(listener, tree);
        context = listener.getProperty();
    }
}
