package at.fhj.msd;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import java.io.IOException;
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
   /**
 * Generates a PDF report containing performance statistics for search algorithms.
 * The report includes minimum, maximum and average execution times for each algorithm
 * across different array sizes. The data is presented in a tabular format with
 * proper headers and automatic pagination when content exceeds page limits.
 * 
 * @param filePath The absolute path where the PDF file should be saved, including
 *                 the filename and .pdf extension (e.g., "/reports/search_stats.pdf")
 * @throws IOException If there's an error creating or writing to the PDF file.
 *                     This includes file permission issues, invalid paths, or
 *                     PDF generation errors.
 * 
 * @implNote The method organizes data by array sizes (100, 1000, 10000, 100000)
 *           and algorithms (LinearSearch, BinarySearch, InterpolationSearch, QuadraticBinarySearch).
 *           Each algorithm/size combination shows statistics from multiple test runs.
 *           The PDF includes:
 *           - A title and subtitle
 *           - Table headers (Algorithm, Array Size, Min, Max, Avg)
 *           - Automatically generated new pages when content reaches bottom margin
 *           - Consistent formatting with Helvetica fonts
 */
public void generatePDFReport(String filePath) throws IOException {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);
              PDPageContentStream contentStream =null;
            try {
              contentStream = new PDPageContentStream(document, page);
                // Set up fonts
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 14);
                
                // Title
                contentStream.beginText();
                contentStream.newLineAtOffset(100, 700);
                contentStream.showText("Statistical evaluation of the search algorithms");

                contentStream.endText();

                 // Table header
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 10);
                contentStream.beginText();
                contentStream.newLineAtOffset(100, 650);
                contentStream.showText("Algorithmus");
                contentStream.newLineAtOffset(110, 0);
                contentStream.showText("Size");
                contentStream.newLineAtOffset(110, 0);
                contentStream.showText("Min (ns)");
                contentStream.newLineAtOffset(90, 0);
                contentStream.showText("Max (ns)");
                contentStream.newLineAtOffset(90, 0);
                contentStream.showText("Avg (ns)");
                contentStream.endText();

                // Table content
                contentStream.setFont(PDType1Font.HELVETICA, 10);
                int yPosition = 630;
                
                // Process all recorded data 
                  int[] sizes = {100, 1000, 10000, 100000};
            String[] algorithms = {"LinearSearch", "BinarySearch", "InterpolationSearch", "QuadraticBinarySearch"};
                for (int size : sizes) {
                for (String algorithm : algorithms) {
                    String key = algorithm + "(" + size + ")";
                    
                    List<Long> times = stats.get(key);
                    if (times == null || times.isEmpty()) continue;
                    long min = Collections.min(times);
                    long max = Collections.max(times);
                    double avg = times.stream().mapToLong(Long::longValue).average().orElse(0);

                    contentStream.beginText();
                    contentStream.newLineAtOffset(100, yPosition);
                    contentStream.showText(algorithm);
                    contentStream.newLineAtOffset(150, 0);
                    contentStream.showText(String.valueOf(size));

                    contentStream.newLineAtOffset(80, 0);
                    contentStream.showText(String.valueOf(min));

                    contentStream.newLineAtOffset(80, 0);
                    contentStream.showText(String.valueOf(max));

                    contentStream.newLineAtOffset(80, 0);
                    contentStream.showText(String.format("%.2f", avg));

                    contentStream.endText();

                    yPosition -= 20;
                    //Add new page if running out of space
                    if (yPosition < 50) {  
                        contentStream.close();
                        page = new PDPage();
                        document.addPage(page);

                        contentStream = new PDPageContentStream(document, page);

                        contentStream.setFont(PDType1Font.HELVETICA, 10);
                        yPosition = 700;
                    // Redraw header on new page
                        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 10);
                        contentStream.beginText();
                        contentStream.newLineAtOffset(100, yPosition);
                        contentStream.showText("Algorithm");
                        contentStream.newLineAtOffset(150, 0);
                        contentStream.showText("Size");
                        contentStream.newLineAtOffset(80, 0);
                        contentStream.showText("Min (ns)");
                        contentStream.newLineAtOffset(80, 0);
                        contentStream.showText("Max (ns)");
                        contentStream.newLineAtOffset(80, 0);
                        contentStream.showText("Avg (ns)");
                        contentStream.endText();
                        yPosition -= 20;
                    }
                }
            }
        } finally {
            if (contentStream != null) {
                contentStream.close();
            }
        }

        document.save(filePath);
        System.out.println("PDF report generated successfully: " + filePath);
        }
    }
}
