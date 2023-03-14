package threads.interruptions.daemon;

public class Main {
    public static void main(String[] args) {
        Thread customThread = new Thread(new CustomTask());

        customThread.setName("Custom thread");
        customThread.setDaemon(true);

        customThread.start();
    }

    static class CustomTask implements Runnable {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " has started");

            lengthyOperation();
        }

        // Very Dummy lengthy operation implementation
        private void lengthyOperation() {
            for (int i = 0; i <= 2000000000; i++) {
                for (int x = 0; x <= 2000000000; x++) {

                }
                for (int x = 0; x <= 2000000000; x++) {

                }
                for (int x = 0; x <= 2000000000; x++) {

                }
                for (int x = 0; x <= 2000000000; x++) {

                }
            }
        }
    }
}
