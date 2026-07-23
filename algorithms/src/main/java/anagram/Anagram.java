package anagram;

import java.util.Arrays;
import java.util.HashMap;

public class Anagram {

    public static boolean isAnagram(String word, String anagram) {
        if (word == null || anagram == null || word.length() != anagram.length()) return false;
        HashMap<Character, Integer> charMap = new HashMap<>();
        for (int i = 0; i < word.length(); i++) {
            char nextChar = word.charAt(i);
            charMap.merge(nextChar, 1, Integer::sum);
            nextChar = anagram.charAt(i);
            charMap.merge(nextChar, -1, Integer::sum);
        }
        return charMap.values().stream().allMatch(count -> count == 0);
    }

    public static boolean isAnagramLogN(String word, String anagram) {
        if (word == null || anagram == null || word.length() != anagram.length()) return false;
        int[] w = word.chars().toArray();
        int[] a = anagram.chars().toArray();
        Arrays.sort(w);
        Arrays.sort(a);
        return Arrays.equals(w, a);
    }
}
