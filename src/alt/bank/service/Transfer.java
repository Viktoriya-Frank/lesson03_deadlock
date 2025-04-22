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
        Account firstLock = accountFrom.getAccountNumber() > accountTo.getAccountNumber() ? accountTo : accountFrom;
        Account secondLock = accountFrom.getAccountNumber() > accountTo.getAccountNumber() ? accountFrom : accountTo;
        firstLock.lock();
        try {
            Thread.sleep(1000);
            secondLock.lock();
            try {
                if (accountFrom.getBalance() >= sum) {
                    accountFrom.credit(sum);
                    accountTo.debit(sum);
                }
            } finally {
                secondLock.unlock();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            firstLock.unlock();
        }
    }
}
