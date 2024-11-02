package com.example.demo;

import java.util.Random;

public class Reporting {
    private final Random random = new Random();

    public String generateReport() {
        int totalCrops = random.nextInt(1000) + 100;
        int totalResourcesDistributed = random.nextInt(500) + 50;
        double averageYield = 5 + random.nextDouble() * 15;

        return String.format("Report:\n- Total Crops Monitored: %d\n- Total Resources Distributed: %d\n- Average Yield per Acre: %.2f\n", 
                             totalCrops, totalResourcesDistributed, averageYield);
    }
}
