package medium.config;

import medium.services.TestTransactionalService;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.TransactionTemplate;
import testclasses.PoorHashDistribution;

import static org.mockito.Mockito.mock;

@EnableTransactionManagement
public class TransactionalTestConfig {
    public PoorHashDistribution poorHashDistribution() {
        return new PoorHashDistribution("1");
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return mock(PlatformTransactionManager.class);
    }

    @Bean
    public TransactionTemplate transactionTemplate(PlatformTransactionManager transactionManager) {
        return new TransactionTemplate(transactionManager);
    }

    @Bean
    public TestTransactionalService testTransactionalService(TransactionTemplate transactionTemplate) {
        return new TestTransactionalService("beanNane", transactionTemplate);
    }
}
