package at.fhj.msd;

// leicht aber langsam bei größeren Arrays
public class SearchAlgorithms {
    public static int linearSearch(int[] a, int x) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] == x) return i;
        }
        return -1;
    }


    // schnell, aber nur für sortierte Arrays
    // funktioniert nicht bei unsortierten Arrays
    // O(log n) Zeitkomplexität
    // O(1) Speicherkomplexität
    // O(n) im Worst Case, wenn das Element nicht gefunden wird

    public static int binarySearch(int[] a, int x) {
        int left = 0, right = a.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (a[mid] == x) return mid;
            if (a[mid] < x) left = mid + 1;
            else right = mid - 1;
        }
        return -1;
    }

    /*
     * Interpolation Search
     * This algorithm is an improved version of binary search that works on sorted arrays.
     * It estimates the position of the target value based on the values at the low and high indices.
     * It is particularly efficient for uniformly distributed data.
     * Time Complexity: O(log log n) on average, O(n) in the worst case.
     * Space Complexity: O(1)
     */

    public static int interpolationSearch(int[] a, int x) {
    int low = 0, high = a.length - 1;
    
    while (low <= high && x >= a[low] && x <= a[high]) {
        
        // Special case: All elements in the range are equal
        if (a[low] == a[high]) {
            return (a[low] == x) ? low : -1;
        }

        // Ensure that the division does not go through zero
        if (a[high] == a[low]) {
            break;
        }

       // Interpolation formula with overflow protection (long)
        int pos = low + (int)(((long)(x - a[low]) * (long)(high - low)) / (a[high] - a[low]));

        // Check boundaries
        if (pos < low || pos > high) {
            pos = (low + high) / 2;  
        }
        if (a[pos] == x) {
            return pos;
        } else if (a[pos] < x) {
            low = pos + 1;
        } else {
            high = pos - 1;
        }
    }
    return -1;
}
    
    public static int quadraticBinarySearch(int[] a, int x) {
    int left = 0, right = a.length - 1;

    // To prevent endless loops
    int maxTries = 1000; 

    while (left <= right && maxTries-- > 0) {
        int range = right - left;
        int denominator = (a.length * a.length) / 4; // 4 is a constant to avoid division by zero and to scale the range

        if (denominator == 0) denominator = 1; // if denominator is zero, we set it to 1 (selten case)

        int mid = left + (range * range) / denominator;

        // If mid is out of bounds, we use normal arithmetic.
        // This can happen if the array is small or if the range is very small.
        // if rechnerischer Mittelwert außerhalb des Bereichs liegt, dann setze mid auf den Mittelwert(binary search)   

        if (mid < left || mid > right) {
            mid = left + (right - left) / 2;
        }
        // Check if the middle element is the target

        if (a[mid] == x) return mid;
        if (a[mid] < x) left = mid + 1;
        else right = mid - 1;
    }
    
    return -1; 
}
}
