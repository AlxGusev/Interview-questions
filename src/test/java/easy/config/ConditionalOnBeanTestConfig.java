package easy.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import testclasses.PoorHashDistribution;

public class ConditionalOnBeanTestConfig {

    @ConditionalOnProperty(value = "custom.value", havingValue = "true")
    @Bean
    public PoorHashDistribution poorHashDistribution() {
        return new PoorHashDistribution("Condition 1");
    }

    @ConditionalOnMissingBean(value = PoorHashDistribution.class)
    @Bean
    public PoorHashDistribution anotherPoorHashDistribution() {
        return new PoorHashDistribution("Condition 2");
    }
}
