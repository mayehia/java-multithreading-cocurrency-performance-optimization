package threads.criticalsection.criticalsectionproblem;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Inventory inventory = new Inventory(0);

        IncrementThread incrementThread = new IncrementThread(inventory);
        Thread decrementThread = new Thread(new DecrementTask(inventory));

        incrementThread.start();
//        incrementThread.join(); waiting until the increment thread finishes execution, then start the decrementThread.

        decrementThread.start();
//        decrementThread.join(); waiting until the decrement thread finishes execution, to display a correct result.

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
