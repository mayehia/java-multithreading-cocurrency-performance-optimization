package threads.criticalsection.criticalsectionproblem;

public class Inventory {
    private int count;

    public Inventory(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void increaseCount() {
        for (int i = 0; i < 100000; i++) {
            count += 1;
        }
    }

    public void decreaseCount() {
        for (int i = 0; i < 100000; i++) {
            count -= 1;
        }
    }
}
