package multithreading.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public class CustomThreadPool {

    private final List<Thread> workers;
    private final BlockingDeque<Runnable> workQueue;
    private volatile boolean isShutdown = false;
    public CustomThreadPool(int numberOfThreads) {
        this.workers = new ArrayList<>(numberOfThreads);
        this.workQueue = new LinkedBlockingDeque<>();
        for (int i = 0; i < numberOfThreads; i++) {
            Thread worker = new Thread(this::workerLoop);
            workers.add(worker);
            worker.start();
        }
    }

    private void workerLoop() {
        while (!isShutdown || !workQueue.isEmpty()) {
            try {
                Runnable task = workQueue.poll(100, TimeUnit.MILLISECONDS);
                if (task != null) {
                    task.run();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void submit(Runnable task) {
        if (isShutdown) {
            throw new IllegalStateException("Pool is shut down, cannot accept new tasks");
        }
        workQueue.add(task);
    }

    public void shutdown() {
        isShutdown = true;
    }

    public void awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        long deadline = System.currentTimeMillis() + unit.toMillis(timeout);
        for (Thread worker : workers) {
            long remaining = deadline - System.currentTimeMillis();
            if (remaining <= 0) {
                return;
            }
            worker.join(remaining);
        }
    }
}
