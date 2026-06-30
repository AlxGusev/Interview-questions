package easy;

import org.junit.jupiter.api.Test;
import testclasses.PoorHashDistribution;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;

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

    @Test
    public void hashMapEqualsAndHashCode() {
        // regular map
        Map<Long, Long> regularMap = new HashMap<>();
        regularMap.put(1L, 1L);
        assertEquals(1L, regularMap.get(1L));

        // map with key is mutable object
        Map<Map<Long, Long>, Long> mapToLongMap = new HashMap<>();
        mapToLongMap.put(regularMap, 2L);

        // create a new object to get the value = works
        Map<Long, Long> mapKey = new HashMap<>();
        mapKey.put(1L, 1L);
        assertEquals(2L, mapToLongMap.get(mapKey));

        // mutate regular map and try to get the value = doesn't work
        regularMap.put(2L, 1L);
        assertNotEquals(2L, mapToLongMap.get(mapKey));

        // another attempt with the same object key = doesn't work
        assertNotEquals(2L, mapToLongMap.get(mapKey));

        // another attempt with modified object key = doesn't work
        mapKey.put(2L, 1L);
        assertNotEquals(2L, mapToLongMap.get(mapKey));
    }

    @Test
    public void poorHashMapDistribution() {
        Map<PoorHashDistribution, Integer> map = new HashMap<>();
        for (int i = 1; i < 8; i++) {
            map.put(new PoorHashDistribution(String.valueOf(i)), i);
        }
        Integer value = map.get(new PoorHashDistribution(String.valueOf(7)));

        assertEquals(7, value);
    }

    @Test
    public void optionalOf() {
        assertThrows(NullPointerException.class, () -> Optional.of(null));

        assertEquals(Optional.empty(), Optional.of(new PoorHashDistribution(null)).map(PoorHashDistribution::getValue));
        assertThrows(NullPointerException.class, () -> Optional.of(null).orElseGet(() -> "Default"));
    }

    @Test
    public void optionalOfNullable() {
        assertEquals(Optional.empty(), Optional.ofNullable(null));

        assertDoesNotThrow(() -> Optional.ofNullable(null).orElseGet(() -> "Default"));
    }

    @Test
    public void threadLocal() throws InterruptedException {
        Runnable sharedRun = new Runnable() {
            private final ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

            @Override
            public void run() {
                System.out.println(
                        Thread.currentThread().getName() +
                                " Before: " + threadLocal.get());
                threadLocal.set((int) (Math.random() * 1000));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ignored) {
                }
                System.out.println(
                        Thread.currentThread().getName() +
                                "After: " + threadLocal.get());
            }
        };

        Thread firstThread = new Thread(sharedRun, "First Thread");
        Thread secondThread = new Thread(sharedRun, "Second Thread");

        firstThread.start();
        secondThread.start();

        firstThread.join();
        secondThread.join();
    }

    @Test
    public void inheritableThreadLocal() throws InterruptedException {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();

        Thread thread1 = new Thread(() -> {
            System.out.println("*** Thread 1 ***");
            threadLocal.set("Thread 1 - ThreadLocal");
            inheritableThreadLocal.set("Thread 1 - InheritableThreadLocal");

            System.out.println(threadLocal.get());
            System.out.println(inheritableThreadLocal.get());

            Thread childThread = new Thread(() -> {
                System.out.println("*** Child Thread ***");
                System.out.println(threadLocal.get());
                System.out.println(inheritableThreadLocal.get());
            });

            childThread.start();
            try {
                childThread.join();
            } catch (InterruptedException ignored) {
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {
            }
            System.out.println("*** Thread 2 ***");
            System.out.println(threadLocal.get());
            System.out.println(inheritableThreadLocal.get());
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }

    @Test
    public void stream() {
        List<Integer> values = List.of(1, 1, 2, 2, 3, 4, 5);
        assertIterableEquals(
                List.of(2, 4, 6, 8, 10),
                values.stream()
                        .distinct()
                        .map(v -> v * 2)
                        .toList());

        assertEquals(5, values.stream().max(Integer::compareTo).get());
        assertEquals(1, values.stream().min(Integer::compareTo).get());
    }

    @Test
    public void primitiveStreams() {
        assertEquals(10, IntStream.range(1, 5).sum());
        assertIterableEquals(
                List.of(2, 4, 6, 8, 10),
                IntStream.rangeClosed(1, 10)
                        .filter(i -> i % 2 == 0)
                        .boxed()
                        .toList()
        );
    }
}
