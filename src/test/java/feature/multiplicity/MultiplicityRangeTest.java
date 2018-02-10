package feature.multiplicity;

import feature.value.expression.OneIdentifier;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class MultiplicityRangeTest {

    MultiplicityRange obj;

    @Nested
    class 上限のみの場合 {

        @Test
        void 式を設定すると式を返す() {
            String expected = "1";

            obj = new MultiplicityRange(new Bounder(new OneIdentifier(1)));
            String actual = obj.toString();

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void 文字列を設定すると文字列を返す() {
            String expected = "upperText";

            obj = new MultiplicityRange(new Bounder("upperText"));
            String actual = obj.toString();

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void nullを設定すると例外を投げる() {
            assertThatThrownBy(() -> obj = new MultiplicityRange(null)).isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        void 上限のみかどうかの真偽値として真を返す() {

            obj = new MultiplicityRange(new Bounder("isUpperOnly"));
            boolean actual = obj.isUpperOnly();

            assertThat(actual).isTrue();
        }
    }

    @Nested
    class 下限と上限を含む場合 {
        final Bounder[] bounders = {new Bounder(new OneIdentifier(0)), new Bounder(new OneIdentifier(1))};
        final String[] stringBounders = {"lowerCount", "upperCount"};
        final List<List<Integer>> bounderIndexes = Arrays.asList(
                Arrays.asList(0, 0),
                Arrays.asList(0, 1),
                Arrays.asList(1, 0),
                Arrays.asList(1, 1)
        );

        @RepeatedTest(4)
        void 式を設定すると式を返す(RepetitionInfo info) {
            int index = info.getCurrentRepetition() - 1;
            Bounder bounders[] = new Bounder[2];
            String strings[] = new String[2];
            for (int i = 0; i < bounderIndexes.get(index).size(); i++) {
                if (bounderIndexes.get(index).get(i) == 0) {
                    bounders[i] = this.bounders[i];
                    strings[i] = this.bounders[i].toString();
                } else {
                    bounders[i] = new Bounder(stringBounders[i]);
                    strings[i] = stringBounders[i];
                }
            }
            String expected = strings[0] + ".." + strings[1];

            obj = new MultiplicityRange(bounders[0], bounders[1]);
            String actual = obj.toString();

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void 下限にnullを設定しても例外は投げない() {
            obj = new MultiplicityRange(null, new Bounder(new OneIdentifier("isThrownException")));
        }

        @Test
        void 上限にnullを設定すると例外を投げる() {
            assertThatThrownBy(() -> obj = new MultiplicityRange(new Bounder(new OneIdentifier("ThisIsThrownExceptionBecauseOfInputting")),null))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        void 下限がnullでない場合は上限のみかどうかの真偽値として偽を返す() {

            obj = new MultiplicityRange(new Bounder("isNot"), new Bounder("upperOnly"));
            boolean actual = obj.isUpperOnly();

            assertThat(actual).isFalse();
        }

        @Test
        void 下限がnullの場合は上限のみかどうかの真偽値として真を返す() {

            obj = new MultiplicityRange(null, new Bounder("isUpperOnly"));
            boolean actual = obj.isUpperOnly();

            assertThat(actual).isTrue();
        }
    }
}