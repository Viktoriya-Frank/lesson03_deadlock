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

        synchronized (accountFrom) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            synchronized (accountTo) {
                if (accountFrom.getBalance() >= sum) {
                    accountFrom.credit(sum);
                    accountTo.debit(sum);
                }
            }
        }
    }
}
