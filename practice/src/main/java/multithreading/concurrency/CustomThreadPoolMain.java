package multithreading.concurrency;

public class CustomThreadPoolMain {
    public static void main(String[] args) throws InterruptedException {
        CustomThreadPool threadPool = new CustomThreadPool(3);

        for (int i = 0; i < 10; i++) {
            int taskNumber = i;
            threadPool.submit(() -> {
                System.out.println("Task " + taskNumber + " is running on thread " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                System.out.println("Task " + taskNumber + " is done");
            });
        }
        threadPool.shutdown();
        System.out.println("All tasks completed");
    }
}
