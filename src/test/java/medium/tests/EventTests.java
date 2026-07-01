package medium.tests;

import medium.config.EventsTestConfig;
import medium.services.ApplicationStartedEventListener;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = EventsTestConfig.class)
@SpringBootTest
public class EventTests {
    @Autowired
    private ApplicationStartedEventListener eventListener;

    @Test
    public void shouldShowEventInfo() {
        Assertions.assertNotNull(eventListener);
        System.out.println("Event listener is not null");
    }
}
