package threads.criticalsection.deadlock.problem;

/*
 * This is the shared resource between the running threads (the railway is shared among threads which represent trains).
 */
public class RailwayWithIntersection {
    private final Object lineA = new Object();
    private final Object lineB = new Object();

    public void runInlineA() {
        synchronized (lineA) {
            System.out.println(Thread.currentThread().getName() + ":- Train is going to run in line A");
            System.out.println(Thread.currentThread().getName() + ":- Line A is blocked");

            // to prevent accidents between trains at intersection points
            synchronized (lineB) {
                System.out.println(Thread.currentThread().getName() + ":- Line B is blocked");

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            System.out.println(Thread.currentThread().getName() + ":- Line B is unblocked");
        }

        System.out.println(Thread.currentThread().getName() + ":- Line A is unblocked");
    }

    public void runInlineB() {
        synchronized (lineB) {
            System.out.println(Thread.currentThread().getName() + ":- Train is going to run in line B");
            System.out.println(Thread.currentThread().getName() + ":- Line B is blocked");

            // to prevent accidents between trains at intersection points
            synchronized (lineA) {
                System.out.println(Thread.currentThread().getName() + ":- Line A is blocked");

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            System.out.println(Thread.currentThread().getName() + ":- Line A is unblocked");
        }

        System.out.println(Thread.currentThread().getName() + ":- Line B is unblocked");
    }
}
