package observer;

public class BuggyObserver implements PriceAlertObserver{
    @Override
    public void onPriceChanged(String symbol, double oldPrice, double newPrice) {
        throw new RuntimeException("Simulated failure in BuggyObserver");
    }
}
