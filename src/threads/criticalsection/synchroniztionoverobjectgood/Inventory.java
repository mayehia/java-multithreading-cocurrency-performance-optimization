package threads.criticalsection.synchroniztionoverobjectgood;

public class Inventory {
    private int count;
    private final Object synchronizationObject = new Object();
    public Inventory(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void increaseCount() {
        synchronized (synchronizationObject) {
            for (int i = 0; i < 100000; i++) {
                count += 1;
            }
        }
    }

    public void decreaseCount() {
        synchronized (synchronizationObject) {
            for (int i = 0; i < 100000; i++) {
                count -= 1;
            }
        }
    }
}
