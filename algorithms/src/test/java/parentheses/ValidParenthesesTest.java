package parentheses;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static parentheses.ValidParentheses.*;

class ValidParenthesesTest {

    @ParameterizedTest(name = "{0}: \"{1}\" = {2}")
    @MethodSource("validParenthesesCases")
    void testIsValidParentheses(String testCaseName, String target, boolean expected) {
        assertEquals(expected, isValid(target));
    }

    static Stream<Arguments> validParenthesesCases() {
        return Stream.of(
                Arguments.of("simple pair", "()", true),
                Arguments.of("multiple types in sequence", "()[]{}", true),
                Arguments.of("nested brackets", "([{}])", true),
                Arguments.of("mismatched types", "(]", false),
                Arguments.of("wrong closing order", "([)]", false),
                Arguments.of("unclosed opening bracket", "(", false),
                Arguments.of("closing bracket on empty stack", ")", false),
                Arguments.of("nested same brackets", "(())", true),
                Arguments.of("even length but unclosed", "((", false),
                Arguments.of("empty string", "", true)
        );
    }
}