package hard.tests;

import hard.classes.BeanA;
import hard.config.ProxyConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = ProxyConfig.class)
@SpringBootTest
public class ProxyTests {

    @Autowired
    private BeanA beanA;
    @Autowired
    private BeanA beanB;

    @Test
    public void circularDependencyTest() {
        assert beanA != null;
        assert beanB != null;
    }
}
