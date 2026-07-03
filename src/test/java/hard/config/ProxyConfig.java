package hard.config;

import hard.classes.BeanA;
import hard.classes.BeanB;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
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
}
