package observer;

public class BigJumpAlert implements PriceAlertObserver {

    private static final double THRESHOLD = 0.05;
    @Override
    public void onPriceChanged(String symbol, double oldPrice, double newPrice) {
        double jumpPercent = (newPrice - oldPrice) / oldPrice;
        if (jumpPercent >= THRESHOLD) {
            System.out.printf("[ALERT] %s jumped %.1f%% ($%.2f -> $%.2f)%n",
                    symbol, jumpPercent * 100, oldPrice, newPrice);
        }
    }
}
