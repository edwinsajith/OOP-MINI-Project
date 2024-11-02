package com.example.demo;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Agriculture extends Application {
    private ResourceManager<String> resourceManager = new ResourceManager<>();
    private RealTimeData realTimeData;
    private Communication communication = new Communication(); 

    private TextArea marketPricesArea;
    private TextArea weatherInfoArea;
    private TextArea reportArea;
    private TextArea updatesArea;

    private TextArea communicationArea;
    private TextField messageField;

    public void start(Stage primaryStage) {
        primaryStage.setTitle("Agriculture Management System");

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
                updatesArea.appendText(String.join("\n", farmer.getUpdates()) + "\n");
                outputArea.appendText("Farmer registered: " + name + "\n");

                nameField.clear();
                idField.clear();
            } catch (Exception ex) {
                outputArea.appendText("Error: " + ex.getMessage() + "\n");
            }
        });

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

        Label marketLabel = new Label("Market Prices:");
        marketPricesArea = new TextArea();
        marketPricesArea.setEditable(false);

        Label weatherLabel = new Label("Weather Info:");
        weatherInfoArea = new TextArea();
        weatherInfoArea.setEditable(false);

        Label reportLabel = new Label("Reports:");
        reportArea = new TextArea();
        reportArea.setEditable(false);
        reportArea.setPrefHeight(150);

        Label updatesLabel = new Label("Updates:");
        updatesArea = new TextArea();
        updatesArea.setEditable(false);

        Label messageLabel = new Label("Message:");
        messageField = new TextField();
        Button sendMessageButton = new Button("Send Message");
        communicationArea = new TextArea();
        communicationArea.setEditable(false);

        sendMessageButton.setOnAction(e -> {
            String messageContent = messageField.getText();
            String sender = "Farmer"; 
            String recipient = "Government"; 

            if (!messageContent.isEmpty()) {
                communication.sendMessage(sender, recipient, messageContent);
                communicationArea.appendText("You: " + messageContent + "\n");
                messageField.clear();
                communicationArea.appendText("Gov: Thank you for your message!\n");
            }
        });

        GridPane grid = new GridPane();
        grid.setStyle("-fx-background-color: Yellow");
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

        grid.add(marketLabel, 0, 5);
        grid.add(marketPricesArea, 0, 6, 2, 1);

        grid.add(weatherLabel, 0, 7);
        grid.add(weatherInfoArea, 0, 8, 2, 1);

        grid.add(reportLabel, 0, 9);
        grid.add(reportArea, 0, 10, 2, 1);

        grid.add(updatesLabel, 0, 11);
        grid.add(updatesArea, 0, 12, 2, 1);

        grid.add(messageLabel, 0, 13);
        grid.add(messageField, 1, 13);
        grid.add(sendMessageButton, 1, 14);
        grid.add(communicationArea, 0, 15, 2, 1);
        
        grid.add(outputArea, 0, 16, 2, 1);

        Scene scene = new Scene(grid, 600, 800);
        primaryStage.setScene(scene);
        primaryStage.show();

        realTimeData = new RealTimeData(this);
        Thread realTimeThread = new Thread(realTimeData);
        realTimeThread.setDaemon(true); 
        realTimeThread.start();
    }

    public void updateMarketPrices(String prices) {
        marketPricesArea.setText(prices);
    }

    public void updateWeatherInfo(String weather) {
        weatherInfoArea.setText(weather);
    }

    public void updateReports(String report) {
        reportArea.setText(report); 
    }

    public static void main(String[] args) {
        launch(args);
    }
}
