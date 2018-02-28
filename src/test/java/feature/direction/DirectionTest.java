package feature.direction;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class DirectionTest {

    Direction obj;

    @Nested
    class 入力の場合 {

        @BeforeEach
        void setup() {
            obj = new In();
        }

        @Test
        void 空文字を返す() {

            String actual = obj.toString();

            assertThat(actual).isEmpty();
        }

        @Test
        void 出力する際は入力文字列を返す() {
            String expected = "in";

            obj.isOuted();
            String actual = obj.toString();

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void 出力しない際は空文字を返す() {

            obj.isNotOuted();
            String actual = obj.toString();

            assertThat(actual).isEmpty();
        }
    }

    @Nested
    class 出力の場合 {

        @BeforeEach
        void setup() {
            obj = new Out();
        }

        @Test
        void 出力文字列を返す() {
            String expected = "out";

            String actual = obj.toString();

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void 出力する際は出力文字列を返す() {
            String expected = "out";

            obj.isOuted();
            String actual = obj.toString();

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void 出力しない際は例外を投げる() {
            assertThatThrownBy(() -> obj.isNotOuted()).isInstanceOf(IllegalCallerException.class);
        }
    }

    @Nested
    class 入出力の場合 {

        @BeforeEach
        void setup() {
            obj = new InOut();
        }

        @Test
        void 入出力文字列を返す() {
            String expected = "inout";

            String actual = obj.toString();

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void 出力する際は入出力文字列を返す() {
            String expected = "inout";

            obj.isOuted();
            String actual = obj.toString();

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void 出力しない際は例外を投げる() {
            assertThatThrownBy(() -> obj.isNotOuted()).isInstanceOf(IllegalCallerException.class);
        }
    }

    @Nested
    class 戻り値の場合 {

        @BeforeEach
        void setup() {
            obj = new Return();
        }

        @Test
        void 戻り値文字列を返す() {
            String expected = "return";

            String actual = obj.toString();

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void 出力する際は戻り値文字列を返す() {
            String expected = "return";

            obj.isOuted();
            String actual = obj.toString();

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void 出力しない際は例外を投げる() {
            assertThatThrownBy(() -> obj.isNotOuted()).isInstanceOf(IllegalCallerException.class);
        }
    }
}