package multithreading.concurrency;

import java.math.BigInteger;
import java.time.LocalTime;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class ParallelCompletableFutureCallsMain {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Supplier<BigInteger> serviceA = () -> {
            try {
                Thread.sleep(1000);
                return BigInteger.valueOf(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
        };

        Supplier<Boolean> serviceB = () -> {
            try {
                Thread.sleep(750);
                boolean b = new Random().nextBoolean();
                if (!b) {
                    throw new RuntimeException("Service B is unavailable");
                }
                return true;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
        };

        Supplier<LocalTime> serviceC = () -> {
            try {
                Thread.sleep(1500);
                return LocalTime.now().plusHours(new Random().nextInt(24));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
        };

        long startTime = System.currentTimeMillis();
        CompletableFuture<BigInteger> futureA = CompletableFuture.supplyAsync(serviceA);
        CompletableFuture<Boolean> futureB = CompletableFuture.supplyAsync(serviceB)
                .exceptionally(ex -> {
                    System.out.println("serviceB failed, using fallback: " + ex.getMessage());
                    return false;
                });
        CompletableFuture<LocalTime> futureC = CompletableFuture.supplyAsync(serviceC)
                .orTimeout(1, TimeUnit.SECONDS).handle((result, ex) -> {
                    if (ex != null) {
                        System.out.println("serviceC failed, using fallback: " + ex.getMessage());
                        return LocalTime.now().plusHours(new Random().nextInt(24));
                    }
                    return result;
                });

        CompletableFuture.allOf(futureA, futureB, futureC).join();

        CompletableFuture<String> futureD = futureA
                .thenCombine(futureB, (a, b) -> "Price: " + a.toString() + "; Available: " + b)
                .thenCombine(futureC, (a, b) -> a + "; Delivery Time: " + b.toString());
        System.out.println(futureD.get());
        System.out.println("Total time: " + (System.currentTimeMillis() - startTime) + "ms");
    }
}
