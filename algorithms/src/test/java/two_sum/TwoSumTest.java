package two_sum;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.params.provider.Arguments.*;

class TwoSumTest {

    @ParameterizedTest(name = "{0}: source = {1}, target = {2}, result = {3}")
    @MethodSource("twoSumTestCases")
    void twoSum(String testName, int[] nums, int target, int[] expected) {
        assertArrayEquals(expected, TwoSum.twoSum(nums, target));
    }

    static Stream<Arguments> twoSumTestCases() {
        return Stream.of(
                of("should find matching pair", new int[]{2, 5, 7, 15}, 9, new int[]{0, 2}),
                of("should return empty array when no solution exists", new int[]{1, 2, 3}, 7, new int[]{}),
                of("should return empty array for null input", null, 0, new int[]{}),
                of("should return empty array for empty input", new int[]{}, 5, new int[]{}),
                of("should return empty array for single-element array", new int[]{5}, 5, new int[]{})
        );
    }
}