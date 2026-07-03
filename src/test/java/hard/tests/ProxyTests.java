package hard.tests;

import hard.classes.BeanA;
import hard.classes.ComplexTransactionalService;
import hard.config.ProxyConfig;
import medium.config.TransactionalTestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = {
        ProxyConfig.class,
        TransactionalTestConfig.class})
@SpringBootTest
public class ProxyTests {

    @Autowired
    private BeanA beanA;
    @Autowired
    private BeanA beanB;
    @Autowired
    private ComplexTransactionalService complexTransactionalService;

    @Test
    public void circularDependencyTest() {
        assert beanA != null;
        assert beanB != null;
    }

    @Test
    public void wrappersCombinationTest() throws InterruptedException {
        complexTransactionalService.executeInTransactionMakeAsyncUnderHood();
        complexTransactionalService.asyncMethod();
        complexTransactionalService.regularTransaction();
    }
}
