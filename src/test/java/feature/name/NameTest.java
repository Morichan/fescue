package feature.name;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class NameTest {

    Name obj;

    @BeforeEach
    void setup() {
        obj = new Name();
    }

    @Test
    void 名前を設定するとその名前を返す() {
        String expected = "name";

        obj.setNameText("name");
        String actual = obj.getNameText();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void 名前を設定せずに取得しようとすると例外を返す() {
        assertThatThrownBy(() -> obj.getNameText()).isInstanceOf(IllegalStateException.class);
    }

    @Test
    void 名前を複数回設定すると最後に設定した名前を返す() {
        String expected = "lastName";

        obj.setNameText("firstName");
        obj.setNameText("secondName");
        obj.setNameText("lastName");
        String actual = obj.getNameText();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void 名前を設定してその名前を取得した後に別の名前を設定しても取得した名前は変わらない() {
        String expected = "firstName";

        obj.setNameText("firstName");
        String actual = obj.getNameText();
        obj.setNameText("changedName");

        assertThat(actual).isEqualTo(expected);
    }
}