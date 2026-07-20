package observer;

import java.util.ArrayList;
import java.util.List;

public class StockPrice {
    private final String symbol;
    private double currentPrice;
    private final List<PriceAlertObserver> observers = new ArrayList<>();

    public StockPrice(String symbol, double currentPrice) {
        this.symbol = symbol;
        this.currentPrice = currentPrice;
    }

    public void addObserver(PriceAlertObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(PriceAlertObserver observer) {
        observers.remove(observer);
    }

    public void updatePrice(double newPrice) {
        double oldPrice = this.currentPrice;
        this.currentPrice = newPrice;
        notifyObservers(oldPrice, newPrice);
    }

    private void notifyObservers(double oldPrice, double newPrice) {
        for (PriceAlertObserver observer : observers) {
            try {
                observer.onPriceChanged(symbol, oldPrice, newPrice);
            } catch (Exception e) {
                System.err.println("Observer failed, continuing with others: " + e.getMessage());
            }
        }
    }
}
