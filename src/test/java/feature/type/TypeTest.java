package feature.type;

import feature.name.Name;
import net.java.quickcheck.Generator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static net.java.quickcheck.generator.PrimitiveGenerators.strings;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class TypeTest {

    Type obj;

    List<String> predefinedTypeNames = Arrays.asList(
            "bool", "boolean",
            "c", "char", "character",
            "i8", "int8", "int8_t", "byte",
            "i16", "int16", "int16_t", "short",
            "i32", "int32", "int32_t", "int", "integer",
            "i64", "int64", "int64_t", "long",
            "f32", "float",
            "lf", "f64", "double",
            "void");
    final Generator<String> exceptionTypeNameGenerator = strings(1, 1);

    final int predefinedTypeNameCount = 28;

    @BeforeEach
    void setup() {
        obj = new Type();
    }

    @Test
    void 名前を設定すると名前を返す() {
        Name expected = new Name("int");

        obj.setName(new Name("int"));
        Name actual = obj.getName();

        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    @Test
    void 名前にnullを設定しようとすると例外を投げる() {
        assertThatThrownBy(() -> obj.setName(null)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 名前を設定せずに名前を取得しようとすると例外を投げる() {
        assertThatThrownBy(() -> obj.getName()).isInstanceOf(IllegalStateException.class);
    }

    @Test
    void 名前に型の名前以外を設定しようとすると例外を投げる() {
        Name exceptionName = new Name("notTypeName");

        assertThatThrownBy(() -> obj.setName(exceptionName)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 初期化時に文字列で名前を設定すると名前を返す() {
        String expected = "double";

        obj = new Type("double");
        String actual = obj.getName().toString();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void 初期化時に空文字を設定しようとすると例外を投げる() {
        assertThatThrownBy(() -> obj = new Type("")).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 初期化時に定義済みでない名前を設定しようとすると例外を投げる() {
        assertThatThrownBy(() -> obj = new Type("notTypeName")).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void オブジェクト自体を文字列にすると名前を返す() {
        String expected = "float";

        obj = new Type("float");

        assertThat(obj.toString()).isEqualTo(expected);
    }

    @Test
    void オブジェクト自体を文字列にする際に名前を設定していなかった場合は例外を返す() {
        assertThatThrownBy(() -> obj.toString()).isInstanceOf(IllegalStateException.class);
    }

    @Test
    void オブジェクト自体を出力する際に名前を設定していなかった場合は例外を返す() {
        assertThatThrownBy(() -> System.out.println(obj)).isInstanceOf(IllegalStateException.class);
    }

    @RepeatedTest(predefinedTypeNameCount)
    void 定義済みの名前に全て対応している(RepetitionInfo info) {
        // 例外を投げない
        obj.setName(new Name(predefinedTypeNames.get(info.getCurrentRepetition() - 1)));
    }

    @RepeatedTest(100)
    void 定義済みの名前以外に全て例外を返す() {
        Name name = new Name(createExceptionTypeName());

        assertThatThrownBy(() -> obj.setName(name))
                .as(name.getNameText())
                .isInstanceOf(IllegalArgumentException.class);
    }

    @RepeatedTest(predefinedTypeNameCount)
    void 初期化時に定義済みの名前に全て対応している(RepetitionInfo info) {
        // 例外を投げない
        obj = new Type(predefinedTypeNames.get(info.getCurrentRepetition() - 1));
    }

    @RepeatedTest(100)
    void 初期化時に定義済みの名前以外に全て例外を返す() {
        String name = createExceptionTypeName();

        assertThatThrownBy(() -> obj = new Type(name))
                .as(name)
                .isInstanceOf(IllegalArgumentException.class);
    }

    private String createExceptionTypeName() {
        String name;

        do {
            name = exceptionTypeNameGenerator.next();
        } while (predefinedTypeNames.contains(name));

        return name;
    }
}