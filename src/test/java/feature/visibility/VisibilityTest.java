package feature.visibility;

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
    }

    @Nested
    class インスタンス状態を定義していない場合 {

        @Test
        void 判定しようとすると例外を返す() {
            assertThatThrownBy(() -> obj.is("~")).isInstanceOf(NullPointerException.class);
        }
    }
}