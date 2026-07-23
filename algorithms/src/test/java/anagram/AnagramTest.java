package anagram;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class AnagramTest {

    @Test
    void testIsAnagram() {
        assertTrue(Anagram.isAnagram("word", "drow"));
        assertFalse(Anagram.isAnagram("word", "ward"));
    }

    @ParameterizedTest(name = "{0} => \"{1}\" vs \"{2}\" : {3}")
    @MethodSource("edgeCases")
    void testIsAnagramEdgeCases(String caseName, String s, String t, boolean expected) {
        assertEquals(expected, Anagram.isAnagram(s, t));
    }

    static Stream<Arguments> edgeCases() {
        return Stream.of(
                Arguments.of("word is null", null, "abc", false),
                Arguments.of("anagram is null", "abc", null, false),
                Arguments.of("both params are null", null, null, false),
                Arguments.of("word is empty", "", "abc", false),
                Arguments.of("anagram is empty", "abc", "", false),
                Arguments.of("both params are empty", "", "", true),
                Arguments.of("different length", "word", "words", false)
        );
    }
}