package observer;

public class ObserverMain {
    public static void main(String[] args) {
        StockPrice appleStock = new StockPrice("AAPL", 150.00);

        appleStock.addObserver(new BigDropAlert());
        appleStock.addObserver(new PriceHistoryLogger());
        appleStock.addObserver(new BuggyObserver());
        appleStock.addObserver(new BigJumpAlert());

        System.out.println("--- Small change (no alert expected) ---");
        appleStock.updatePrice(148.00);

        System.out.println("\n--- Big drop (alert expected) ---");
        appleStock.updatePrice(135.00);

        System.out.println("\n--- Big jump (alert expected) ---");
        appleStock.updatePrice(170.00);

    }
}
