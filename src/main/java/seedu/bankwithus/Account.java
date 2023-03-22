package seedu.bankwithus;

public class Account {
    private String name;
    private float balance;

    /**
     * Instantiates an account object
     * 
     * @param name    initialise in the name of the account
     * @param balance initialise the balance of the account
     */
    public Account(String name, float balance) {
        this.name = name;
        this.balance = balance;
    }

    public String getAccountName() {
        return name;
    }

    public float getAccountBalance() {
        return balance;
    }

    public void addBalance(float balance) {
        this.balance += balance;
    }

    public void subtractBalance(float balance) {
        this.balance -= balance;
    }
}
