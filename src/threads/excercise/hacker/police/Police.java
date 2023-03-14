package threads.excercise.hacker.police;

public class Police extends Thread {
    public Police() {
        setName(this.getClass().getSimpleName());
    }

    @Override
    public void start() {
        System.out.println(getName() + " has started chasing hackers...");

        super.start();
    }

    @Override
    public void run() {
        for (int i = 10; i > 0; i--) {
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println("Thread " + getName() + ": time left to catch hackers: " + i + "sec");
        }

        System.out.println("Office has caught the hackers!");
        System.exit(0);
    }
}
