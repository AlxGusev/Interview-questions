package strategy;

public class SmsNotificationSender implements NotificationSender {

    private final String phoneNumber;

    public SmsNotificationSender(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void send(String message) {
        System.out.println("SMS sent: " + message);
    }
}
