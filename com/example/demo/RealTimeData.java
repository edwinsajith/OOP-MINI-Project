package com.example.demo;

public class RealTimeData implements Runnable {
    private volatile boolean running = true;
    private final Agriculture agriculture;
    private final Reporting reporting;

    public RealTimeData(Agriculture agriculture) {
        this.agriculture = agriculture;
        this.reporting = new Reporting();
    }

    @Override
    public void run() {
        while (running) {
            String marketPrices = "Current Market Prices: $" + (Math.random() * 100 + 1) + " per unit";
            String weatherInfo = "Weather: " + (Math.random() > 0.5 ? "Sunny" : "Rainy");
            String report = reporting.generateReport();

            javafx.application.Platform.runLater(() -> {
                agriculture.updateMarketPrices(marketPrices);
                agriculture.updateWeatherInfo(weatherInfo);
                agriculture.updateReports(report);
            });

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void stop() {
        running = false;
    }
}
