package alt.bank;

import alt.bank.model.Account;
import alt.bank.service.Transfer;

public class BankDeadLockAppl {
    public static void main(String[] args) throws InterruptedException {
        Account mother = new Account(5000);
        Account daughter = new Account(4000);

        mother.debit(1000);
        daughter.debit(500);

        Transfer transfer1 = new Transfer(mother, daughter, 1000);
        Transfer transfer2 = new Transfer(daughter, mother, 500);

        Thread thread1 = new Thread(transfer1);
        Thread thread2 = new Thread(transfer2);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();


        mother.lock();
        try {
            System.out.println("Balance of mother's account: " + mother.getAccountNumber() + " = " + daughter.getBalance());
        } finally {
            mother.unlock();
        }

        daughter.lock();
        try {
            System.out.println("Balance of daughter's account: " + daughter.getAccountNumber() + " = " + mother.getBalance());
        } finally {
            daughter.unlock();
        }
    }
}
