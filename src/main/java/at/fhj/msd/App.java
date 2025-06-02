package at.fhj.msd;

public class App {
  
 public static void main(String[] args) {
        int[] sizes = {100, 1000, 10000, 100000};
        int runs = 2;
        StatsCollector stats = new StatsCollector();

        for (int size : sizes) {
            int[] unsorted = ArrayGenerator.generateArray(size);
            int[] sorted = ArrayGenerator.sortedCopy(unsorted);
            System.out.println("\nTesting array of size: " + size);

            for (int i = 0; i < runs; i++) {
                long start = System.nanoTime();
                for (int x = 1; x <= size; x++) SearchAlgorithms.linearSearch(unsorted, x);
                long end = System.nanoTime();
                stats.record("LinearSearch(" + size + ")", end - start);

                start = System.nanoTime();
                for (int x = 1; x <= size; x++) SearchAlgorithms.binarySearch(sorted, x);
                end = System.nanoTime();
                stats.record("BinarySearch(" + size + ")", end - start);

                start = System.nanoTime();
                for (int x = 1; x <= size; x++) SearchAlgorithms.interpolationSearch(sorted, x);
                end = System.nanoTime();
                stats.record("InterpolationSearch(" + size + ")", end - start);

                start = System.nanoTime();
                for (int x = 1; x <= size; x++) SearchAlgorithms.quadraticBinarySearch(sorted, x);
                end = System.nanoTime();
                stats.record("QuadraticBinarySearch(" + size + ")", end - start);
            }

            stats.printStatistics("LinearSearch(" + size + ")");
            stats.printStatistics("BinarySearch(" + size + ")");
            stats.printStatistics("InterpolationSearch(" + size + ")");
            stats.printStatistics("QuadraticBinarySearch(" + size + ")");
        
        }
    }
}
