package threads.criticalsection.producerconsumer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class GoodImplementation {
    public static void main(String... args) {
        List<Thread> threads = new ArrayList<>();
        Inventory inventory = new Inventory();

        threads.add(new Producer(inventory));
        threads.add(new Consumer(inventory));

        threads.forEach(Thread::start);
    }

    static class Producer extends Thread {
        private final Inventory inventory;
        private final Random random;

        public Producer(Inventory inventory) {
            this.inventory = inventory;
            random = new Random();
            setName("Producer");
        }
        @Override
        public void run() {
            while (true) {
                int randomCount = random.nextInt();
                try {
                    inventory.setCount(randomCount);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    static class Consumer extends Thread {
        private final Inventory inventory;

        public Consumer(Inventory inventory) {
            setName("Consumer");
            this.inventory = inventory;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    System.out.println(getName() + " is consuming: " + inventory.getCount());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    static class Inventory {
        Semaphore full = new Semaphore(1);
        Semaphore empty = new Semaphore(0);

        private int count = 0;

        public void setCount(int count) throws InterruptedException {
            try {
                full.acquire();
                System.out.println("Lock acquired in setCount() by: " + Thread.currentThread().getName());
                this.count = count;
            } finally {
                System.out.println("Empty Lock released in setCount() by: " + Thread.currentThread().getName());
                empty.release();
            }
        }

        public int getCount() throws InterruptedException {
            try {
                empty.acquire();
                System.out.println("Lock acquired in showCount() by: " + Thread.currentThread().getName());
                System.out.println("Consuming count: " + count);
                return count;
            } finally {
                System.out.println("Full Lock released in showCount() by: " + Thread.currentThread().getName());
                full.release();
            }
        }
    }
}
