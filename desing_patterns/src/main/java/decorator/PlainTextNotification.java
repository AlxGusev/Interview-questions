package decorator;

public class PlainTextNotification implements Notification{
    private final String message;

    public PlainTextNotification(String message) {
        this.message = message;
    }

    @Override
    public String getContent() {
        return message;
    }
}
