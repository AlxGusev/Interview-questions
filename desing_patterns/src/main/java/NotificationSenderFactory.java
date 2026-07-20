import strategy.EmailNotificationSender;
import strategy.NotificationSender;
import strategy.PushNotificationSender;
import strategy.SmsNotificationSender;

public class NotificationSenderFactory {

    public static NotificationSender getEmailNotificationSender(String email) {
        return new EmailNotificationSender(email);
    }

    public static NotificationSender getSmsNotificationSender(String phoneNumber) {
        return new SmsNotificationSender(phoneNumber);
    }

    public static NotificationSender getPushNotificationSender(String deviceToken) {
        return new PushNotificationSender(deviceToken);
    }

    public static void main(String[] args) {
        NotificationSender emailSender = getEmailNotificationSender("");
        emailSender.send("Hello, this is an email.");

        NotificationSender smsSender = getSmsNotificationSender("");
        smsSender.send("Hello, this is an SMS.");

        NotificationSender pushSender = getPushNotificationSender("");
        pushSender.send("Hello, this is a push notification.");
    }
}
