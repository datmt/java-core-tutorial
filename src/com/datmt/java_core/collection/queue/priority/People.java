package com.datmt.java_core.collection.queue.priority;

public class People implements Comparable<People> {
    private String name;

    public People(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "People[name=" + name + "]";
    }

    @Override
    public int compareTo(People people) {
        return name.compareTo(people.getName());
    }
}
