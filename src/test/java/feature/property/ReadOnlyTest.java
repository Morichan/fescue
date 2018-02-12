package feature.property;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ReadOnlyTest {

    ReadOnly obj;

    @Test
    void 文字列に変換する() {
        String expected = "readOnly";

        obj = new ReadOnly();
        String actual = obj.toString();

        assertThat(actual).isEqualTo(expected);
    }
}