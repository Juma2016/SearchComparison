package at.fhj.msd;

import java.util.*;
public class StatsCollector {
    private final Map<String, List<Long>> stats = new HashMap<>();
    /**
     * 
     * @param key
     * @param value
     */
   public void record(String key, long value) {
        stats.computeIfAbsent(key, k -> new ArrayList<>()).add(value);
    }
    /**
     * Prints the statistics for a specific algorithm.
     * @param algorithm The name of the algorithm to print statistics for.
     */
    public void printStatistics(String algorithm) {
        List<Long> data = stats.get(algorithm);
        if (data == null || data.isEmpty()) return;
        long min = Collections.min(data);
        long max = Collections.max(data);
        double avg = data.stream().mapToLong(Long::longValue).average().orElse(0);
        System.out.printf("%s -> Min: %d ms | Max: %d ms | Avg: %.2f ms\n", algorithm, min, max, avg);
    }
    /**
     * Prints the statistics for all recorded algorithms.
     * This method iterates through all keys in the stats map and prints their statistics.
     */
    public void printAllStatistics() {
        for (String key : stats.keySet()) {
            printStatistics(key);
        }
    }
}
