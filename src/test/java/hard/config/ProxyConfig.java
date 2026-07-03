package hard.config;

import hard.classes.BeanA;
import hard.classes.BeanB;
import hard.classes.ComplexTransactionalService;
import medium.services.TestTransactionalService;
import org.springframework.boot.task.ThreadPoolTaskExecutorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
public class ProxyConfig {

    @Bean
    public BeanA beanA(@Lazy BeanB beanB) {
        return new BeanA(beanB);
    }

    @Bean
    public BeanB beanB(@Lazy BeanA beanA) {
        return new BeanB(beanA);
    }

    @Bean
    public ComplexTransactionalService complexTransactionalService(TestTransactionalService testTransactionalService) {
        return new ComplexTransactionalService(testTransactionalService);
    }

    @Bean
    public TaskExecutor taskExecutor() {return new ThreadPoolTaskExecutorBuilder().build();}
}
