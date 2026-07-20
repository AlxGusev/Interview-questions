package decorator;

public class DecoratorMain {
    public static void main(String[] args) {

        Notification plain = new PlainTextNotification("Server is down");
        System.out.println(plain.getContent());

        Notification urgent = new UrgentPrefixDecorator(plain);
        System.out.println(urgent.getContent());

        Notification urgentWithTime = new UrgentPrefixDecorator(new TimestampDecorator(plain));
        System.out.println(urgentWithTime.getContent());

        Notification full = new EncryptedDecorator(
                new UrgentPrefixDecorator(
                        new TimestampDecorator(plain)));
        System.out.println(full.getContent());

        Notification differentOrder = new TimestampDecorator(
                new UrgentPrefixDecorator(
                        new EncryptedDecorator(plain)));
        System.out.println(differentOrder.getContent());
    }
}
