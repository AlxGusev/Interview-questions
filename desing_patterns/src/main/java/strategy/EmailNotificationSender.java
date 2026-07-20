package strategy;

public class EmailNotificationSender implements NotificationSender {

    private final String email;

    public EmailNotificationSender(String email) {
        this.email = email;
    }

    @Override
    public void send(String message) {
        System.out.println("Email sent: " + message);
    }
}
