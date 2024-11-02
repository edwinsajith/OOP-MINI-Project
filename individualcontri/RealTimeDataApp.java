import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label; // Add this import
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Insets;


public class RealTimeDataApp extends Application {
    private TextArea marketPricesArea;
    private TextArea weatherInfoArea;
    private volatile boolean running = true;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Real-Time Data");

        marketPricesArea = new TextArea();
        weatherInfoArea = new TextArea();
        marketPricesArea.setEditable(false);
        weatherInfoArea.setEditable(false);

        VBox layout = new VBox(10, new Label("Market Prices:"), marketPricesArea,
                               new Label("Weather Info:"), weatherInfoArea);
        layout.setPadding(new Insets(15));

        primaryStage.setScene(new Scene(layout, 400, 300));
        primaryStage.show();

        new Thread(this::fetchRealTimeData).start();
    }

    private void fetchRealTimeData() {
        while (running) {
            Platform.runLater(() -> {
                marketPricesArea.setText("Market Price: $" + (Math.random() * 100 + 1));
                weatherInfoArea.setText("Weather: " + (Math.random() > 0.5 ? "Sunny" : "Rainy"));
            });
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @Override
    public void stop() {
        running = false;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
