package threads.criticalsection.synchronizationoverclassobject;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Inventory inventory = new Inventory(0);

        IncrementThread incrementThread = new IncrementThread(inventory);
        Thread decrementThread = new Thread(new DecrementTask(inventory));

        incrementThread.start();
        decrementThread.start();

        // wait until the 2 threads finish execution, then display the result.
        incrementThread.join();
        decrementThread.join();

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
