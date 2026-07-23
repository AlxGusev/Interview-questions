package reversedLinkedList;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static reversedLinkedList.ReverseLinkedList.ListNode;
import static reversedLinkedList.ReverseLinkedList.reverse;

class ReverseLinkedListTest {

    @ParameterizedTest(name = "{0} => target = {1}, expected = {2}")
    @MethodSource("reverseTestCases")
    void testReverse(String testCaseName, int[] target, int[] expected) {
        ListNode head = target.length == 0 ? null : buildList(target);
        assertArrayEquals(expected, toArray(reverse(head)));
    }

    static List<Arguments> reverseTestCases() {
        return List.of(
                Arguments.of("Multiple Elements", new int[]{1, 2, 3, 4, 5}, new int[]{5, 4, 3, 2, 1}),
                Arguments.of("Two Elements", new int[]{1, 2}, new int[]{2, 1}),
                Arguments.of("Single Element", new int[]{42}, new int[]{42}),
                Arguments.of("Empty List", new int[]{}, new int[]{}),
                Arguments.of("Duplicate Elements", new int[]{1, 1, 1}, new int[]{1, 1, 1}));
    }

    private ListNode buildList(int... values) {
        ListNode head = new ListNode(0);
        ListNode curr = head;
        for (int v : values) {
            curr.next = new ListNode(v);
            curr = curr.next;
        }
        return head.next;
    }

    private int[] toArray(ListNode head) {
        List<Integer> result = new ArrayList<>();
        while (head != null) {
            result.add(head.val);
            head = head.next;
        }
        return result.stream().mapToInt(Integer::intValue).toArray();
    }
}