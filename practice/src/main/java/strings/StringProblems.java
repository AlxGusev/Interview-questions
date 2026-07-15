package strings;

import java.util.HashSet;

public class StringProblems {

    public static void main(String[] args) {
        System.out.println(reverseString( "Hello"));
        System.out.println(isPalindrome("racecar"));
        System.out.println(isPalindrome("race1car"));
        System.out.println(stringCompression("aabcccccaaa"));
        System.out.println(removeDuplicates("aabcccccaaa"));
        System.out.println(longestCommonPrefix(new String[]{"flower", "flow", "flight"}));
    }

    public static String reverseString(String str) {
        if (str == null || str.isBlank()) {
            throw new IllegalArgumentException("String cannot be null or empty");
        }
        StringBuilder sb = new StringBuilder();
        for (int i = str.length(); i > 0; i--) {
            sb.append(str.charAt(i - 1));
        }
        return sb.toString();
    }

    public static boolean isPalindrome(String str) {
        if (str == null || str.isBlank()) {return false;}
        for (int i = 0; i < str.length() / 2; i++) {
            if (str.charAt(i) != str.charAt(str.length() - 1 - i)) {
                return false;
            }
        }
        return true;
//        return str.contentEquals(new StringBuilder(str).reverse());
//        return str.equals(reverseString(str));
    }

    public static String stringCompression(String str) {
        if (str == null || str.isBlank()) {return str;}
        StringBuilder sb = new StringBuilder();
        int count = 1;
        for (int i = 0; i < str.length(); i++) {
            if (i + 1 < str.length() && str.charAt(i) == str.charAt(i + 1)) {
                count++;
            } else {
                sb.append(str.charAt(i));
                sb.append(count);
                count = 1;
            }
        }
        return sb.toString();
    }

    public static String removeDuplicates(String str) {
        StringBuilder sb = new StringBuilder();
        HashSet<Character> seen = new HashSet<>();
        for (char c : str.toCharArray()) {
            if (!seen.contains(c)) {
                sb.append(c);
                seen.add(c);
            }
        }
        return sb.toString();
    }

    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        for (int i = 0; i < strs[0].length(); i++) {
            char c = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (i == strs[j].length() || strs[j].charAt(i) != c) {
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0];
    }
}
