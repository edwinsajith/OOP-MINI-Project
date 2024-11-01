package com.example.demo;

public class RealTimeData implements Runnable {
    private volatile boolean running = true;
    private Agriculture agriculture;
    private Reporting reporting;

    public RealTimeData(Agriculture agriculture) {
        this.agriculture = agriculture;
        this.reporting = new Reporting();
    }

    @Override
    public void run() {
        while (running) {
            // Simulate fetching market prices and weather data
            String marketPrices = "Current Market Prices: $" + (Math.random() * 100 + 1) + " per unit";
            String weatherInfo = "Weather: " + (Math.random() > 0.5 ? "Sunny" : "Rainy");

            // Generate a report and update the UI
            String report = reporting.generateReport();

            // Update the UI on the JavaFX Application Thread
            javafx.application.Platform.runLater(() -> {
                agriculture.updateMarketPrices(marketPrices);
                agriculture.updateWeatherInfo(weatherInfo);
                agriculture.updateReports(report);
            });

            try {
                Thread.sleep(5000); // Sleep for 5 seconds
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void stop() {
        running = false;
    }
}
