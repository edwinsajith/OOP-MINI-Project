import javafx.scene.control.Label;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Random;

public class ReportingApp extends Application {
    private TextArea reportArea;
    private Reporting reporting = new Reporting();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Monitoring and Reporting");

        reportArea = new TextArea();
        reportArea.setEditable(false);

        reportArea.setText(reporting.generateReport());

        VBox layout = new VBox(10, new Label("Report:"), reportArea);
        layout.setPadding(new Insets(15));

        primaryStage.setScene(new Scene(layout, 400, 300));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

class Reporting {
    private Random random = new Random();

    public String generateReport() {
        int totalCrops = random.nextInt(1000) + 100;
        int totalResources = random.nextInt(500) + 50;
        double averageYield = 5 + random.nextDouble() * 15;

        return "Total Crops Monitored: " + totalCrops + "\n" +
               "Total Resources Distributed: " + totalResources + "\n" +
               "Average Yield per Acre: " + String.format("%.2f", averageYield);
    }
}
