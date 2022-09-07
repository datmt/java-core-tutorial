package com.datmt.java_core.concurrency;

import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadCommunication {

    public static void main(String[] args) {
        BankAccount myAccount = new BankAccount();
        var executors = Executors.newFixedThreadPool(5);
        executors.submit(new BuyThings(myAccount, "buy new macbook pro", 3_000));
        executors.submit(new BuyThings(myAccount, "buy new phone", 500));
        executors.submit(new BuyThings(myAccount, "buy new keyboard", 400));
        executors.submit(new BuyThings(myAccount, "buy new gadgets", 500));

        int cycle = 6;
        while (cycle > 0) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
               ex.printStackTrace();
            }

            executors.submit(new PayEmployee(myAccount, 1_000));
            cycle--;
        }

        executors.shutdown();
    }

}

class BankAccount {
    private int balance = 0;


    private static Lock lock = new ReentrantLock();
    private static Condition paycheckArrivedCondition = lock.newCondition();

    public void getPaid(int amount) {
        lock.lock();
        try {
            System.out.println("Getting paid " + amount);
            balance += amount;
            paycheckArrivedCondition.signalAll();
        } finally {
            lock.unlock();
        }
    }


    public void withdraw(int amount, String purpose) {
        lock.lock();
        try {
            while (balance < amount) {
                paycheckArrivedCondition.await();
            }
            System.out.println("Withdraw " + amount + " to " + purpose);
            balance -= amount;

            System.out.println("new balance -> " + balance);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

class BuyThings implements Runnable {
    private final BankAccount bankAccount;
    private final String purpose;

    private final int amount;

    public BuyThings(BankAccount account, String purpose, int amount) {
        this.bankAccount = account;
        this.purpose = purpose;
        this.amount = amount;
        System.out.println("Plan to " + purpose + " with " + amount);
    }

    @Override
    public void run() {
        bankAccount.withdraw(amount, purpose);
    }
}

class PayEmployee implements Runnable {

    private final BankAccount bankAccount;
    private final int amount;

    PayEmployee(BankAccount employeeBankAccount, int amount) {
        this.bankAccount = employeeBankAccount;
        this.amount = amount;
    }

    @Override
    public void run() {
        bankAccount.getPaid(amount);
    }
}