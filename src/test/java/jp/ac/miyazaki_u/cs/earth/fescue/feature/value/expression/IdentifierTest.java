package jp.ac.miyazaki_u.cs.earth.fescue.feature.value.expression;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class IdentifierTest {

    Identifier obj;

    @Nested
    class 数値の場合 {

        @BeforeEach
        void setup() {
            obj = new Identifier();
        }

        @Test
        void 数値を入力すると数値を返す() {
            int expected = 1;

            obj.set(1);

            assertThat(obj).hasToString(Integer.toString(expected));
        }

        @Test
        void 何も設定していない際は例外を投げる() {
            assertThatThrownBy(() -> obj.toString()).isInstanceOf(IllegalStateException.class);
        }

        @Test
        void 数値は値である() {

            obj.set(1);

            assertThat(obj.isValue()).isTrue();
        }

        @Test
        void 初期化時に数値を入力すると数値を返す() {
            int expected = 1;

            obj = new Identifier(1);

            assertThat(obj).hasToString(Integer.toString(expected));
        }
    }

    @Nested
    class 文字列の場合 {

        @BeforeEach
        void setup() {
            obj = new Identifier();
        }

        @Test
        void 数値のみの文字列を入力すると文字列を返す() {
            String expected = "1";

            obj.set("1");

            assertThat(obj).hasToString(expected);
        }

        @Test
        void ダブルクオーテーションで囲んだ文字列を入力すると文字列を返す() {
            String expected = "\"stringName\"";

            obj.set("\"stringName\"");

            assertThat(obj).hasToString(expected);
        }

        @Test
        void シングルクオーテーションで囲んだ文字列を入力すると文字列を返す() {
            String expected = "'stringName'";

            obj.set("'stringName'");

            assertThat(obj).hasToString(expected);
        }

        @Test
        void ダブルクオーテーションで囲んだ空文字を入力すると空文字を返す() {
            String expected = "\"\"";

            obj.set("\"\"");

            assertThat(obj).hasToString(expected);
        }

        @Test
        void シングルクオーテーションで囲んだ空文字を入力すると空文字を返す() {
            String expected = "''";

            obj.set("''");

            assertThat(obj).hasToString(expected);
        }

        @Test
        void 数値のみの文字列は値である() {

            obj.set("1");

            assertThat(obj.isValue()).isTrue();
        }

        @Test
        void ダブルクオーテーションで囲んだ文字列は値である() {

            obj.set("\"stringName\"");

            assertThat(obj.isValue()).isTrue();
        }

        @Test
        void シングルクオーテーションで囲んだ文字列は値である() {

            obj.set("'stringName'");

            assertThat(obj.isValue()).isTrue();
        }

        @Test
        void 文字列にnullを入力すると例外を投げる() {
            assertThatThrownBy(() -> obj.set(null)).isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        void 文字列に空文字を入力すると例外を投げる() {
            assertThatThrownBy(() -> obj.set("")).isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        void 何も設定していない際は例外を投げる() {
            assertThatThrownBy(() -> obj.toString()).isInstanceOf(IllegalStateException.class);
        }

        @Test
        void 初期化時にダブルクオーテーションで囲んだ文字列を入力すると文字列を返す() {
            String expected = "\"stringName\"";

            obj = new Identifier("\"stringName\"");

            assertThat(obj).hasToString(expected);
        }
    }

    @Nested
    class 変数の場合 {

        @BeforeEach
        void setup() {
            obj = new Identifier();
        }

        @Test
        void 変数名を入力すると変数名を返す() {
            String expected = "variableName";

            obj.set("variableName");

            assertThat(obj).hasToString(expected);
        }

        @Test
        void 変数名は値ではない() {

            obj.set("variableName");

            assertThat(obj.isValue()).isFalse();
        }

        @Test
        void 変数名にnullを入力すると例外を投げる() {
            assertThatThrownBy(() -> obj.set(null)).isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        void 変数名に空文字を入力すると例外を投げる() {
            assertThatThrownBy(() -> obj.set("")).isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        void 何も設定していない際は例外を投げる() {
            assertThatThrownBy(() -> obj.toString()).isInstanceOf(IllegalStateException.class);
        }

        @Test
        void 初期化時に変数名を入力すると変数名を返す() {
            String expected = "variableName";

            obj = new Identifier("variableName");

            assertThat(obj).hasToString(expected);
        }
    }

    @Nested
    class 文字列か変数名かを判定する場合 {

        @BeforeEach
        void setup() {
            obj = new Identifier();
        }

        @Test
        void シングルクオーテーションで囲んだ文字列は値である() {

            boolean actual = obj.isValue("'string'");

            assertThat(actual).isTrue();
        }

        @Test
        void シングルクオーテーションをシングルクオーテーションで囲んだ文字列は値である() {

            boolean actual = obj.isValue("'''");

            assertThat(actual).isTrue();
        }

        @Test
        void ダブルクオーテーションをシングルクオーテーションで囲んだ文字列は値である() {

            boolean actual = obj.isValue("'\"'");

            assertThat(actual).isTrue();
        }

        @Test
        void シングルクオーテーション1文字は値ではない() {

            boolean actual = obj.isValue("'");

            assertThat(actual).isFalse();
        }

        @Test
        void ダブルクオーテーションで囲んだ文字列は値である() {

            boolean actual = obj.isValue("\"string\"");

            assertThat(actual).isTrue();
        }

        @Test
        void シングルクオーテーションをダブルクオーテーションで囲んだ文字列は値である() {

            boolean actual = obj.isValue("\"'\"");

            assertThat(actual).isTrue();
        }

        @Test
        void ダブルクオーテーションをダブルクオーテーションで囲んだ文字列は値である() {

            boolean actual = obj.isValue("\"\"\"");

            assertThat(actual).isTrue();
        }

        @Test
        void ダブルクオーテーション1文字は値ではない() {

            boolean actual = obj.isValue("\"");

            assertThat(actual).isFalse();
        }

        @Test
        void シングルクオーテーションとダブルクオーテーションで囲んだ文字列は値ではない() {

            boolean actual = obj.isValue("'notString\"");

            assertThat(actual).isFalse();
        }

        @Test
        void ダブルクオーテーションとシングルクオーテーションで囲んだ文字列は値ではない() {

            boolean actual = obj.isValue("\"notString'");

            assertThat(actual).isFalse();
        }

        @Test
        void シングルクオーテーションとダブルクオーテーションで囲んだ空文字は値ではない() {

            boolean actual = obj.isValue("'\"");

            assertThat(actual).isFalse();
        }

        @Test
        void ダブルクオーテーションとシングルクオーテーションで囲んだ空文字は値ではない() {

            boolean actual = obj.isValue("\"'");

            assertThat(actual).isFalse();
        }
    }
}