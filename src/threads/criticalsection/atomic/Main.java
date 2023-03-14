package threads.criticalsection.atomic;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Inventory inventory = new Inventory(0);
        List<Thread> threads = new ArrayList<>();

        threads.add(new IncrementThread(inventory));
        threads.add(new Thread(new DecrementTask(inventory)));

        threads.forEach(Thread::start);

        threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        System.out.println(inventory.getCount());
    }

    static class IncrementThread extends Thread {
        private final Inventory inventory;

        public IncrementThread(Inventory inventory) {
            this.inventory = inventory;
        }

        @Override
        public void run() {
            inventory.increaseCount();
        }
    }

    static class DecrementTask implements Runnable {
        private final Inventory inventory;

        public DecrementTask(Inventory inventory) {
            this.inventory = inventory;
        }

        @Override
        public void run() {
            inventory.decreaseCount();
        }
    }
}
