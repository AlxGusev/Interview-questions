package parentheses;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;

public class ValidParentheses {

    public static boolean isValid(String s) {
        HashMap<Character, Character> parenthesesPairs = new HashMap<>();
        parenthesesPairs.put(')', '(');
        parenthesesPairs.put('}', '{');
        parenthesesPairs.put(']', '[');
        Deque<Character> charDeque = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (parenthesesPairs.containsKey(c)) {
                if (charDeque.isEmpty() || !parenthesesPairs.get(c).equals(charDeque.pop())) {
                    return false;
                }
            } else {
                charDeque.push(c);
            }
        }
        return charDeque.isEmpty();
    }
}
