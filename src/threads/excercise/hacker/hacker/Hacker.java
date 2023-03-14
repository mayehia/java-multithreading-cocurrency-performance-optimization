package threads.excercise.hacker.hacker;

import threads.excercise.hacker.Vault;

public abstract class Hacker extends Thread {
    private final Vault vault;
    private final int range;

    public Hacker(Vault vault, int range) {
        this.vault = vault;
        this.range = range;
    }

    public int getRange() {
        return range;
    }

    public Vault getVault() {
        return vault;
    }

    @Override
    public void start() {
        System.out.println("Hacker " + this.getClass().getSimpleName() + " has started guessing the vault password...");

        super.start();
    }

    @Override
    public void run() {
        crackVault();
    }

    public abstract void crackVault();
}
