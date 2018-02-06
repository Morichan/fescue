package feature;

import feature.name.Name;
import feature.type.Type;
import feature.value.DefaultValue;
import feature.value.expression.OneIdentifier;
import feature.visibility.Visibility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class AttributeTest {

    Attribute obj;

    @Nested
    class 名前に関して {

        @BeforeEach
        void setup() {
            obj = new Attribute();
        }

        @Test
        void 名前を設定するとその名前を返す() {
            String expected = "name";
            Name name = new Name("name");

            obj.setName(name);
            Name actual = obj.getName();

            assertThat(actual.getNameText()).isEqualTo(expected);
        }

        @Test
        void 名前にnullを設定すると例外を返す() {
            assertThatThrownBy(() -> obj.setName(null)).isInstanceOf(IllegalStateException.class);
        }

        @Test
        void 名前を設定せずに取得しようとすると例外を返す() {
            assertThatThrownBy(() -> obj.getName()).isInstanceOf(NoSuchElementException.class);
        }
    }

    @Nested
    class 可視性に関して {

        @BeforeEach
        void setup() {
            obj = new Attribute();
        }

        @Test
        void 可視性を設定するとその可視性を返す() {
            String expected = "-";

            obj.setVisibility(Visibility.Private);
            Visibility actual = obj.getVisibility();

            assertThat(actual.is(expected)).isTrue();
        }

        @Test
        void 可視性を文字列で設定するとその可視性を返す() {
            String expected = "-";

            obj.setVisibility(Visibility.choose("-"));
            Visibility actual = obj.getVisibility();

            assertThat(actual.is(expected)).isTrue();
        }

        @Test
        void 可視性にnullを設定すると例外を返す() {
            assertThatThrownBy(() -> obj.setVisibility(null)).isInstanceOf(IllegalStateException.class);
        }

        @Test
        void 可視性を設定せずに取得しようとすると例外を返す() {
            assertThatThrownBy(() -> obj.getVisibility()).isInstanceOf(NoSuchElementException.class);
        }
    }

    @Nested
    class 派生に関して {

        @BeforeEach
        void setup() {
            obj = new Attribute();
        }

        @Test
        void 派生に真を設定すると真を返す() {

            obj.setDerived(true);
            boolean actual = obj.isDerived();

            assertThat(actual).isTrue();
        }

        @Test
        void 派生に偽を設定すると偽を返す() {

            obj.setDerived(false);
            boolean actual = obj.isDerived();

            assertThat(actual).isFalse();
        }

        @Test
        void 派生を文字列で設定すると真を返す() {

            obj.setDerived("/");
            boolean actual = obj.isDerived();

            assertThat(actual).isTrue();
        }

        @Test
        void 派生を設定していない場合は偽を返す() {

            boolean actual = obj.isDerived();

            assertThat(actual).isFalse();
        }

        @Test
        void 派生を文字列で設定する際に別の文字列を入力すると例外を返す() {
            assertThatThrownBy(() -> obj.setDerived("notDerivedText")).isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        void 派生を文字列で設定する際にnullを入力すると例外を返す() {
            assertThatThrownBy(() -> obj.setDerived(null)).isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Nested
    class 型に関して {

        @BeforeEach
        void setup() {
            obj = new Attribute();
        }

        @Test
        void 型を設定するとその型を返す() {
            String expected = "int";

            obj.setType(new Type("int"));
            String actual = obj.getType().toString();

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void 型にnullを設定すると例外を返す() {
            assertThatThrownBy(() -> obj.setType(null)).isInstanceOf(IllegalStateException.class);
        }

        @Test
        void 型を設定せずに取得しようとすると例外を返す() {
            assertThatThrownBy(() -> obj.getType()).isInstanceOf(NoSuchElementException.class);
        }
    }

    @Nested
    class 既定値に関して {

        @BeforeEach
        void setup() {
            obj = new Attribute();
        }

        @Test
        void 設定すると既定値を返す() {
            String expected = "1";

            obj.setDefaultValue(new DefaultValue(new OneIdentifier(1)));
            String actual = obj.getDefaultValue().toString();

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void nullを設定すると例外を返す() {
            assertThatThrownBy(() -> obj.setDefaultValue(null)).isInstanceOf(IllegalStateException.class);
        }

        @Test
        void 設定せずに取得しようとすると例外を返す() {
            assertThatThrownBy(() -> obj.getDefaultValue()).isInstanceOf(NoSuchElementException.class);
        }
    }
}