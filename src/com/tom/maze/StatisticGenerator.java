package com.tom.maze;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class StatisticGenerator {
    private final Map<String, MazeBuilder> algorithms;

    public StatisticGenerator(Map<String, MazeBuilder> algorithms) {
        this.algorithms = algorithms;
    }

    public void writeToFile() {
        this.writeToFile("stats2.txt");
    }

    public void writeToFile(String filename) {
        try (FileWriter file = new FileWriter(filename)) {
            for (String algo : algorithms.keySet()) {
                file.write(algo + "\n");
                file.write(generateStats(algo));
            }
        } catch (IOException e) {
            System.out.println(e.toString());
            e.getStackTrace();
        }
    }

    private String generateStats(String algo) {
        String plotValues = "";
        TimeIt timeIt_;
        for (int size = 20; size < 100; size += 10) {
            timeIt_ = new TimeIt(algorithms.get(algo), size, size);
            float[] times = timeIt_.timeIt(100, 3);

            float sum = 0.0f;
            int count = 0;
            for (float time : times) {
                count++;
                sum += time;
            }
            float average = sum / (float) count;

            plotValues += size + " " + average + "\n";
        }
        return plotValues + "\n";
    }
}
