package threads.interruptions;

public class Main2 {
    public static void main(String[] args) {
        CustomThread customThread = new CustomThread();

        customThread.setName("Custom Thread");

        customThread.start();
        customThread.interrupt();
    }

    static class CustomThread extends Thread {
        @Override
        public void run() {
            System.out.println(getName() + " has started");

            lengthyOperation();
        }

        private void lengthyOperation() {
            try {
                sleep(1000000);
            } catch (Exception e) {
                System.out.println(getName() + " was interrupted!");
                System.out.println(e);
            }
        }
    }
}
