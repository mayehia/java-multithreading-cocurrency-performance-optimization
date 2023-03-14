package threads.runnable;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Runnable task2 = () -> System.out.println(Thread.currentThread().getName());
        Runnable task3 = () -> {
            Thread.currentThread().setName("thread with custom name");
            Thread.currentThread().setPriority(Thread.MAX_PRIORITY);

            System.out.println(Thread.currentThread().getName());
            System.out.println(Thread.currentThread().getPriority());
        };
        Runnable task4 = () -> {
            Thread.currentThread().setUncaughtExceptionHandler((Thread t, Throwable e) -> {
                System.out.println("Exception handler, exception thrown from thread: " + t.getName() + " and the error: " + e);
            });

            System.out.println(Thread.currentThread().getName());
            throw new RuntimeException("Exception while executing in task4");
        };

        Thread newThread1 = new Thread(new Task1());
        Thread newThread2 = new Thread(task2);
        Thread newThread3 = new Thread(task3);
        Thread newThread4 = new Thread(task4);

        System.out.println(Thread.currentThread().getName());

        newThread1.start();
        newThread2.start();
        newThread3.start();
        newThread4.start();

        Thread.sleep(1000);

        System.out.println(Thread.currentThread().getName());
    }

    static class Task1 implements Runnable {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
        }
    }
}