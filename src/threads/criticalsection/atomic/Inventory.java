package threads.criticalsection.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class Inventory {
    private final AtomicInteger count;

    public Inventory(Integer count) {
        this.count = new AtomicInteger(count);
    }

    public AtomicInteger getCount() {
        return count;
    }

    public void increaseCount() {
        for (int i = 0; i < 100000; i++) {
            count.incrementAndGet();
        }
    }

    public void decreaseCount() {
        for (int i = 0; i < 100000; i++) {
            count.decrementAndGet();
        }
    }
}
