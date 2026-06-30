package easy;

import org.junit.jupiter.api.Test;

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
}
