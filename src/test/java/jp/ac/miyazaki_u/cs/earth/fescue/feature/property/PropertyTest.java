package jp.ac.miyazaki_u.cs.earth.fescue.feature.property;

import jp.ac.miyazaki_u.cs.earth.fescue.feature.value.expression.Binomial;
import jp.ac.miyazaki_u.cs.earth.fescue.feature.value.expression.Expression;
import jp.ac.miyazaki_u.cs.earth.fescue.feature.value.expression.MethodCall;
import jp.ac.miyazaki_u.cs.earth.fescue.feature.value.expression.OneIdentifier;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class PropertyTest {

    Property obj;

    @Nested
    class ReadOnlyの場合 {

        @Test
        void 文字列に変換する() {
            String expected = "readOnly";

            obj = new ReadOnly();
            String actual = obj.toString();

            assertThat(actual).isEqualTo(expected);
        }
    }

    @Nested
    class Unionの場合 {

        @Test
        void 文字列に変換する() {
            String expected = "union";

            obj = new Union();
            String actual = obj.toString();

            assertThat(actual).isEqualTo(expected);
        }
    }

    @Nested
    class Orderedの場合 {

        @Test
        void 文字列に変換する() {
            String expected = "ordered";

            obj = new Ordered();
            String actual = obj.toString();

            assertThat(actual).isEqualTo(expected);
        }
    }

    @Nested
    class Uniqueの場合 {

        @Test
        void 文字列に変換する() {
            String expected = "unique";

            obj = new Unique();
            String actual = obj.toString();

            assertThat(actual).isEqualTo(expected);
        }
    }

    @Nested
    class Queryの場合 {

        @Test
        void 文字列に変換する() {
            String expected = "query";

            obj = new Query();
            String actual = obj.toString();

            assertThat(actual).isEqualTo(expected);
        }
    }

    @Nested
    class Redefinesの場合 {

        @Test
        void 式を設定すると文字列に変換する() {
            String expected = "redefines ClassName.method()";
            Expression propertyName = new Binomial(".", new OneIdentifier("ClassName"), new MethodCall("method"));

            obj = new Redefines(propertyName);
            String actual = obj.toString();

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void 式としてnullを設定すると例外を投げる() {
            assertThatThrownBy(() -> obj = new Redefines(null)).isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Nested
    class Subsetsの場合 {

        @Test
        void 式を設定すると文字列に変換する() {
            String expected = "subsets instance";
            Expression propertyName = new OneIdentifier("instance");

            obj = new Subsets(propertyName);
            String actual = obj.toString();

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void 式としてnullを設定すると例外を投げる() {
            assertThatThrownBy(() -> obj = new Subsets(null)).isInstanceOf(IllegalArgumentException.class);
        }
    }
}