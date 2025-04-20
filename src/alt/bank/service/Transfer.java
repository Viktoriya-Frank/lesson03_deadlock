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
        Account lock1 = accountFrom.getAccountNumber() < accountTo.getAccountNumber() ? accountFrom : accountTo;
        Account lock2 = accountFrom.getAccountNumber() < accountTo.getAccountNumber() ? accountTo : accountFrom;

        synchronized (lock1) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            synchronized (lock2) {
                if (accountFrom.getBalance() >= sum) {
                    accountFrom.credit(sum);
                    accountTo.debit(sum);
                    System.out.println("Transferred " + sum + " from " +
                            accountFrom.getAccountNumber() + " to " + accountTo.getAccountNumber());
                } else {
                    System.out.println("Not enough funds in account " + accountFrom.getAccountNumber());
                }
                }
            }
        }
    }

