package hard.classes;

import medium.services.TestTransactionalService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;

public class ComplexTransactionalService {

    private final TestTransactionalService testTransactionalService;

    public ComplexTransactionalService(TestTransactionalService testTransactionalService) {
        this.testTransactionalService = testTransactionalService;
    }

    @Transactional
    public void executeInTransactionMakeAsyncUnderHood() {
        testTransactionalService.asyncTransaction();
    }

    @Async
    @Transactional
    public void asyncMethod() throws InterruptedException {
        testTransactionalService.asyncTransaction();
        testTransactionalService.longTransaction();
        System.out.println("Async method completed");
    }

    @Transactional
    public void regularTransaction() {
        testTransactionalService.asyncWithTransaction();
    }
}
