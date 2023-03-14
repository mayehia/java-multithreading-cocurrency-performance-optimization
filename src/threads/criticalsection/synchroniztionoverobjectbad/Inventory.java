package threads.criticalsection.synchroniztionoverobjectbad;

public class Inventory {
    private int count;
    private final Object synchronizationObject1 = new Object();
    private final Object synchronizationObject2 = new Object();

    public Inventory(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void increaseCount() {
        synchronized (synchronizationObject1) {
            for (int i = 0; i < 100000; i++) {
                count += 1;
            }
        }
    }

    public void decreaseCount() {
        synchronized (synchronizationObject2) {
            for (int i = 0; i < 100000; i++) {
                count -= 1;
            }
        }
    }
}
