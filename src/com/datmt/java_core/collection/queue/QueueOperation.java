package com.datmt.java_core.collection.queue;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

public class QueueOperation {
    public static void main(String[] args) {
        Queue<String> people = new LinkedList<>();
        Queue<String> emptyQueue = new LinkedList<>();

        //1. Adding elements to queue
        people.offer("Anna");
        people.add("Bob");

       //2. Get element from queue, not removing
        System.out.println(people.peek());
        System.out.println(people.element());

        System.out.printf("Now the queue contains %s people",  people.size());
        System.out.println();

        //3. Get element and also remove
        System.out.println(people.remove());
        System.out.println(people.poll());


        System.out.printf("Now the queue contains %s people",  people.size());

        //4. Get element from an empty queue
        System.out.println("the following method are safe on empty queue");
        System.out.println(emptyQueue.poll());
        System.out.println(emptyQueue.peek());


        System.out.println("the following method calls would throw exceptions");

        try {
            System.out.println(emptyQueue.remove());
        } catch (NoSuchElementException ex) {
            System.out.println("element does not exist");
        }

        try {
            System.out.println(emptyQueue.element());
        } catch (NoSuchElementException ex) {
            System.out.println("element does not exist");
        }
    }
}
