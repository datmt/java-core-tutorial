package com.datmt.java_core.concurrency.threadlocalex;

public class TLRemoveDemo {
    public static void main(String[] args) {
        var gorillaBank = new ThreadLocal<Integer>();
        new Thread(() -> {
            System.out.println("Joe deposits $1000");
            gorillaBank.set(1000);

            System.out.println("Joe's balance: " + gorillaBank.get());

            gorillaBank.remove();

            System.out.println("Joe's balance: " + gorillaBank.get());
        }).start();

    }
}
