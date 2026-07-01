package medium.tests;

import easy.config.BeanLifecycleBean;
import medium.config.SimpleTestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import testclasses.PoorHashDistribution;

import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration(classes = SimpleTestConfig.class)
@SpringBootTest
public class BeanConfigTests {
    @Autowired
    private BeanFactory beanFactory;
    @Autowired
    private BeanLifecycleBean beanLifecycleBeanPrototype;
    @Autowired
    private PoorHashDistribution poorHashDistributionSingleton;

    @Test
    public void shouldInjectValue() {
        assertNotNull(beanLifecycleBeanPrototype);
    }

    @Test
    public void shouldGetDifferentBean() {
        BeanLifecycleBean bean = beanFactory.getBean(BeanLifecycleBean.class);
        assertNotNull(beanLifecycleBeanPrototype);
        assertNotEquals(bean, beanLifecycleBeanPrototype);
    }

    @Test
    public void shouldGetSameBean() {
        PoorHashDistribution bean = beanFactory.getBean(PoorHashDistribution.class);
        assertNotNull(bean);
        assertEquals(bean, poorHashDistributionSingleton);
    }
}
