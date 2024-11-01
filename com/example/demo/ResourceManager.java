// ResourceManager.java
package com.example.demo;

import java.util.ArrayList;
import java.util.List;

// Interface for Subsidy and Resource Management with Generics
interface Manageable<T> {
    void addResource(T resource);
    void displayResources();
}

// Generic ResourceManager class implementing Manageable for resource management
class ResourceManager<T> implements Manageable<T> {
    private List<T> resources;

    public ResourceManager() {
        this.resources = new ArrayList<>();
    }

    @Override
    public void addResource(T resource) {
        resources.add(resource);
        System.out.println("Resource added successfully: " + resource);
    }

    @Override
    public void displayResources() {
        System.out.println("Current Resources:");
        for (T resource : resources) {
            System.out.println(resource);
        }
    }
}
