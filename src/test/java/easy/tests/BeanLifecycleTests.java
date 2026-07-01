package easy.tests;

import easy.config.BeanLifecycleBean;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = {BeanLifecycleBean.class})
@SpringBootTest
public class BeanLifecycleTests {

    @Autowired
    private BeanLifecycleBean bean;

    @Test
    public void beanLifecycleTest() {
        Assertions.assertNotNull(bean);
        Assertions.assertEquals("constructed", bean.getName());
    }
}
