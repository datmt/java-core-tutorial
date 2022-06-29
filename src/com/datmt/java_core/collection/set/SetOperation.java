package com.datmt.java_core.collection.set;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SetOperation {
    public static void main(String[] args) {
        inspectSet();
    }

    private static void createSets() {
        //Create an empty set
        Set<String> name = Set.of();

        //Create a set with some values
        Set<String> classmate = Set.of("Jane", "Jake");

        //Create a set with just one element
        Set<Integer> houseNumber = Collections.singleton(111);

        //Create a HashSet
        Set<String> friends = new HashSet<>();

        friends.add("Jim");
        friends.add("Kim");
    }

    private static void accessSetMembers() {
        HashSet<String> friends = new HashSet<>();

        friends.add("Jim");
        friends.add("Kim");

        friends.iterator().forEachRemaining(System.out::println);
    }

    private static void setOperations() {
        Set<String> mySet = new HashSet<>();
        //add one element
        mySet.add("Lisa");

        //add many elements
        mySet.addAll(List.of("Leo", "Neo", "Jake"));

        //Check if the set is empty

        //Remove one element
        mySet.remove("Lisa");

        //Remove multiple elements
        mySet.removeAll(List.of("Leo", "Neo"));

        //empty the set
        mySet.clear();

        //Use retainAll to keep only the selected elements
        mySet.addAll(List.of("Jim", "Derek", "Cat", "Horseman"));

        mySet.retainAll(List.of("Derek", "Cat"));

        System.out.println("Now the set is: " + mySet);
    }

    private static void inspectSet() {

        Set<String> mySet = new HashSet<>(List.of("Jake", "Luis", "Liam"));

        //get number of items in the set
        System.out.println("Number of elements: " + mySet.size());

        //check if a set contain an element
        System.out.println("Contains Luis? " + mySet.contains("Luis"));

    }
}
