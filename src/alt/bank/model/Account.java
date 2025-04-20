package alt.bank.model;

import java.util.Objects;

public class Account {
    private int accountNumber;
    private int balance;

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
