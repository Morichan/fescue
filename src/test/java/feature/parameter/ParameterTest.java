package feature.parameter;

import feature.name.Name;
import feature.type.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class ParameterTest {

    Parameter obj;

    @Nested
    class パラメータ名について {

        @BeforeEach
        void setup() {
            obj = new Parameter();
        }

        @Test
        void 設定すると名前を返す() {
            String expected = "parameterName";

            obj.setName(new Name("parameterName"));
            String actual = obj.getName().toString();

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void nullを設定すると例外を投げる() {
            assertThatThrownBy(() -> obj.setName(null)).isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        void 設定前に取得しようとすると例外を返す() {
            assertThatThrownBy(() -> obj.getName()).isInstanceOf(IllegalStateException.class);
        }
    }

    @Nested
    class パラメータの型について {

        @BeforeEach
        void setup() {
            obj = new Parameter();
        }

        @Test
        void 設定すると型を返す() {
            String expected = "int";

            obj.setType(new Type("int"));
            String actual = obj.getType().toString();

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void nullを設定すると例外を投げる() {
            assertThatThrownBy(() -> obj.setType(null)).isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        void 設定前に取得しようとすると例外を返す() {
            assertThatThrownBy(() -> obj.getType()).isInstanceOf(IllegalStateException.class);
        }
    }
}