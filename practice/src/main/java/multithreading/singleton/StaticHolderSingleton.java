package multithreading.singleton;

public class StaticHolderSingleton {
    private StaticHolderSingleton(){}

    private static class StaticHolder{
        private static final StaticHolderSingleton INSTANCE = new StaticHolderSingleton();
    }

    public static StaticHolderSingleton getInstance() {
        return StaticHolder.INSTANCE;
    }
}
