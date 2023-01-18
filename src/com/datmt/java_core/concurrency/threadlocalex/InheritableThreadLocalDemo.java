package com.datmt.java_core.concurrency.threadlocalex;

public class InheritableThreadLocalDemo {
    public static void main(String[] args) {
        var gorillaBank = new InheritableThreadLocal<Integer>();
        var oldBank = ThreadLocal.withInitial(() -> 0);
        oldBank.set(3000);
        gorillaBank.set(1000);
        new Thread(() -> {
            System.out.println("Joe deposits $1000 at gorilla bank");
            gorillaBank.set(gorillaBank.get() + 1000);

            System.out.println("Joe deposits $1000 at old bank");
            oldBank.set(oldBank.get() + 1000);

            System.out.println("Joe's balance at gorilla bank: " + gorillaBank.get());
            System.out.println("Joe's balance at old bank: " + oldBank.get());
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Jane deposits $1000 at gorilla bank");
            gorillaBank.set(gorillaBank.get() + 1000);


            System.out.println("Jane's balance at gorilla bank: " + gorillaBank.get());
        }).start();
    }
}
