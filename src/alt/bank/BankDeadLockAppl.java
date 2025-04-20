package alt.bank;

import alt.bank.model.Account;
import alt.bank.service.Transfer;

public class BankDeadLockAppl {
    public static void main(String[] args) throws InterruptedException {
        Account mother = new Account(9999);
        Account daughter = new Account(14999);

        mother.debit(1001);
        daughter.debit(1001);

        Transfer transfer1 = new Transfer(mother, daughter, 900);
        Transfer transfer2 = new Transfer(daughter, mother, 900);

        Thread thread1 = new Thread(transfer1);
        Thread thread2 = new Thread(transfer2);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("Balance of account: " + mother.getAccountNumber() + " = " + mother.getBalance());
        System.out.println("Balance of account: " + daughter.getAccountNumber() + " = " + daughter.getBalance());

    }
}
