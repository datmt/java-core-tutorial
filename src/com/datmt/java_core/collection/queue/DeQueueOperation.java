package com.datmt.java_core.collection.queue;

import java.sql.SQLOutput;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public class DeQueueOperation {
    public static void main(String[] args) throws InterruptedException {
//        safeDequeOperations();
        dequeAsStackUsingRemoveFirst();
    }

    private static void dequeOperations() {
        Deque<String> people = new ArrayDeque<>();

        //Adding elements
        people.add("Jane");
        people.offer("Jake");
        people.addFirst("Derek");
        people.addLast("Bob");

        System.out.println("people are: " + people); //people are: [Derek, Jane, Jake, Bob]

        //Getting elements from the tail of the queue
        System.out.println(people.peekLast());//Get the last element, not removing
        System.out.println(people.pollLast());//Get the last element, removing

        System.out.println("people are: " + people); //people are: [Derek, Jane, Jake]

        //Getting elements from the head of the queue
        System.out.println(people.getFirst()); //get the first element, not removing but throws exception on empty queue
        System.out.println(people.peekFirst()); //same as get first, but not throwing an exception
        System.out.println(people.removeFirst()); //get the first element and remove it


    }

    private static void removeOccurrence() {
        Deque<String> people = new ArrayDeque<>();

        //Adding elements
        people.add("Jane");
        people.offer("Jake");

        System.out.println("people are: " + people); //people are: [Jane, Jake]

        //Add element to the beginning and end of the queue
        people.addFirst("Jim");
        people.addLast("Jim");

        //Remove first, last occurrence of an element
        System.out.println("people are: " + people); //people are: [Jim, Jane, Jake, Jim]

        System.out.println(people.removeFirstOccurrence("Jim"));

        System.out.println("people are: " + people); //people are: [Jane, Jake, Jim]

        System.out.println(people.removeLastOccurrence("Jim"));

        System.out.println("people are: " + people); //people are: [Jane, Jake]
    }


    private static void nonSafeDequeOperations() throws InterruptedException {
        Deque<Integer> numberQueue = new ArrayDeque<>();

        for (int i = 0; i < 20; i++) {
            numberQueue.offer(i);
        }

        //Do some concurrent removing
        ExecutorService service = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++)
            service.submit(() -> System.out.println("Taking element: " + numberQueue.remove()));

        service.shutdown();
        service.awaitTermination(10, TimeUnit.SECONDS);

        System.out.println("Number now: " + numberQueue);


    }

    private static void safeDequeOperations() throws InterruptedException {
        Deque<Integer> numberQueue = new LinkedBlockingDeque<>();
        Deque<Integer> numberQueue2 = new ConcurrentLinkedDeque<>();
        for (int i = 0; i < 20; i++) {
            numberQueue.offer(i);
        }

        //Do some concurrent removing
        ExecutorService service = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++)
            service.submit(() -> System.out.println("Taking element: " + numberQueue.remove()));

        service.shutdown();
        service.awaitTermination(10, TimeUnit.SECONDS);

        System.out.println("Number now: " + numberQueue);


    }

    private static void dequeAsStackUsingRemoveLast() {
        Deque<String> stack = new ArrayDeque<>();
        stack.addLast("Jake");
        stack.offer("Jim");

        System.out.println(stack.removeLast());//Jim
        System.out.println(stack.removeLast());//Jake
    }

    private static void dequeAsStackUsingRemoveFirst() {
        Deque<String> stack = new ArrayDeque<>();
        stack.addFirst("Jake");
        stack.addFirst("Jim");

        System.out.println(stack.removeFirst());//Jim
        System.out.println(stack.removeFirst());//Jake
    }
}
