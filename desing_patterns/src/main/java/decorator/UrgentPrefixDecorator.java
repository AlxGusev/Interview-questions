package decorator;

public class UrgentPrefixDecorator extends NotificationDecorator {
    public UrgentPrefixDecorator(Notification wrapped) {
        super(wrapped);
    }

    @Override
    public String getContent() {
        return "URGENT: " + wrapped.getContent();
    }
}
