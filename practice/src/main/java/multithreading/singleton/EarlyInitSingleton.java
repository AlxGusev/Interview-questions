package multithreading.singleton;

public class EarlyInitSingleton {
    private static final EarlyInitSingleton instance = new EarlyInitSingleton();
    private EarlyInitSingleton(){}
    public static EarlyInitSingleton getInstance() {
        return instance;
    }
}
