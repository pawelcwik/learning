package pl.com.clockworkgnome.pragmaticunittesting.third;

public class Account {

    private String accountName;
    private int amount;

    public Account(int amount) {
        this.amount = amount;
    }

    public Account(String accountName) {
        this.accountName = accountName;
    }

    public void deposit(int amount) {
        this.amount+=amount;
    }

    public boolean hasPositiveBalance() {
        return this.amount>0;
    }

    public int getBalance() {
        return amount;
    }

    public String getName() {
        return accountName;
    }
}
