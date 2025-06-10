package at.fhj.msd;

import java.io.IOException;

public class App {
  
   public static void main(String[] args) {
        int[] sizes = {100, 1000, 10000, 100000};
        StatsCollector stats = new StatsCollector(); // object to collect statistics

       // Run tests 5 times for each size
        for (int run = 1; run <= 5; run++) {
            System.out.println("\nTest run " + run + " von 5");

            // Generate random arrays and test search algorithms durch sorted or unsorted arrays
            // Loop through each size
        for (int size : sizes) {
            int[] unsorted = ArrayGenerator.generateArray(size);
            int[] sorted = ArrayGenerator.sortedCopy(unsorted);
            System.out.println("\nTesting array of size: " + size);

            // Test LinearSearch (on unsorted array)
            long totalTime = 0;
            for (int j = 0; j < 1000; j++) {
            int x = (int)(Math.random() * size) + 1;
            long start = System.nanoTime();
            SearchAlgorithms.linearSearch(unsorted, x);
            long end = System.nanoTime();
            totalTime += (end-start);
            }

            stats.record("LinearSearch(" + size + ")", (totalTime/1000));

            // Test BinarySearch (on sorted array)
           totalTime = 0;
            for (int j = 0; j < 1000; j++) {
            int x = (int)(Math.random() * size) + 1;
            long start = System.nanoTime();
            SearchAlgorithms.binarySearch(sorted, x);
            long end = System.nanoTime();
            totalTime += (end-start);
            }
  
            stats.record("BinarySearch(" + size + ")", totalTime/1000);

           // Test InterpolationSearch (on sorted array) 
            for (int j = 0; j < 1000; j++) {
            int x = (int)(Math.random() * size) + 1;
            long start = System.nanoTime();
            SearchAlgorithms.interpolationSearch(sorted, x);
            long end = System.nanoTime();
            totalTime += (end-start);       
            }
           
            stats.record("InterpolationSearch(" + size + ")", totalTime/1000);

           //Test QuadraticBinarySearch (on sorted array)
           
            for (int j = 0; j < 1000; j++) {
            int x = (int)(Math.random() * size) + 1;
            long start = System.nanoTime();
            SearchAlgorithms.quadraticBinarySearch(sorted, x);
            long end = System.nanoTime(); 
            totalTime += (end-start);    
            }
           
            stats.record("QuadraticBinarySearch(" + size + ")", totalTime/1000);

            // Print statistics for each algorithm
            stats.printStatistics("LinearSearch(" + size + ")");
            stats.printStatistics("BinarySearch(" + size + ")");
            stats.printStatistics("InterpolationSearch(" + size + ")");
            stats.printStatistics("QuadraticBinarySearch(" + size + ")");
        }
    }
        // Generate PDF report
        try {
            String filePath = System.getProperty("user.dir") + "/TestResult.pdf";
            stats.generatePDFReport(filePath);
        } catch (IOException e) {
            System.err.println("Error generating PDF report: " + e.getMessage());
    }
  }
}
