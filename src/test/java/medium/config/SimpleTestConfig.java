package medium.config;

import easy.config.BeanLifecycleBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import testclasses.PoorHashDistribution;

public class SimpleTestConfig {

    @Bean
    @Scope(scopeName = "prototype")
    public BeanLifecycleBean beanLifecycleBeanPrototype() {
        return new BeanLifecycleBean();
    }

    @Bean
    @Scope(scopeName = "singleton")
    public PoorHashDistribution poorHashDistributionSingleton() {
        return new PoorHashDistribution("singleton");
    }
}
