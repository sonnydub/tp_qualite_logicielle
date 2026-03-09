public class BankAccount {

    private double balance = 0;

    public void deposit(double amount) {
        if (amount <= 0) throw new IllegalArgumentException();
        balance += amount;
    }

    public void withdraw(double amount) {
        if (amount <= 0) throw new IllegalArgumentException();
        if (amount > balance) throw new IllegalArgumentException();
        balance -= amount;
    }

    public double getBalance() {
        return balance;
    }
}
