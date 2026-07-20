package strategy;

public class PushNotificationSender implements NotificationSender {

    private final String apiKey;

    public PushNotificationSender(String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public void send(String message) {
        System.out.println("Push notification sent: " + message);
    }
}
