package hard.tests;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import hard.classes.SimpleCacheExample;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CacheTests {

    @Test
    public void unlimitedCacheTest() {
        /*
        No eviction
        No expiration
        Unlimited growth
        Not thread-safe
         */
        SimpleCacheExample<Integer, String> cache = new SimpleCacheExample<>(new HashMap<>());
        for (int i = 0; i < 10000; i++) {
            cache.save(i, "value " + i);
            cache.get(i);
        }
        assertEquals(10000, cache.size());
    }

    @Test
    public void unlimitedConcurrentCacheTest() {
        // Thread-safe, but still unlimited
        SimpleCacheExample<Integer, String> cache = new SimpleCacheExample<>(new ConcurrentHashMap<>());
        for (int i = 0; i < 10000; i++) {
            cache.save(i, "value " + i);
            cache.get(i);
        }
        assertEquals(10000, cache.size());
    }

    @Test
    public void caffeineCacheTest() throws InterruptedException {
        // Automatic expiration (TTL)
        Cache<Integer, String> cache =
                Caffeine.newBuilder().expireAfterWrite(Duration.ofSeconds(1)).build();
        cache.put(1, "value 1");
        Thread.sleep(1500);
        cache.cleanUp();
        assertNull(cache.getIfPresent(1));
        assertEquals(1, cache.estimatedSize());
    }
}
