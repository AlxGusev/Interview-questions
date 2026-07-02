package hard.classes;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class DeadLockExample {
    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();

    private static final CyclicBarrier barrier1 = new CyclicBarrier(2);
    private static final CyclicBarrier barrier2 = new CyclicBarrier(2);

    public void synchronizedDeadLock() throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            synchronized (lock1) {
                System.out.println("Thread 1 acquired lock1");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ignored) {}
                synchronized (lock2) {
                    System.out.println("Thread 1 acquired lock2");
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            synchronized (lock2) {
                System.out.println("Thread 2 acquired lock2");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ignored) {}
                synchronized (lock1) {
                    System.out.println("Thread 2 acquired lock1");
                }
            }
        });

        System.out.println("Starting deadlock example...");
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("Deadlock example completed.");
    }


    public void cyclicBarrierDeadLock() throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            try {
                barrier1.await();
                synchronized (barrier2) {
                    System.out.println("Thread 1 acquired barrier2");
                    barrier2.await();
                }
            } catch (InterruptedException | BrokenBarrierException ignored) {}
        });

        Thread thread2 = new Thread(() -> {
            try {
                barrier2.await();
                synchronized (barrier1) {
                    System.out.println("Thread 2 acquired barrier1");
                    barrier1.await();
                }
            } catch (InterruptedException | BrokenBarrierException ignored) {}
        });

        System.out.println("Starting cyclic barrier deadlock example...");
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("Cyclic barrier deadlock example completed.");
    }
}
