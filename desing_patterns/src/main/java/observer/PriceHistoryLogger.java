package observer;

public class PriceHistoryLogger implements PriceAlertObserver{
    @Override
    public void onPriceChanged(String symbol, double oldPrice, double newPrice) {
        System.out.printf("[LOG] %s: $%.2f -> $%.2f%n", symbol, oldPrice, newPrice);
    }
}
