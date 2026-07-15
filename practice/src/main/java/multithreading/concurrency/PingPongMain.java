package multithreading.concurrency;

public class PingPongMain {
    public static void main(String[] args) {
        PingPong pingPong = new PingPong(20);

        Runnable evenRunnable = pingPong::printEvenNumbers;
        Runnable oddRunnable = pingPong::printOddNumbers;

        Thread evenThread = new Thread(evenRunnable);
        Thread oddThread = new Thread(oddRunnable);

        evenThread.start();
        oddThread.start();

        try {
            evenThread.join();
            oddThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }
}
