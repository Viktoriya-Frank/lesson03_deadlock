package alt.bank.service;

import alt.bank.model.Account;

public class Transfer implements Runnable {
    private Account accountFrom;
    private Account accountTo;
    private int sum;

    public Transfer(Account accountFrom, Account accountTo, int sum) {
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.sum = sum;
    }


    @Override
    public void run() {
        accountFrom.lock();
        try {
            Thread.sleep(1000);
            accountTo.lock();
            try {
                if (accountFrom.getBalance() >= sum) {
                    accountFrom.credit(sum);
                    accountTo.debit(sum);
                }
            } finally {
                accountTo.unlock();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            accountFrom.unlock();
        }
    }
}
