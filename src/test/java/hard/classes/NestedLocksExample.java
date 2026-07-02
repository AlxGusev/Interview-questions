package hard.classes;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class NestedLocksExample {
    private static final Lock lock1 = new ReentrantLock();
    private static final Lock lock2 = new ReentrantLock();

    public void method1() {
        lock1.lock();
        try {
            System.out.println("Lock 1 acquired in method1");
            method2();
        } finally {
            lock1.unlock();
        }
    }

    public void method2() {
        lock2.lock();
        try {
            System.out.println("Lock 2 acquired in method2");
        } finally {
            lock2.unlock();
        }
    }
}
