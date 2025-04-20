package alt.bank.model;

import java.util.Objects;
import java.util.concurrent.locks.ReentrantLock;

public class Account {
    private int accountNumber;
    private int balance;
    private final ReentrantLock lock = new ReentrantLock();

    public Account(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public int getBalance() {
        return balance;
    }

    public void debit(int sum) {
        balance = balance + sum;
    }

    public void credit(int sum) {
        balance = balance - sum;
    }
    public void lock() {
        lock.lock();
    }

    public void unlock() {
        lock.unlock();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Account account)) return false;
        return accountNumber == account.accountNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(accountNumber);
    }
}
