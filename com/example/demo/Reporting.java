package com.example.demo;

import java.util.Random;

public class Reporting {
    private Random random = new Random();

    public String generateReport() {
        // Generate some mock data for the report
        int totalCrops = random.nextInt(1000) + 100; // Random total crops between 100 and 1100
        int totalResourcesDistributed = random.nextInt(500) + 50; // Random resources between 50 and 550
        double averageYield = 5 + random.nextDouble() * 15; // Random average yield between 5 and 20 per acre
        
        // Format the report
        return String.format("Report:\n- Total Crops Monitored: %d\n- Total Resources Distributed: %d\n- Average Yield per Acre: %.2f\n", 
                             totalCrops, totalResourcesDistributed, averageYield);
    }
}
