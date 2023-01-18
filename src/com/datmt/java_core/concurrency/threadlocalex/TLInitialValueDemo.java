package com.datmt.java_core.concurrency.threadlocalex;

public class TLInitialValueDemo {
    public static void main(String[] args) {
        var gorillaBank = ThreadLocal.withInitial(() -> 1000);
        new Thread(() -> {
            System.out.println("Joe deposits $1000");
            gorillaBank.set(gorillaBank.get() + 1000);

            System.out.println("Joe's balance: " + gorillaBank.get());
        }).start();

    }
}
