package hard.tests;

import hard.classes.DeadLockExample;
import hard.classes.NestedLocksExample;
import hard.classes.TryLockExample;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

public class DeadLockTest {

    @Test
    public void deadLockTest() throws InterruptedException {
        new DeadLockExample().synchronizedDeadLock();
        // to collect thread dumps
        // jps -l
        // jstack -l <pid>
    }

    @Timeout(5)
    @Test
    public void cyclicBarrier() throws InterruptedException {
        new DeadLockExample().cyclicBarrierDeadLock();
    }

    @Test
    public void correctNestedLocks() {
        NestedLocksExample example = new NestedLocksExample();
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                example.method1();
                example.method2();
            }).start();
        }
        System.out.println("There is no deadlock");
    }

    @Test
    public void nestedLocksWithTryLock() {
        TryLockExample tryLockExample = new TryLockExample();
        new Thread(() -> {
            try {
                tryLockExample.method1();
            } catch (InterruptedException ignored) {}
        }).start();

        new Thread(() -> {
            try {
                tryLockExample.method2();
            } catch (InterruptedException ignored) {}
        }).start();

        System.out.println("Deadlock resolved");
    }
}
