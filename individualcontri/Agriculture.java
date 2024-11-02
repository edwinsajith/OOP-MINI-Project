import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

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

// Interface for Subsidy and Resource Management with Generics
interface Manageable<T> {
    void addResource(T resource);
    void displayResources();
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

// Main JavaFX application class
public class Agriculture extends Application {
    private ResourceManager<String> resourceManager = new ResourceManager<>();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Agriculture Management System");

        // Farmer Registration Section
        Label nameLabel = new Label("Name:");
        TextField nameField = new TextField();
        Label idLabel = new Label("ID:");
        TextField idField = new TextField();
        Button registerButton = new Button("Register Farmer");
        TextArea outputArea = new TextArea();

        registerButton.setOnAction(e -> {
            String name = nameField.getText();
            String id = idField.getText();

            try {
                if (name.isEmpty() || id.isEmpty()) {
                    throw new IllegalArgumentException("Name and ID cannot be empty.");
                }

                Farmer farmer = new Farmer(name, id);
                farmer.register();
                farmer.receiveUpdate("You are now registered to receive personalized updates.");
                outputArea.appendText("Farmer registered: " + name + "\n");

                nameField.clear();
                idField.clear();
            } catch (Exception ex) {
                outputArea.appendText("Error: " + ex.getMessage() + "\n");
            }
        });

        // Resource Management Section
        Label resourceLabel = new Label("Resource:");
        TextField resourceField = new TextField();
        Button addResourceButton = new Button("Add Resource");

        addResourceButton.setOnAction(e -> {
            String resource = resourceField.getText();

            try {
                if (resource.isEmpty()) {
                    throw new IllegalArgumentException("Resource cannot be empty.");
                }

                resourceManager.addResource(resource);
                outputArea.appendText("Resource added: " + resource + "\n");
                resourceField.clear();
            } catch (Exception ex) {
                outputArea.appendText("Error: " + ex.getMessage() + "\n");
            }
        });

        // Layout Setup
        GridPane grid = new GridPane();
        grid.setStyle("-fx-background-color: Lightblue");
        grid.setPadding(new Insets(10));
        grid.setVgap(8);
        grid.setHgap(10);

        grid.add(nameLabel, 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(idLabel, 0, 1);
        grid.add(idField, 1, 1);
        grid.add(registerButton, 1, 2);

        grid.add(resourceLabel, 0, 3);
        grid.add(resourceField, 1, 3);
        grid.add(addResourceButton, 1, 4);

        grid.add(outputArea, 0, 5, 2, 1);

        Scene scene = new Scene(grid, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}