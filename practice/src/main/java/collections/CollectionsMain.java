package collections;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CollectionsMain {
    public static void main(String[] args) {
        CustomHashMap<Integer, String> map = new CustomHashMap<>();
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");
        map.put(1, "one+one");
        for (CustomHashMap.CustomEntry<Integer, String> entry : map) {
            System.out.println(entry);
        }

        List<Integer> list = List.of(1, 1, 2, 2, 3, 4, 4, 5);
        List<Integer> distinctNumbers = list.stream()
                .distinct()
                .toList();

        LinkedHashSet<Integer> set = list.stream()
                .collect(Collectors.toCollection(LinkedHashSet::new));

        LinkedHashSet<Integer> integers = new LinkedHashSet<>(list);

        System.out.println(distinctNumbers);
        System.out.println(set);
        System.out.println(integers);

        String str = "java spring java";
        HashMap<String, Integer> wordFrequencyMap = new HashMap<>();
        for (String s : str.split("\\s+")) {
            wordFrequencyMap.put(s, wordFrequencyMap.getOrDefault(s, 0) + 1);
            wordFrequencyMap.merge(s, 1, Integer::sum);
            wordFrequencyMap.compute(s, (k, v) -> v == null ? 1 : v + 1);
            wordFrequencyMap.putIfAbsent(s, 0);
            wordFrequencyMap.put(s, wordFrequencyMap.get(s) + 1);
        }
        System.out.println(wordFrequencyMap);

        String s = "swiss";
        Map<Character, Long> lettersFrequency = s.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        s.chars()
                .mapToObj(c -> (char) c)
                .filter(c -> lettersFrequency.get(c) == 1)
                .findFirst()
                .ifPresent(System.out::println);
    }
}
