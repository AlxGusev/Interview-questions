package hard.classes;

import java.util.concurrent.locks.ReentrantLock;

public class TryLockExample {
    private static final ReentrantLock lock1 = new ReentrantLock();
    private static final ReentrantLock lock2 = new ReentrantLock();

    public void method1() throws InterruptedException {
        while (true) {
            if (lock1.tryLock()) {
                try {
                    System.out.println("Lock 1 acquired in method1");
                    if (lock2.tryLock()) {
                        try {
                            System.out.println("Lock 2 acquired in method1");
                            System.out.println("Both locks acquired successfully");
                            break;
                        } finally {
                            lock2.unlock();
                        }
                    }
                } finally {
                    if (lock1.isHeldByCurrentThread()) {
                        lock1.unlock();
                    }
                }
            }
            lock1.newCondition().await();
        }
    }

    public void method2() throws InterruptedException {
        while (true) {
            if (lock2.tryLock()) {
                try {
                    System.out.println("Lock 2 acquired in method2");
                    if (lock1.tryLock()) {
                        try {
                            System.out.println("Lock 1 acquired in method2");
                            System.out.println("Both locks acquired successfully");
                            break;
                        } finally {
                            lock1.unlock();
                        }
                    }
                } finally {
                    if(lock2.isHeldByCurrentThread()) {
                        lock2.unlock();
                    }
                }
            }
            lock2.newCondition().await();
        }
    }
}
