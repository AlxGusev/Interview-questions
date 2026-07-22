package binary_search;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static binary_search.BinarySearch.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BinarySearchTest {

    private static final int[] SORTED_ARRAY = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

    @ParameterizedTest(name = "target={0} -> expectedIndex={1}")
    @CsvSource({
            "5, 4",
            "1, 0",
            "10, 9",
            "11, -1",
            "0, -1"
    })
    void testBinarySearchWithVariousInputs(int target, int expectedIndex) {
        assertEquals(expectedIndex, binarySearch(SORTED_ARRAY, target));
    }

    @Test
    void testBinarySearchWithEmptyArray() {
        assertEquals(-1, BinarySearch.binarySearch(new int[]{}, 5));
    }

    @Test
    void testBinarySearchWithNullArray() {
        assertEquals(-1, BinarySearch.binarySearch(null, 5));
    }

    @Test
    void testBinarySearchWithSingleElementFound() {
        assertEquals(0, BinarySearch.binarySearch(new int[]{5}, 5));
    }

    @Test
    void testBinarySearchWithSingleElementNotFound() {
        assertEquals(-1, BinarySearch.binarySearch(new int[]{5}, 6));
    }
}