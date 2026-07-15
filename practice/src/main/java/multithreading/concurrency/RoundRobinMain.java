package multithreading.concurrency;

public class RoundRobinMain {
    public static void main(String[] args) {
        RoundRobin rr = new RoundRobin(20, 3);
        String[] arr = {"A", "B", "C"};
        for (int id = 0; id < arr.length; id++) {
            int idx = id;
            new Thread(() -> rr.print(arr[idx], idx)).start();
        }
    }
}
