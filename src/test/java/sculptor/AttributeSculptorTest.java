package sculptor;

import feature.Attribute;
import feature.name.Name;
import org.antlr.v4.runtime.ParserRuleContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import parser.ClassFeatureParser;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class AttributeSculptorTest {

    AttributeSculptor obj;

    @Nested
    class 正しい属性文の際 {

        @BeforeEach
        void setup() {
            obj = new AttributeSculptor();
        }

        @Test
        void 取得するコンテキストがPropertyContext型である() {
            String text = "- attribute";

            obj.parse(text);
            ParserRuleContext actual = obj.getContext();

            assertThat(actual).isInstanceOf(ClassFeatureParser.PropertyContext.class);
        }

        @Nested
        class 名前のみの場合 {

            @BeforeEach
            void setup() {
                obj = new AttributeSculptor();
            }

            @Test
            void 設定した文字列を返す() {
                Attribute expected = new Attribute(new Name("name"));

                obj.parse("name");
                Attribute actual = obj.carve();

                assertThat(actual).isEqualToComparingFieldByFieldRecursively(expected);
            }
        }
    }

    @Nested
    class 不正な属性文の際 {

        @BeforeEach
        void setup() {
            obj = new AttributeSculptor();
        }

        @Test
        void 空文字をパースしようとすると例外を投げる() {
            assertThatThrownBy(() -> obj.parse("")).isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        void nullをパースしようとすると例外を投げる() {
            assertThatThrownBy(() -> obj.parse(null)).isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        void パースする前にコンテキストを取得しようとすると例外を投げる() {
            assertThatThrownBy(() -> obj.getContext()).isInstanceOf(IllegalStateException.class);
        }
    }
}