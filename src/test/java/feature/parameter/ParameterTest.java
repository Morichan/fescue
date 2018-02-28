package feature.parameter;

import feature.direction.InOut;
import feature.multiplicity.Bounder;
import feature.multiplicity.MultiplicityRange;
import feature.name.Name;
import feature.type.Type;
import feature.value.DefaultValue;
import feature.value.expression.OneIdentifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class ParameterTest {

    Parameter obj;

    @Nested
    class 方向について {

        @BeforeEach
        void setup() {
            obj = new Parameter();
        }

        @Test
        void 設定すると方向を返す() {
            String expected = "inout";

            obj.setDirection(new InOut());
            String actual = obj.getDirection().toString();

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void nullを設定すると例外を投げる() {
            assertThatThrownBy(() -> obj.setDirection(null)).isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        void 設定前に取得しようとすると例外を返す() {
            assertThatThrownBy(() -> obj.getDirection()).isInstanceOf(IllegalStateException.class);
        }
    }

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

    @Nested
    class 多重度について {

        @BeforeEach
        void setup() {
            obj = new Parameter();
        }

        @Test
        void 設定すると型を返す() {
            String expected = "*";

            obj.setMultiplicityRange(new MultiplicityRange(new Bounder("*")));
            String actual = obj.getMultiplicityRange().toString();

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void nullを設定すると例外を投げる() {
            assertThatThrownBy(() -> obj.setMultiplicityRange(null)).isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        void 設定前に取得しようとすると例外を返す() {
            assertThatThrownBy(() -> obj.getMultiplicityRange()).isInstanceOf(IllegalStateException.class);
        }
    }

    @Nested
    class 既定値について {

        @BeforeEach
        void setup() {
            obj = new Parameter();
        }

        @Test
        void 設定すると型を返す() {
            String expected = "10";

            obj.setDefaultValue(new DefaultValue(new OneIdentifier(10)));
            String actual = obj.getDefaultValue().toString();

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void nullを設定すると例外を投げる() {
            assertThatThrownBy(() -> obj.setDefaultValue(null)).isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        void 設定前に取得しようとすると例外を返す() {
            assertThatThrownBy(() -> obj.getDefaultValue()).isInstanceOf(IllegalStateException.class);
        }
    }
}