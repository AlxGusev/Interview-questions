package observer;

public interface PriceAlertObserver {
    void onPriceChanged(String symbol, double oldPrice, double newPrice);
}
