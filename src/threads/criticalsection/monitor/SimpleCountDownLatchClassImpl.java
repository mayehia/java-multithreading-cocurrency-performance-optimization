package threads.criticalsection.monitor;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SimpleCountDownLatchClassImpl {
    private int count;

    public SimpleCountDownLatchClassImpl(int count) {
        this.count = count;
        if (count < 0) {
            throw new IllegalArgumentException("count cannot be negative");
        }
    }

    /**
     * Causes the current thread to wait until the latch has counted down to zero.
     * If the current count is already zero then this method returns immediately.
     */
    public synchronized void await() throws InterruptedException {
        if (count == 0) {
            return;
        } else {
            wait();
        }
    }

    /**
     *  Decrements the count of the latch, releasing all waiting threads when the count reaches zero.
     *  If the current count already equals zero then nothing happens.
     */
    public synchronized void countDown() {

        if (count == 0)
            return;

        if (--count <= 0) {
            notifyAll();
        }

    }

    /**
     * Returns the current count.
     */
    public int getCount() {
        return count;
    }
}
