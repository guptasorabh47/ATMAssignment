package ie.assignment.model;

public class Atm {
    private AtmBalance balance;
    private int amount;

    public Atm(){}

    public Atm(AtmBalance balance, int amount) {
        this.balance = balance;
        this.amount = amount;
    }

    public AtmBalance getBalance() {
        return balance;
    }

    public int getAmount() {
        return amount;
    }

    public void setBalance(AtmBalance balance) {
        this.balance = balance;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
