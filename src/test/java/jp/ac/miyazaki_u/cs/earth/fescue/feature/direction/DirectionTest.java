package jp.ac.miyazaki_u.cs.earth.fescue.feature.direction;

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

            obj.setOuted(true);
            String actual = obj.toString();

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void 出力しない際は空文字を返す() {

            obj.setOuted(false);
            String actual = obj.toString();

            assertThat(actual).isEmpty();
        }

        @Test
        void 出力するかどうかは既定値として偽を返す() {
            assertThat(obj.isOuted()).isFalse();
        }

        @Test
        void 出力するかどうかを設定するとその設定値を返す() {
            obj.setOuted(false);
            boolean actual = obj.isOuted();

            assertThat(actual).isFalse();
        }

        @Test
        void コンストラクタで出力する設定を行う場合は入力文字列を返す() {
            String expected = "in";

            obj = new In(true);
            String actual = obj.toString();

            assertThat(actual).isEqualTo(expected);
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

            obj.setOuted(true);
            String actual = obj.toString();

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void 出力しない際は例外を投げる() {
            assertThatThrownBy(() -> obj.setOuted(false)).isInstanceOf(IllegalCallerException.class);
        }

        @Test
        void 出力するかどうかは既定値として真を返す() {
            assertThat(obj.isOuted()).isTrue();
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

            obj.setOuted(true);
            String actual = obj.toString();

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void 出力しない際は例外を投げる() {
            assertThatThrownBy(() -> obj.setOuted(false)).isInstanceOf(IllegalCallerException.class);
        }

        @Test
        void 出力するかどうかは既定値として真を返す() {
            assertThat(obj.isOuted()).isTrue();
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

            obj.setOuted(true);
            String actual = obj.toString();

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void 出力しない際は例外を投げる() {
            assertThatThrownBy(() -> obj.setOuted(false)).isInstanceOf(IllegalCallerException.class);
        }

        @Test
        void 出力するかどうかは既定値として真を返す() {
            assertThat(obj.isOuted()).isTrue();
        }
    }
}