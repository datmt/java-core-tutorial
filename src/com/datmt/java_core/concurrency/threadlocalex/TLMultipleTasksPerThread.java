package com.datmt.java_core.concurrency.threadlocalex;

import java.util.concurrent.Executors;

public class TLMultipleTasksPerThread {
    public static void main(String[] args) {
        var gorillaBank = ThreadLocal.withInitial(() -> 0);

        var executor = Executors.newSingleThreadExecutor();

        for (int i =0; i < 5; i++) {
            executor.submit(() -> {
                System.out.println("Anonymous user donates $1000");
                gorillaBank.set(gorillaBank.get() + 1000);

                System.out.println("Charity's balance: " + gorillaBank.get());
            });
        }

        executor.shutdown();
    }
}
