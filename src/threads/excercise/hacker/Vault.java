package threads.excercise.hacker;

import java.util.Random;

public class Vault {
    private final int password;

    public Vault(int range) {
        Random random = new Random();
        password = random.nextInt(range);
        System.out.println("Secret Password is: " + password);
    }

    public boolean isCorrectPassword(int guess) {
        return password == guess;
    }
}
