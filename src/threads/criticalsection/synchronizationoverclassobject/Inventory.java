package threads.criticalsection.synchronizationoverclassobject;

public class Inventory {
    private int count;

    public Inventory(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public synchronized void increaseCount() {
        for (int i = 0; i < 100000; i++) {
            count += 1;
        }
    }

    public synchronized void decreaseCount() {
        for (int i = 0; i < 100000; i++) {
            count -= 1;
        }
    }
}
