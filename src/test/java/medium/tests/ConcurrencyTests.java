package medium.tests;

import medium.classes.VolatileExample;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConcurrencyTests {

    @Execution(ExecutionMode.CONCURRENT)
    @RepeatedTest(100)
    public void volatileTest() throws InterruptedException {
        VolatileExample volatileExample = new VolatileExample();
        volatileExample.doWorkInParallel();
    }

    @Execution(ExecutionMode.CONCURRENT)
    @RepeatedTest(100)
    public void volatileTestWithVolatile() throws InterruptedException {
        VolatileExample volatileExample = new VolatileExample();
        volatileExample.doWorkInParallelWithVolatile();
    }

    @Execution(ExecutionMode.CONCURRENT)
    @RepeatedTest(100)
    public void volatileTestWithAtomic() throws InterruptedException {
        VolatileExample volatileExample = new VolatileExample();
        volatileExample.doWorkInParallelWithAtomic();
    }

    @Execution(ExecutionMode.CONCURRENT)
    @RepeatedTest(100)
    public void actualUseOfVolatileTest() {
        VolatileExample volatileExample = new VolatileExample();
        volatileExample.actualUseOfVolatile();
    }

    @Execution(ExecutionMode.CONCURRENT)
    @RepeatedTest(100)
    public void actualUseOfVolatileTestWithVolatile() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                System.out.println("Thread 1");
            }
        }.start();

        new Thread(() -> System.out.println("Thread 2"));

        ExecutorService executor = Executors.newFixedThreadPool(10);
        executor.execute(() -> System.out.println("Thread 3"));

        CompletableFuture.runAsync(() -> System.out.println("Thread 4"));
    }
}
