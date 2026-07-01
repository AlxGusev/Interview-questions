package easy.tests;

import easy.config.ConditionalOnBeanTestConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import testclasses.PoorHashDistribution;

@ContextConfiguration(classes = ConditionalOnBeanTestConfig.class)
@SpringBootTest(properties = {"custom.value=false"})
public class ConditionalOnMissingBeanEasyQuestionsTests {

    @Autowired
    private PoorHashDistribution bean;

    @Value("${custom.value}")
    private String value;

    @Test
    public void conditionalOnTest() {
        System.out.println("value: " + value);
        Assertions.assertEquals("Condition 2", bean.value());
    }
}
