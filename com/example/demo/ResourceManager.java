
package com.example.demo;

import java.util.ArrayList;
import java.util.List;

interface Manageable<T> {
    void addResource(T resource);
    void displayResources();
}

class ResourceManager<T> implements Manageable<T> {
    private List<T> resources;

    public ResourceManager() {
        this.resources = new ArrayList<>();
    }

    public void addResource(T resource) {
        resources.add(resource);
        System.out.println("Resource added successfully: " + resource);
    }

    public void displayResources() {
        System.out.println("Current Resources:");
        for (T resource : resources) {
            System.out.println(resource);
        }
    }
}
