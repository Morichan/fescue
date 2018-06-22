package jp.ac.miyazaki_u.cs.earth.fescue.feature.name;

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

    @Test
    void 名前に空文字を設定すると例外を返す() {
        assertThatThrownBy(() -> obj.setNameText("")).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 名前にnullを設定すると例外を返す() {
        assertThatThrownBy(() -> obj.setNameText(null)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 初期化時に名前を設定するとその名前を返す() {
        String expected = "name";

        obj = new Name("name");
        String actual = obj.getNameText();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void 初期化時に名前に空文字を設定すると例外を返す() {
        assertThatThrownBy(() -> obj = new Name("")).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 初期化時に名前にnullを設定すると例外を返す() {
        assertThatThrownBy(() -> obj = new Name(null)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 初期化時に名前を設定した後に別の名前を設定すると最後に設定した名前を返す() {
        String expected = "changedName";

        obj = new Name("initializedName");
        obj.setNameText("changedName");
        String actual = obj.getNameText();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void オブジェクト自体を文字列にするとその名前を返す() {
        String expected = "name";

        obj.setNameText("name");
        String actual = obj.toString();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void オブジェクト自体を文字列にする際に名前を設定していなかった場合は例外を返す() {
        assertThatThrownBy(() -> obj.toString()).isInstanceOf(IllegalStateException.class);
    }

    @Test
    void オブジェクト自体を出力する際に名前を設定していなかった場合は例外を返す() {
        assertThatThrownBy(() -> System.out.println(obj)).isInstanceOf(IllegalStateException.class);
    }
}