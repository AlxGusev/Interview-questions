package decorator;

import java.time.LocalTime;

public class TimestampDecorator extends NotificationDecorator {
    public TimestampDecorator(Notification wrapped) {
        super(wrapped);
    }

    @Override
    public String getContent() {
        String timestamp = LocalTime.now().toString();
        return "[" + timestamp + "] " + wrapped.getContent();
    }
}
