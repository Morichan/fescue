package io.github.morichan.fescue.feature.visibility;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class VisibilityTest {
    Visibility obj;

    @Nested
    class インスタンス状態がPublicの場合 {

        @BeforeEach
        void setup() {
            obj = Visibility.Public;
        }

        @Test
        void Publicの際は真を返す() {
            assertThat(obj.is("+")).isTrue();
        }

        @Test
        void Publicでない際は偽を返す() {
            assertAll(
                    () -> assertThat(obj.is("-")).isFalse(),
                    () -> assertThat(obj.is("~")).isFalse(),
                    () -> assertThat(obj.is("#")).isFalse());
        }

        @Test
        void 可視性を文字列で出力する() {
            String expected = "+";

            String actual = obj.toString();

            assertThat(actual).isEqualTo(expected);
        }
    }

    @Nested
    class インスタンス状態がPrivateの場合 {

        @BeforeEach
        void setup() {
            obj = Visibility.Private;
        }

        @Test
        void Privateの際は真を返す() {
            assertThat(obj.is("-")).isTrue();
        }

        @Test
        void Privateでない際は偽を返す() {
            assertAll(
                    () -> assertThat(obj.is("+")).isFalse(),
                    () -> assertThat(obj.is("~")).isFalse(),
                    () -> assertThat(obj.is("#")).isFalse());
        }

        @Test
        void 可視性を文字列で出力する() {
            String expected = "-";

            String actual = obj.toString();

            assertThat(actual).isEqualTo(expected);
        }
    }

    @Nested
    class インスタンス状態がPackageの場合 {

        @BeforeEach
        void setup() {
            obj = Visibility.Package;
        }

        @Test
        void Packageの際は真を返す() {
            assertThat(obj.is("~")).isTrue();
        }

        @Test
        void Packageでない際は偽を返す() {
            assertAll(
                    () -> assertThat(obj.is("+")).isFalse(),
                    () -> assertThat(obj.is("-")).isFalse(),
                    () -> assertThat(obj.is("#")).isFalse());
        }

        @Test
        void 可視性を文字列で出力する() {
            String expected = "~";

            String actual = obj.toString();

            assertThat(actual).isEqualTo(expected);
        }
    }

    @Nested
    class インスタンス状態がProtectedの場合 {

        @BeforeEach
        void setup() {
            obj = Visibility.Protected;
        }

        @Test
        void Protectedの際は真を返す() {
            assertThat(obj.is("#")).isTrue();
        }

        @Test
        void Protectedでない際は偽を返す() {
            assertAll(
                    () -> assertThat(obj.is("+")).isFalse(),
                    () -> assertThat(obj.is("-")).isFalse(),
                    () -> assertThat(obj.is("~")).isFalse());
        }

        @Test
        void 可視性を文字列で出力する() {
            String expected = "#";

            String actual = obj.toString();

            assertThat(actual).isEqualTo(expected);
        }
    }

    @Nested
    class インスタンス状態が未定義の場合 {

        @BeforeEach
        void setup() {
            obj = Visibility.Undefined;
        }

        @Test
        void どの状態でも偽を返す() {
            assertAll(
                    () -> assertThat(obj.is("+")).isFalse(),
                    () -> assertThat(obj.is("-")).isFalse(),
                    () -> assertThat(obj.is("~")).isFalse(),
                    () -> assertThat(obj.is("#")).isFalse());
        }

        @Test
        void Publicの文字列を選択するとPublicを返す() {
            Visibility expected = Visibility.Public;

            Visibility actual = Visibility.choose("+");

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void Privateの文字列を選択するとPrivateを返す() {
            Visibility expected = Visibility.Private;

            Visibility actual = Visibility.choose("-");

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void Packageの文字列を選択するとPackageを返す() {
            Visibility expected = Visibility.Package;

            Visibility actual = Visibility.choose("~");

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void Protectedの文字列を選択するとProtectedを返す() {
            Visibility expected = Visibility.Protected;

            Visibility actual = Visibility.choose("#");

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void 可視性の文字列以外を選択すると例外を投げる() {
            assertThatThrownBy(() -> Visibility.choose("E")).isInstanceOf(IllegalStateException.class);
        }

        @Test
        void 可視性の選択にnullを入力すると例外を投げる() {
            assertThatThrownBy(() -> Visibility.choose(null)).isInstanceOf(IllegalStateException.class);
        }

        @Test
        void 可視性のオブジェクト自体を文字列にすると例外を投げる() {
            assertThatThrownBy(() -> obj.toString()).isInstanceOf(IllegalStateException.class);
        }
    }

    @Nested
    class インスタンスを生成していない場合 {

        @Test
        void 判定しようとすると例外を返す() {
            assertThatThrownBy(() -> obj.is("~")).isInstanceOf(NullPointerException.class);
        }
    }
}