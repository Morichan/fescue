package io.github.morichan.fescue.evaluation;

import mockit.MockUp;
import org.antlr.v4.runtime.InputMismatchException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FeatureEvaluationTest {

    FeatureEvaluation obj;

    @Nested
    class 例外処理に関して {

        @BeforeEach
        void setup() {
            setObj();
        }

        @Test
        void 何も設定していない場合は何も返さない() {
            obj.checkIfNameIsSamePrimitiveType();
        }

        @Test
        void 例外を設定した場合は例外を返す() {
            obj.setInputMismatchException(
                    new MockUp<InputMismatchException>() {
                    }.getMockInstance());

            assertThatThrownBy(() -> obj.checkIfNameIsSamePrimitiveType()).isInstanceOf(InputMismatchException.class);
        }

        @Test
        void 例外を設定後に初期化した場合は何も返さない() {
            obj.setInputMismatchException(
                    new MockUp<InputMismatchException>() {
                    }.getMockInstance());

            obj.initIfIsSameBetweenNameAndKeyword();

            obj.checkIfNameIsSamePrimitiveType();
        }
    }

    @Nested
    class 多重度の文字列置換に関して {

        @BeforeEach
        void setup() {
            setObj();
        }

        @Test
        void 空文字を入力した場合は空文字を返す() {
            String expected = "";

            String actual = obj.insertSpaceBothEndsOfRangeOperator("");

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void ドットを入力した場合はドットを返す() {
            String expected = ".";

            String actual = obj.insertSpaceBothEndsOfRangeOperator(".");

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void ドット2つを入力した場合はドット2つの両端にスペースを挿入した文字列を返す() {
            String expected = " .. ";

            String actual = obj.insertSpaceBothEndsOfRangeOperator("..");

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void ドット2つの間にスペースを含む文字列を入力した場合はその文字列を返す() {
            String expected = ". .";

            String actual = obj.insertSpaceBothEndsOfRangeOperator(". .");

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void ドット2つの両端に数字を含む文字列を入力した場合はドット2つの両端と数値の間にスペースを挿入した文字列を返す() {
            String expected = "0 .. 1";

            String actual = obj.insertSpaceBothEndsOfRangeOperator("0..1");

            assertThat(actual).isEqualTo(expected);
        }
    }

    private void setObj() {
        obj = new FeatureEvaluation() {
            @Override
            public void setText(String text) {
            }

            @Override
            public String getText() {
                return null;
            }

            @Override
            public void walk() {
            }
        };
    }
}