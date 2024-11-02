

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CommunicationApp extends Application {
    private TextArea communicationArea;
    private TextField messageField;
    private Communication communication = new Communication();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Communication");

        communicationArea = new TextArea();
        communicationArea.setEditable(false);
        messageField = new TextField();

        Button sendButton = new Button("Send Message");
        sendButton.setOnAction(e -> sendMessage("Farmer", "Government", messageField.getText()));

        VBox layout = new VBox(10, communicationArea, messageField, sendButton);
        layout.setPadding(new Insets(15));

        primaryStage.setScene(new Scene(layout, 400, 300));
        primaryStage.show();
    }

    private void sendMessage(String sender, String recipient, String content) {
        if (!content.isEmpty()) {
            communication.sendMessage(sender, recipient, content);
            communicationArea.appendText(sender + ": " + content + "\n");
            messageField.clear();
            
            // Simulate a government response after the farmer sends a message
            simulateGovernmentResponse();
        }
    }

    private void simulateGovernmentResponse() {
        // You could randomize responses or use specific responses for each message
        String governmentResponse = "Government: Thank you for your message. We are here to help!";
        communicationArea.appendText(governmentResponse + "\n");
    }

    public static void main(String[] args) {
        launch(args);
    }
}

class Communication {
    public void sendMessage(String sender, String recipient, String content) {
        // Simulate message storage or logging if needed
    }
}
