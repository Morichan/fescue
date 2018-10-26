package io.github.morichan.fescue.evaluation;

import org.antlr.v4.runtime.InputMismatchException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import io.github.morichan.fescue.parser.ClassFeatureParser;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AttributeEvaluationTest {
    AttributeEvaluation obj;

    @Nested
    class 正しい文を入力している場合 {

        @BeforeEach
        void setup() {
            obj = new AttributeEvaluation();
        }

        @Test
        void 文を設定すると文を返す() {
            String expected = "attribute";

            obj.setText(expected);
            String actual = obj.getText();

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void 文を設定して走査するとエラーを返さない() {
            String expected = "attribute";
            String actual;

            obj.setText("attribute");
            obj.walk();
            actual = obj.getText();

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void 文を設定して走査するとコンテキストを返す() {
            ClassFeatureParser.PropertyContext actual;

            obj.setText("- /attribute");
            obj.walk();
            actual = obj.getContext();

            assertThat(actual).isInstanceOf(ClassFeatureParser.PropertyContext.class);
        }
    }

    @Nested
    class 正しい文を入力していない場合 {

        @Test
        void 文を設定せずに走査したらエラーを返す() {
            obj = new AttributeEvaluation();

            assertThatThrownBy(() -> obj.walk()).isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        void 文を設定せずに文を取得しようとしたら例外を投げる() {
            obj = new AttributeEvaluation();

            assertThatThrownBy(() -> obj.getText()).isInstanceOf(IllegalStateException.class);
        }

        @Test
        void 正しくない文を設定して走査したらエラーを返す() {
            obj = new AttributeEvaluation();

            obj.setText("- Integer");

            assertThatThrownBy(() -> obj.walk()).isInstanceOf(InputMismatchException.class);
        }

        @Test
        void 文を走査せずにコンテキストを取得しようとすると例外を投げる() {
            obj = new AttributeEvaluation();

            obj.setText("attribute");

            assertThatThrownBy(() -> obj.getContext()).isInstanceOf(IllegalStateException.class);
        }
    }
}