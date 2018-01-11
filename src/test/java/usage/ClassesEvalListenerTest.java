package usage;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import parser.ClassesLexer;
import parser.ClassesParser;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.*;

class ClassesEvalListenerTest {
    ClassesEvalListener obj;

    ClassesLexer lexer;
    CommonTokenStream tokens;
    ClassesParser parser;
    ParseTree tree;
    ParseTreeWalker walker;

    @Nested
    class 属性に関して {

        @Nested
        class 正しい文法の場合 {

            final String tested = "- attribute";

            @BeforeEach
            void setup() {
                walk(tested);
            }

            @Test
            void 構文数として2を返す() {
                int expected = 2;

                int actual = obj.getProperty().getChildCount();

                assertThat(actual).isEqualTo(expected);
            }

            @Test
            void 属性名としてattributeを返す() {
                String expected = "attribute";

                String actual = obj.getProperty().getChild(1).getText();

                assertThat(actual).isEqualTo(expected);
            }

            @Test
            void 可視性としてPrivateを返す() {
                String expected = "-";

                String actual = obj.getProperty().getChild(0).getText();

                assertThat(actual).isEqualTo(expected);
            }

            @Test
            void 別の文法を走査するとその結果を返す() {
                String expected = "otherAttribute";
                walk("~ otherAttribute");

                String actual = obj.getProperty().getChild(1).getText();

                assertThat(actual).isEqualTo(expected);
            }
        }

        @Nested
        class 正しくない文法の場合 {

            @Test
            void 空文字を走査してからコンテキストを取得しようとするとnullを返す() {
                walk("");

                assertThatThrownBy(() -> obj.getProperty().getChild(0).getText()).isInstanceOf(NullPointerException.class);
            }
        }

        private void walk(String text) {
            lexer = new ClassesLexer(CharStreams.fromString(text));
            tokens = new CommonTokenStream(lexer);
            parser = new ClassesParser(tokens);
            tree = parser.property();
            walker = new ParseTreeWalker();
            obj = new ClassesEvalListener();
            walker.walk(obj, tree);
        }
    }

    @Nested
    class 操作に関して {

        @Nested
        class 正しい文法の場合 {

            final String tested = "+ operation()";

            @BeforeEach
            void setup() {
                walk(tested);
            }

            @Test
            void 構文数として3を返す() {
                int expected = 3;

                int actual = obj.getOperation().getChildCount();

                assertThat(actual).isEqualTo(expected);
            }

            @Test
            void 属性名としてoperationを返す() {
                String expected = "operation";

                String actual = obj.getOperation().getChild(1).getText();

                assertThat(actual).isEqualTo(expected);
            }

            @Test
            void 可視性としてPublicを返す() {
                String expected = "+";

                String actual = obj.getOperation().getChild(0).getText();

                assertThat(actual).isEqualTo(expected);
            }

            @Test
            void 別の文法を走査するとその結果を返す() {
                String expected = "otherOperation";
                walk("# otherOperation()");

                String actual = obj.getOperation().getChild(1).getText();

                assertThat(actual).isEqualTo(expected);
            }
        }

        @Nested
        class 正しくない文法の場合 {

            @Test
            void 空文字を走査してからコンテキストを取得しようとするとnullを返す() {
                walk("");

                assertThatThrownBy(() -> obj.getOperation().getChild(0).getText()).isInstanceOf(NullPointerException.class);
            }
        }

        private void walk(String text) {
            lexer = new ClassesLexer(CharStreams.fromString(text));
            tokens = new CommonTokenStream(lexer);
            parser = new ClassesParser(tokens);
            tree = parser.operation();
            walker = new ParseTreeWalker();
            obj = new ClassesEvalListener();
            walker.walk(obj, tree);
        }
    }
}