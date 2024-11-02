package com.example.demo;

import java.util.ArrayList;
import java.util.List;

public class Farmer {
    private String name;
    private String id;
    private List<String> updates;

    public Farmer(String name, String id) {
        this.name = name;
        this.id = id;
        this.updates = new ArrayList<>();
    }

    public void register() {
        System.out.println("Farmer " + name + " with ID " + id + " is successfully registered.");
        updates.add("Welcome, " + name + "! You will receive updates on schemes, subsidies, and practices.");
    }

    public List<String> getUpdates() {
        return updates;
    }

    public void receiveUpdate(String message) {
        updates.add(message);
    }
}
