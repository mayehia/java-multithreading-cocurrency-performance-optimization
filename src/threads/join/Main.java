package threads.join;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        List<Integer> values = Arrays.asList(1, 2, 3, 4, 5);
        Thread doubleThread = new Thread(new DoubleValues(values));

        doubleThread.start();

        doubleThread.join();

        values.forEach(System.out::println);
    }

    static class DoubleValues implements Runnable {
        private final List<Integer> values;

        public DoubleValues(List<Integer> values) {
            this.values = values;
        }
        @Override
        public void run() {
            doubleValues();
        }

        private void doubleValues() {
            values.replaceAll(integer -> integer * 2);
        }
    }
}
