package at.fhj.msd;

public class App {
  
   public static void main(String[] args) {
        int[] sizes = {100, 1000, 10000, 100000};
        StatsCollector stats = new StatsCollector();

        for (int size : sizes) {
            int[] unsorted = ArrayGenerator.generateArray(size);
            int[] sorted = ArrayGenerator.sortedCopy(unsorted);
            System.out.println("\nTesting array of size: " + size);

            long start = System.nanoTime();
            for (int j = 0; j < 1000; j++) {
            int x = (int)(Math.random() * size) + 1;
            SearchAlgorithms.linearSearch(unsorted, x);
}

            long end = System.nanoTime();
            stats.record("LinearSearch(" + size + ")", end - start);

            start = System.nanoTime();
            for (int j = 0; j < 1000; j++) {
            int x = (int)(Math.random() * size) + 1;
             SearchAlgorithms.linearSearch(unsorted, x);
}

            end = System.nanoTime();
            stats.record("BinarySearch(" + size + ")", end - start);

            start = System.nanoTime();
            for (int j = 0; j < 1000; j++) {
            int x = (int)(Math.random() * size) + 1;
            SearchAlgorithms.linearSearch(unsorted, x);
}

            end = System.nanoTime();
            stats.record("InterpolationSearch(" + size + ")", end - start);

            start = System.nanoTime();
            for (int j = 0; j < 1000; j++) {
            int x = (int)(Math.random() * size) + 1;
            SearchAlgorithms.linearSearch(unsorted, x);
}

            end = System.nanoTime();
            stats.record("QuadraticBinarySearch(" + size + ")", end - start);

            stats.printStatistics("LinearSearch(" + size + ")");
            stats.printStatistics("BinarySearch(" + size + ")");
            stats.printStatistics("InterpolationSearch(" + size + ")");
            stats.printStatistics("QuadraticBinarySearch(" + size + ")");
        }
    }
}
