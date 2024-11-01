package com.example.demo;

import java.util.ArrayList;
import java.util.List;

// Abstract User class
abstract class User {
    protected String name;
    protected String id;

    public User(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public abstract void displayDetails();
}

// Interface for Registration functionality
interface Registerable {
    void register();
}

// Farmer class that extends User and implements Registerable
class Farmer extends User implements Registerable {
    private List<String> updates;

    public Farmer(String name, String id) {
        super(name, id);
        this.updates = new ArrayList<>();
    }

    @Override
    public void register() {
        System.out.println("Farmer " + name + " with ID " + id + " is successfully registered.");
        updates.add("Welcome, " + name + "! You will receive updates on schemes, subsidies, and practices.");
    }

    @Override
    public void displayDetails() {
        System.out.println("Farmer Name: " + name + ", ID: " + id);
        updates.forEach(System.out::println);
    }

    public void receiveUpdate(String update) {
        updates.add(update);
    }
}
