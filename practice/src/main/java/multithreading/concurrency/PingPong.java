package multithreading.concurrency;

public class PingPong {
    private int counter = 0;
    private final int max;

    public PingPong(int max) {
        this.max = max;
    }

    public synchronized void printEvenNumbers() {
        while (counter <= max) {
            try {
                if (counter % 2 == 0) {
                    System.out.println("Even number: " + counter++);
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

    public synchronized void printOddNumbers() {
        while (counter <= max) {
            try {
                if (counter % 2 != 0) {
                    System.out.println("Odd number: " + counter++);
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
