package feature;

import feature.name.Name;
import feature.type.Type;
import feature.visibility.Visibility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class OperationTest {
    Operation obj;

    @Nested
    class 名前の場合 {

        @BeforeEach
        void setup() {
            obj = new Operation();
        }

        @Test
        void 設定すると名前を返す() {
            String expected = "name";
            Name name = new Name("name");

            obj.setName(name);
            Name actual = obj.getName();

            assertThat(actual.getNameText()).isEqualTo(expected);
        }

        @Test
        void nullを設定すると例外を返す() {
            assertThatThrownBy(() -> obj.setName(null)).isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        void 設定せずに取得しようとすると例外を返す() {
            assertThatThrownBy(() -> obj.getName()).isInstanceOf(IllegalStateException.class);
        }
    }

    @Nested
    class 可視性に関して {

        @BeforeEach
        void setup() {
            obj = new Operation();
        }

        @Test
        void 設定すると可視性を返す() {
            String expected = "-";

            obj.setVisibility(Visibility.Private);
            Visibility actual = obj.getVisibility();

            assertThat(actual.is(expected)).isTrue();
        }

        @Test
        void 文字列で設定すると可視性を返す() {
            String expected = "-";

            obj.setVisibility(Visibility.choose("-"));
            Visibility actual = obj.getVisibility();

            assertThat(actual.is(expected)).isTrue();
        }

        @Test
        void nullを設定すると例外を返す() {
            assertThatThrownBy(() -> obj.setVisibility(null)).isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        void 設定せずに取得しようとすると例外を返す() {
            assertThatThrownBy(() -> obj.getVisibility()).isInstanceOf(IllegalStateException.class);
        }
    }

    @Nested
    class 戻り値の型に関して {

        @BeforeEach
        void setup() {
            obj = new Operation();
        }

        @Test
        void 設定すると型を返す() {
            String expected = "int";

            obj.setReturnType(new Type("int"));
            String actual = obj.getReturnType().toString();

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void nullを設定すると例外を返す() {
            assertThatThrownBy(() -> obj.setReturnType(null)).isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        void 設定せずに取得しようとすると例外を返す() {
            assertThatThrownBy(() -> obj.getReturnType()).isInstanceOf(IllegalStateException.class);
        }
    }
}