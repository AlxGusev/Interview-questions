package easy.tests;

import easy.config.ConditionalOnBeanTestConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest(classes = {ConditionalOnBeanTestConfig.class})
@Testcontainers
public class TestContainersTests {
    private final static String POSTGRES_IMAGE = "postgres:18.4";

    @Container
    static PostgreSQLContainer container = new PostgreSQLContainer<>(POSTGRES_IMAGE);


    @Test
    public void dockerTestContainerTest() {
        Assertions.assertThat(container.isRunning()).isTrue();
    }
}
