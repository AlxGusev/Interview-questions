package medium.services;

import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

public class TestTransactionalService {
    private final String beanName;
    private final TransactionTemplate transactionTemplate;

    public TestTransactionalService(String beanName, TransactionTemplate transactionTemplate) {
        this.beanName = beanName;
        this.transactionTemplate = transactionTemplate;
    }

    /**
     * https://www.baeldung.com/transaction-configuration-with-jpa-and-spring
     * https://www.marcobehler.com/guides/spring-transaction-management-transactional-in-depth
     * TransactionAspectSupport for more details
     * https://www.baeldung.com/spring-programmatic-transaction-management
     */
    @Transactional
    public void createInTransaction() {
        int i = 1;
        int y = 2;
        privateTransaction();
        System.out.println("i + y = " + i + y);
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            public void doInTransactionWithoutResult(TransactionStatus status) {
                privateTransaction();
            }
        });
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    private void privateTransaction() {
        int privateI = 1;
        int privateJ = 2;
        System.out.println("privateI + privateJ = " + privateI + privateJ);
    }

    @Transactional
    public void longTransaction() throws InterruptedException {
        int longI = 1;
        int longJ = 2;
        Thread.sleep(10000);
        System.out.println("longI + longJ = " + longI + longJ);
    }
}
