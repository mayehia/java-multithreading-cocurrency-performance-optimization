package threads.excercise.hacker;

import threads.excercise.hacker.hacker.AscendingHacker;
import threads.excercise.hacker.hacker.DescendingHacker;
import threads.excercise.hacker.police.Police;

import java.util.ArrayList;
import java.util.List;

public class Main {
    final static int PASSWORD_RANGE = 9999;
    public static void main(String[] args) {
        Vault vault = new Vault(PASSWORD_RANGE);
        List<Thread> threads = new ArrayList<>();

        threads.add(new DescendingHacker(vault, PASSWORD_RANGE));
        threads.add(new AscendingHacker(vault, PASSWORD_RANGE));
        threads.add(new Police());

        for (Thread thread : threads) {
            thread.start();
        }
    }
}
