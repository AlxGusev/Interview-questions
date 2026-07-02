package hard.tests;

import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class ConcurrencyTests {

    @Test
    public void parallelStreamBasic() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        numbers.parallelStream()
                .filter(n -> n % 2 == 0)
                .map(n -> n * n * n)
                .forEach(System.out::println);
    }

    @Test
    public void parallelStreamWithPredefinedPool() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        ForkJoinPool predefinedPool = new ForkJoinPool(4);
        predefinedPool.submit(() ->
                numbers.parallelStream()
                        .filter(n -> n % 2 == 0)
                        .map(n -> n * n * n)
                        .forEach(System.out::println)
        ).join();
        predefinedPool.shutdown();
    }

    @Test
    public void parallelStreamUndefinedBehaviour() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        numbers.parallelStream()
                .filter(n -> n % 2 == 0)
                .map(n -> new RestTemplate().getForObject("https://httpbin.org/get", String.class))
                .forEach(System.out::println);
    }
}
