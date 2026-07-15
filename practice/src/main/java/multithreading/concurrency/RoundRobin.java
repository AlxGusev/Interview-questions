package multithreading.concurrency;

public class RoundRobin {

    private int counter = 1;
    private final int max;
    private final int threadCount;

    public RoundRobin(int max, int threadCount) {
        this.max = max;
        this.threadCount = threadCount;
    }

    public synchronized void print(String letter, int threadId) {
        while (counter <= max) {
            try {
                if ((counter - 1) % threadCount == threadId) {
                    System.out.print(letter);
                    counter++;
                    notifyAll();
                } else {
                    wait();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
        }
    }
}
