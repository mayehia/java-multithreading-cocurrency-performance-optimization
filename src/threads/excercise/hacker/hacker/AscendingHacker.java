package threads.excercise.hacker.hacker;

import threads.excercise.hacker.Vault;

public class AscendingHacker extends Hacker {
    public AscendingHacker(Vault vault, int range) {
        super(vault, range);
    }

    @Override
    public void crackVault() {
        for (int guess = 0; guess <= getRange(); guess++) {
            try {
                sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if (getVault().isCorrectPassword(guess)) {
                System.out.println("Hacker " + this.getClass().getSimpleName() + " has opened the vault! password is: " + guess);
                System.exit(0);
            }
        }
    }
}
