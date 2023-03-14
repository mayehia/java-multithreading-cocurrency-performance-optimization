package threads.criticalsection.producerconsumer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class BadImplementationStarvationProblem {
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
                    inventory.showCount();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    static class Inventory {
        Semaphore semaphore = new Semaphore(1);

        private int count = 0;

        public void setCount(int count) throws InterruptedException {
            semaphore.acquire();
            System.out.println("Lock acquired in setCount() by: " + Thread.currentThread().getName());
            this.count = count;
            System.out.println("Lock released in setCount() by: " + Thread.currentThread().getName());
            semaphore.release();
        }

        public void showCount() throws InterruptedException {
            semaphore.acquire();
            System.out.println("Lock acquired in showCount() by: " + Thread.currentThread().getName());
            System.out.println("count is: " + count);
            System.out.println("Lock released in showCount() by: " + Thread.currentThread().getName());
            semaphore.release();
        }
    }
}
