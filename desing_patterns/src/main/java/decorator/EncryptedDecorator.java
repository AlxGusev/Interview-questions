package decorator;

import java.util.Base64;

public class EncryptedDecorator extends NotificationDecorator {
    public EncryptedDecorator(Notification wrapped) {
        super(wrapped);
    }

    @Override
    public String getContent() {
        return Base64.getEncoder().encodeToString(wrapped.getContent().getBytes());
    }
}
