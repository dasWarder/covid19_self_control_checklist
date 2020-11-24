package util;

import java.util.*;
import static org.assertj.core.api.Assertions.assertThat;

public class TestMatcher<T> {

    private final String[] fieldsToIgnore;

    public TestMatcher(String... fieldsToIgnore) {
        this.fieldsToIgnore = fieldsToIgnore;
    }

    public static <T> TestMatcher<T> getTestMatcher(String... fieldsToIgnore) {
        return new TestMatcher<T>(fieldsToIgnore);
    }

    public void assertMatch(T actual, T expected) {
        assertThat(actual).usingRecursiveComparison().ignoringFields(fieldsToIgnore).isEqualTo(expected);
    }

    public void assertMatch(Iterable<T> actual, Iterable<T> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields(fieldsToIgnore).isEqualTo(expected);
    }

    public void assertMatch(Iterable<T> actual, T... expected) {
        assertMatch(actual, List.of(expected));
    }

}
