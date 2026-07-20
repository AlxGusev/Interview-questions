package observer;

public class BigDropAlert implements PriceAlertObserver{

    private static final double THRESHOLD = 0.05;
    @Override
    public void onPriceChanged(String symbol, double oldPrice, double newPrice) {
        double dropPercent = (oldPrice - newPrice) / oldPrice;
        if (dropPercent >= THRESHOLD) {
            System.out.printf("[ALERT] %s dropped %.1f%% ($%.2f -> $%.2f)%n",
                    symbol, dropPercent * 100, oldPrice, newPrice);
        }
    }
}
