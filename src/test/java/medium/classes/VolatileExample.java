package medium.classes;

import java.util.concurrent.atomic.AtomicInteger;

public class VolatileExample {
    private int value = 0;

    private volatile int volatileValue = 0;

    private final AtomicInteger atomicInteger = new AtomicInteger(0);

    public void doWorkInParallel() throws InterruptedException {
        int repeatTimes = 1000;
        int numberOfThreads = 100;

        Runnable runnable = () -> {
            for (int i = 0; i < repeatTimes; i++) {
                value++;
            }
        };

        Thread[] workers = new Thread[numberOfThreads];
        for (int i = 0; i < numberOfThreads; i++) {
            workers[i] = new Thread(runnable, "Thread " + i);
            workers[i].start();
        }
        for (int i = 0; i < numberOfThreads; i++) {
            workers[i].join();
        }

        System.out.printf("Value = %d expected = %d%n", value, repeatTimes * numberOfThreads);
        assert value == repeatTimes * numberOfThreads;
    }

    public void doWorkInParallelWithVolatile() throws InterruptedException {
        int repeatTimes = 1000;
        int numberOfThreads = 100;

        Runnable runnable = () -> {
            for (int i = 0; i < repeatTimes; i++) {
                volatileValue++;
            }
        };

        Thread[] workers = new Thread[numberOfThreads];
        for (int i = 0; i < numberOfThreads; i++) {
            workers[i] = new Thread(runnable, "Thread " + i);
            workers[i].start();
        }
        for (int i = 0; i < numberOfThreads; i++) {
            workers[i].join();
        }

        System.out.printf("VolatileValue = %d expected = %d%n", volatileValue, repeatTimes * numberOfThreads);
        assert volatileValue == repeatTimes * numberOfThreads;
    }

    public void doWorkInParallelWithAtomic() throws InterruptedException {
        int repeatTimes = 1000;
        int numberOfThreads = 100;

        Runnable runnable = () -> {
            for (int i = 0; i < repeatTimes; i++) {
                atomicInteger.incrementAndGet();
            }
        };

        Thread[] workers = new Thread[numberOfThreads];
        for (int i = 0; i < numberOfThreads; i++) {
            workers[i] = new Thread(runnable, "Thread " + i);
            workers[i].start();
        }
        for (int i = 0; i < numberOfThreads; i++) {
            workers[i].join();
        }

        System.out.printf("AtomicInteger = %d expected = %d%n", atomicInteger.get(), repeatTimes * numberOfThreads);
        assert atomicInteger.get() == repeatTimes * numberOfThreads;
    }

    public void actualUseOfVolatile() {
        new Thread(() -> {
            value = 1;
            volatileValue = 1;
        }).start();

        int tempValue = value;
        int tempVolatileValue = volatileValue;

        if (tempVolatileValue == 1) {
            assert tempValue == 1;
        } else {
            assert tempValue == 0;
        }
    }
}
