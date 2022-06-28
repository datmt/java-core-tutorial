package com.datmt.java_core.collection.queue.priority;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

public class PriorityQueueOperations {

    public static void main(String[] args) throws InterruptedException {
        safePriorityQueue();
    }

    private static void priorityQueueExample() {
        Queue<Integer> priority = new PriorityQueue<>();

        priority.add(1);
        priority.offer(0);
        priority.add(4);

        System.out.println("The queue is: " + priority);
    }

    private static void priorityQueueExampleString() {
        PriorityQueue<String> priority = new PriorityQueue<>(String::compareTo);

        priority.offer("Zed");
        priority.offer("Karen");
        priority.offer("Abby");

        System.out.println("The queue is: " + priority);

        System.out.println(priority.remove());
        System.out.println(priority.remove());
        System.out.println(priority.remove());
    }


    private static void priorityQueueCustomObjectUsingComparator() {
        PriorityQueue<People> people = new PriorityQueue<>(Comparator.comparing(People::getName));
        people.offer(new People("Aaron"));
        people.offer(new People("Zed"));
        people.offer(new People("Chris"));

        System.out.println(people.remove());
        System.out.println(people.remove());
        System.out.println(people.remove());
    }

    private static void priorityQueueCustomObjectUsingComparableInterface() {
        PriorityQueue<People> people = new PriorityQueue<>();
        people.offer(new People("Aaron"));
        people.offer(new People("Zed"));
        people.offer(new People("Chris"));

        System.out.println(people.remove());
        System.out.println(people.remove());
        System.out.println(people.remove());
    }

    private static void nonSafePriorityQueue() throws InterruptedException {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        //adding 20 elements
        for (int i = 0; i < 20; i ++) {
            int finalI = i;
            executorService.submit(() -> queue.offer(finalI));
        }

        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.SECONDS);

        System.out.println("queue: " + queue + " has " + queue.size() + " items");
    }

    private static void safePriorityQueue() throws InterruptedException {
        PriorityBlockingQueue<Integer> queue = new PriorityBlockingQueue<>();
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        //adding 20 elements
        for (int i = 0; i < 20; i ++) {
            int finalI = i;
            executorService.submit(() -> queue.offer(finalI));
        }

        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.SECONDS);

        System.out.println("queue: " + queue + " has " + queue.size() + " items");
    }
}
