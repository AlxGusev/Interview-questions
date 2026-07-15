package multithreading.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class ConcurrencyMain {

    private static final int ITEMS_PER_PRODUCER = 20;
    private static final int PRODUCER_COUNT = 2;
    private static final int CONSUMER_COUNT = 3;

    public static void main(String[] args) throws InterruptedException {

        BoundedBuffer buffer = new BoundedBuffer(5);
        List<Thread> threads = new ArrayList<>();

        for (int p = 0; p < PRODUCER_COUNT; p++) {
            int producerId = p;
            Thread t = new Thread(() -> {
                ThreadLocalRandom random = ThreadLocalRandom.current();
                for (int i = 0; i < ITEMS_PER_PRODUCER; i++) {
                    int item = producerId * 1000 + i;
                    buffer.put(item);
                    System.out.println("Producer " + producerId + " produced: " + item);
                    try {
                        Thread.sleep(random.nextInt(50));
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            });
            threads.add(t);
        }

        for (int c = 0; c < CONSUMER_COUNT; c++) {
            int consumerId = c;
            Thread t = new Thread(() -> {
                while (true) {
                    Integer item = buffer.take();
                    if (item == null) {
                        System.out.println("Consumer " + consumerId + " received poison pill, stopping.");
                        break;
                    }
                    System.out.println("Consumer " + consumerId + " consumed: " + item);
                }
            });
            threads.add(t);
        }

        for (Thread t : threads) {
            t.start();
        }

        for (int p = 0; p < PRODUCER_COUNT; p++) {
            threads.get(p).join();
        }

        for (int c = 0; c < CONSUMER_COUNT; c++) {
            buffer.put(null);
        }

        for (int c = PRODUCER_COUNT; c < threads.size(); c++) {
            threads.get(c).join();
        }

        System.out.println("All producers and consumers finished.");
    }
}
