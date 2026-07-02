package hard.tests;

import hard.classes.*;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.ForkJoinPool;

import static org.junit.jupiter.api.Assertions.fail;

public class ConcurrencyTests {

    @Test
    public void parallelStreamBasic() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        numbers.parallelStream()
                .filter(n -> n % 2 == 0)
                .map(n -> n * n * n)
                .forEach(System.out::println);
    }

    @Test
    public void parallelStreamWithPredefinedPool() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        ForkJoinPool predefinedPool = new ForkJoinPool(4);
        predefinedPool.submit(() ->
                numbers.parallelStream()
                        .filter(n -> n % 2 == 0)
                        .map(n -> n * n * n)
                        .forEach(System.out::println)
        ).join();
        predefinedPool.shutdown();
    }

    @Test
    public void parallelStreamUndefinedBehaviour() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        numbers.parallelStream()
                .filter(n -> n % 2 == 0)
                .map(n -> new RestTemplate().getForObject("https://httpbin.org/get", String.class))
                .forEach(System.out::println);
    }

    @Test
    public void synchronizedAnsLocksTest() {
        SynchronizedExample synchronizedExample = new SynchronizedExample();
        ReentrantLockExample reentrantLockExample = new ReentrantLockExample();
        ReadWriteLockExample readWriteLockExample = new ReadWriteLockExample();
        AtomicExample atomicExample = new AtomicExample();

        ForkJoinPool pool = new ForkJoinPool(4);

        for (int i = 0; i < 10; i++) {
            pool.submit(() -> {
                synchronizedExample.increment();
                System.out.println("synchronized count = " + synchronizedExample.getCount());
                reentrantLockExample.increment();
                System.out.println("reentrantLock count = " + reentrantLockExample.getCount());
                readWriteLockExample.increment();
                System.out.println("readWriteLock count = " + readWriteLockExample.getCount());
                atomicExample.increment();
                System.out.println("atomic count = " + atomicExample.getCount());
            }).join();
        }
        pool.shutdown();
    }

    @RepeatedTest(100)
    @Execution(ExecutionMode.CONCURRENT)
    public void happensBeforeForFinalTest() throws InterruptedException {
        HappensBeforeForFinalExample[] holder = new HappensBeforeForFinalExample[1];

        Thread writer = new Thread(() ->
                holder[0] = new HappensBeforeForFinalExample(1, 1));

        Thread reader = new Thread(() -> {
            HappensBeforeForFinalExample obj = holder[0];
            if (obj != null) {
                if (obj.getFinalVar() != 1) {
                    fail("final field broken");
                }
                if (obj.getNonFinalVar() != 1) {
                    System.out.println("Observed stale non-final value: "
                            + obj.getNonFinalVar());
                }
            }
        });

        writer.start();
        reader.start();

        writer.join();
        reader.join();
    }
}
