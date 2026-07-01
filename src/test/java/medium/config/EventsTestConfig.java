package medium.config;

import medium.services.ApplicationStartedEventListener;
import org.springframework.context.annotation.Bean;

public class EventsTestConfig {
    @Bean
    public ApplicationStartedEventListener applicationStartedEvent() {
        return new ApplicationStartedEventListener();
    }
}
