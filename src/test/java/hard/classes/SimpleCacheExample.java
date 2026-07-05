package hard.classes;

import java.util.Map;

public class SimpleCacheExample<K, V> {
    private final Map<K, V> cache;

    public SimpleCacheExample(Map<K, V> cache) {
        this.cache = cache;
    }

    public V get(K key) {
        return cache.get(key);
    }

    public void save(K key, V value) {
        cache.put(key, value);
    }

    public int size() {
        return cache.size();
    }
}
