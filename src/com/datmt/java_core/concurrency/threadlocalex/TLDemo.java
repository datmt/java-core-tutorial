package com.datmt.java_core.concurrency.threadlocalex;

import java.util.concurrent.ExecutionException;

public class TLDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        var gorillaBank = new ThreadLocal<Integer>();

        new Thread(() -> {
            System.out.println("Joe deposits $1000");
            gorillaBank.set(1000);

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Joe about to withdraw $500");
            gorillaBank.set(gorillaBank.get() - 500);

            System.out.println("Joe's balance: " + gorillaBank.get());
        }).start();

        new Thread(() -> {
            System.out.println("Jimmy deposits $2000");
            gorillaBank.set(2000);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Jimmy about to withdraw $500");
            gorillaBank.set(gorillaBank.get() - 500);

            System.out.println("Jimmy's balance: " + gorillaBank.get());
        }).start();
    }
}
