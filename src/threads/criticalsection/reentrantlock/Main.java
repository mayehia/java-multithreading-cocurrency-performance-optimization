package threads.criticalsection.reentrantlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String... args) throws InterruptedException {
        Inventory inventory = new Inventory();

        Thread increasingThread = new Thread(new IncreasingTask(inventory));
        Thread decreasingThread = new Thread(new DecreasingTask(inventory));

        increasingThread.start();
        decreasingThread.start();

        increasingThread.join();
        decreasingThread.join();

        System.out.println("resource value is: " + inventory.getCount());
    }

    static class IncreasingTask implements Runnable {
        private final Inventory inventory;

        public IncreasingTask(Inventory inventory) {
            this.inventory = inventory;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " exec");

            inventory.increaseInventory();
        }
    }

    static class DecreasingTask implements Runnable {
        private final Inventory inventory;

        public DecreasingTask(Inventory inventory) {
            this.inventory = inventory;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " exec");

            inventory.decreaseInventory();
        }
    }

    static class Inventory {
        private int count = 0;
        public final Lock reentrantLock = new ReentrantLock();

        public void increaseInventory() {
            reentrantLock.lock();
            for (int i = 0; i <= 100000; i++) {
                changeValue('+');
            }
            reentrantLock.unlock();
        }

        public void changeValue(char sign) {
            if (sign == '-')
                count -= 1;
            else if (sign == '+')
                count += 1;
            else
                throw new IllegalArgumentException("Undefined sign");
        }


        public void decreaseInventory() {
            reentrantLock.lock();
            for (int i = 0; i <= 100000; i++) {
                changeValue('-');
            }
            reentrantLock.unlock();
        }

        public int getCount() {
            return count;
        }
    }
}
