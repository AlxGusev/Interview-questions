package collections;

public class CustomHashSet<E> {

    private CustomHashMap<E, Object> map;
    private static final Object PRESENT_VALUE = new Object();

    public CustomHashSet() {
        map = new CustomHashMap<>();
    }

    boolean add(E element) {
        map.put(element, PRESENT_VALUE);
        return true;
    }

    boolean remove(E element) {
        return map.remove(element) == PRESENT_VALUE;
    }
    boolean contains(E element) {
        return map.containsKey(element);
    }
    int size() {
        return map.size();
    }
}
