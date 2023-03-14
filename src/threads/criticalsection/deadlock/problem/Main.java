package threads.criticalsection.deadlock.problem;

public class Main {
    public static void main(String[] args) {
        RailwayWithIntersection railwayWithIntersection =
                new RailwayWithIntersection();

        TrainAThread trainAThread = new TrainAThread(railwayWithIntersection);
        TrainBThread trainBThread = new TrainBThread(railwayWithIntersection);

        trainAThread.start();
        trainBThread.start();

        System.out.println("Program has completed execution!");
    }

    static class TrainAThread extends Thread {
        private final RailwayWithIntersection railwayWithIntersection;

        public TrainAThread(RailwayWithIntersection railwayWithIntersection) {
            this.railwayWithIntersection = railwayWithIntersection;
            setName("Train A");
        }

        @Override
        public void run() {
            railwayWithIntersection.runInlineA();
        }
    }

    static class TrainBThread extends Thread {
        private final RailwayWithIntersection railwayWithIntersection;

        public TrainBThread(RailwayWithIntersection railwayWithIntersection) {
            this.railwayWithIntersection = railwayWithIntersection;
            setName("Train B");
        }

        @Override
        public void run() {
            railwayWithIntersection.runInlineB();
        }
    }
}
