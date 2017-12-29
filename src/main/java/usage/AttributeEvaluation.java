package usage;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import parser.ClassesLexer;
import parser.ClassesParser;

public class AttributeEvaluation {
    private ClassesEvalListener obj;

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

    public void extractClassName() {

    }

    public void walk() {
        if(attribute == null) throw new IllegalArgumentException();
        lexer = new ClassesLexer(CharStreams.fromString(attribute));
        tokens = new CommonTokenStream(lexer);
        parser = new ClassesParser(tokens);
        tree = parser.property();
        walker = new ParseTreeWalker();
        obj = new ClassesEvalListener();
        walker.walk(obj, tree);
    }
}
