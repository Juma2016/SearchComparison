package at.fhj.msd;

import java.io.IOException;

public class App {
  
   public static void main(String[] args) {
        int[] sizes = {100, 1000, 10000, 100000};
        StatsCollector stats = new StatsCollector();

       // Run tests 5 times for each size
        for (int run = 1; run <= 5; run++) {
            System.out.println("\nTest run " + run + " von 5");

        for (int size : sizes) {
            int[] unsorted = ArrayGenerator.generateArray(size);
            int[] sorted = ArrayGenerator.sortedCopy(unsorted);
            System.out.println("\nTesting array of size: " + size);

            // Test LinearSearch (on unsorted array)
            long start = System.nanoTime();
            for (int j = 0; j < 1000; j++) {
            int x = (int)(Math.random() * size) + 1;
            SearchAlgorithms.linearSearch(unsorted, x);
            }

            long end = System.nanoTime();
            stats.record("LinearSearch(" + size + ")", end - start);

            // Test BinarySearch (on sorted array)
            start = System.nanoTime();
            for (int j = 0; j < 1000; j++) {
            int x = (int)(Math.random() * size) + 1;
             SearchAlgorithms.binarySearch(sorted, x);
            }

            end = System.nanoTime();
            stats.record("BinarySearch(" + size + ")", end - start);

           // Test InterpolationSearch (on sorted array) 
            start = System.nanoTime();
            for (int j = 0; j < 1000; j++) {
            int x = (int)(Math.random() * size) + 1;
            SearchAlgorithms.interpolationSearch(sorted, x);
}

            end = System.nanoTime();
            stats.record("InterpolationSearch(" + size + ")", end - start);

           //Test QuadraticBinarySearch (on sorted array)
            start = System.nanoTime();
            for (int j = 0; j < 1000; j++) {
            int x = (int)(Math.random() * size) + 1;
            SearchAlgorithms.quadraticBinarySearch(sorted, x);
            }


            end = System.nanoTime();
            stats.record("QuadraticBinarySearch(" + size + ")", end - start);

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
