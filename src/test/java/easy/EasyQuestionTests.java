package easy;

import org.junit.jupiter.api.Test;
import testclasses.PoorHashDistribution;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class EasyQuestionTests {

    @Test
    public void primitiveByValue() {
        int first_primitive = 1;
        int second_primitive = 1;

        // compare by ==
        assertSame(first_primitive, second_primitive);
        // compare by equals
        assertEquals(first_primitive, second_primitive);
    }

    @Test
    public void objectByValue() {
        Integer first_object = 1;
        Integer second_object = 1;

        assertSame(first_object, second_object);
        assertEquals(first_object, second_object);

        first_object = 128;
        second_object = 128;

        assertNotSame(first_object, second_object);
        assertEquals(first_object, second_object);

        assertSame(false, Boolean.FALSE);
        assertEquals(false, Boolean.FALSE);
    }

    @Test
    public void hashMapEqualsAndHashCode() {
        // regular map
        Map<Long, Long> regularMap = new HashMap<>();
        regularMap.put(1L, 1L);
        assertEquals(1L, regularMap.get(1L));

        // map with key is mutable object
        Map<Map<Long, Long>, Long> mapToLongMap = new HashMap<>();
        mapToLongMap.put(regularMap, 2L);

        // create a new object to get the value = works
        Map<Long, Long> mapKey = new HashMap<>();
        mapKey.put(1L, 1L);
        assertEquals(2L, mapToLongMap.get(mapKey));

        // mutate regular map and try to get the value = doesn't work
        regularMap.put(2L, 1L);
        assertNotEquals(2L, mapToLongMap.get(mapKey));

        // another attempt with the same object key = doesn't work
        assertNotEquals(2L, mapToLongMap.get(mapKey));

        // another attempt with modified object key = doesn't work
        mapKey.put(2L, 1L);
        assertNotEquals(2L, mapToLongMap.get(mapKey));
    }

    @Test
    public void poorHashMapDistribution() {
        Map<PoorHashDistribution, Integer> map = new HashMap<>();
        for (int i = 1; i < 8; i++) {
            map.put(new PoorHashDistribution(String.valueOf(i)), i);
        }
        Integer value = map.get(new PoorHashDistribution(String.valueOf(7)));

        assertEquals(7, value);
    }
}
