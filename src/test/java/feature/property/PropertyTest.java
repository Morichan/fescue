package feature.property;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
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
}