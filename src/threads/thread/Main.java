package threads.thread;

public class Main {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());

        CustomThread customThread = new CustomThread();

        customThread.start();
    }

    static class CustomThread extends Thread {
        @Override
        public void run() {
            setName("New Thread Logical Name");

            setUncaughtExceptionHandler((Thread t, Throwable e) -> {
                System.out.println(e);
            });

            System.out.println(getName());
        }
    }
}
