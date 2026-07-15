package collections;

import org.jspecify.annotations.NonNull;

import java.util.*;

public class CustomHashMap<K, V> implements Iterable<CustomHashMap.CustomEntry<K, V>> {

    private ArrayList<LinkedList<CustomEntry<K, V>>> buckets;
    private int bucketsSize = 16;
    private int entries = 0;

    public CustomHashMap() {
        buckets = new ArrayList<>(bucketsSize);
        for (int i = 0; i < bucketsSize; i++) {
            buckets.add(new LinkedList<>());
        }
    }

    @Override
    public @NonNull Iterator<CustomEntry<K, V>> iterator() {
        return new Iterator<>() {
            private int bucketIndex = 0;
            private Iterator<CustomEntry<K, V>> currentBucketIterator;

            @Override
            public boolean hasNext() {
                while (currentBucketIterator == null || !currentBucketIterator.hasNext()) {
                    if (bucketIndex >= buckets.size()) {
                        return false;
                    }
                    currentBucketIterator = buckets.get(bucketIndex).iterator();
                    bucketIndex++;
                }
                return true;
            }

            @Override
            public CustomEntry<K, V> next() {
                return currentBucketIterator.next();
            }
        };
    }


    public static class CustomEntry<K, V> {
        final private K key;
        private V value;
        final private int hash;

        private CustomEntry(K key, V value) {
            this.key = key;
            this.value = value;
            this.hash = key.hashCode();
        }

        private int getHash() {
            return hash;
        }

        private K getKey() {
            return key;
        }

        private V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            CustomEntry<?, ?> that = (CustomEntry<?, ?>) o;
            return Objects.equals(key, that.key);
        }

        @Override
        public int hashCode() {
            return hash;
        }

        @Override
        public String toString() {
            return "CE{" +
                    "key=" + key +
                    ", value=" + value +
                    ", hash=" + hash +
                    '}';
        }
    }

    public void put(K key, V value) {
        if (key == null) {throw new IllegalArgumentException("Key cannot be null");}
        CustomEntry<K, V> entry = new CustomEntry<>(key, value);
        int bucketIndex = getBucketIndex(entry.key);
        buckets.get(bucketIndex).stream()
                .filter(e -> e.getKey().equals(key))
                .findFirst()
                .ifPresentOrElse(
                        e -> e.setValue(value),
                        () -> {
                            buckets.get(bucketIndex).add(entry);
                            entries++;
                        });
    }

    public V get(K key) {
        if (key == null) {return null;}
        return buckets.get(getBucketIndex(key)).stream()
                .filter(e -> e.getKey().equals(key))
                .findFirst()
                .map(CustomEntry::getValue)
                .orElse(null);
    }

    public V remove(K key) {
        if (key == null) {return null;}
        V value = null;
        int bucketIndex = getBucketIndex(key);
        Optional<CustomEntry<K, V>> entry = buckets.get(bucketIndex).stream()
                .filter(e -> e.getKey().equals(key))
                .findFirst();
        if (entry.isPresent()) {
            value = entry.get().getValue();
            buckets.get(bucketIndex).remove(entry.get());
            entries--;
        }
        return value;
    }

    public boolean containsKey(K key) {
        if (key == null) {return false;}
        return buckets.get(getBucketIndex(key)).stream()
                .anyMatch(e -> e.getKey().equals(key));
    }

    public int size() {
        return entries;
    }

    private int getBucketIndex(K key) {
        return (bucketsSize - 1) & key.hashCode();
    }

    @Override
    public String toString() {
        return buckets.toString();}
}
