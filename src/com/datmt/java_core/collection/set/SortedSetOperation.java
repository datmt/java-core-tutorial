package com.datmt.java_core.collection.set;

import com.sun.source.tree.Tree;

import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SortedSetOperation {
    public static void main(String[] args) throws InterruptedException {
        safeTreeSetOperation();
    }

    private static void sortedSetBasicOperations() {

        SortedSet<String> mySet = new TreeSet<>();
        mySet.add("Karen");
        mySet.add("Zed");
        mySet.add("Abby");
        mySet.add("Quentin");
        mySet.add("Randall");

        System.out.println("The full set is: " + mySet);

        //Getting first and last elements
        System.out.println("First element: " + mySet.first());
        System.out.println("Last element: " + mySet.last());

        //Getting all elements from Karen
        System.out.println("From Karen: " + mySet.tailSet("Karen"));

        //Getting all elements before Quentin
        System.out.println("To Quentin: " + mySet.headSet("Quentin"));

        //Getting all elements from Karen to before Quentin
        System.out.println("From Karen To Quentin: " + mySet.subSet("Karen", "Quentin"));
    }

    private static void nonSafeTreeSetOperation() throws InterruptedException {
        var numberSet = new TreeSet<Integer>();

        ExecutorService service = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 20; i++) {
            int finalI = i;
            service.submit(() -> numberSet.add(finalI) );
        }

        service.shutdown();
        service.awaitTermination(10, TimeUnit.SECONDS);

        System.out.println("The set size: " + numberSet.size());
        System.out.println("The set: " + numberSet);

    }

    private static void safeTreeSetOperation() throws InterruptedException {
        var numberSet = new ConcurrentSkipListSet<Integer>();

        ExecutorService service = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 20; i++) {
            int finalI = i;
            service.submit(() -> numberSet.add(finalI) );
        }

        service.shutdown();
        service.awaitTermination(10, TimeUnit.SECONDS);

        System.out.println("The set size: " + numberSet.size());
        System.out.println("The set: " + numberSet);
    }
}
