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

/**
     * Quadratic Binary Search - A search algorithm that combines interpolation with sqrt(n) steps
     * Works on sorted arrays, similar to binary search but with different partitioning
     * 
     * @param a the sorted array to search in
     * @param x the value to search for
     * @return index of the found element, or -1 if not found
     */
    
      public static int quadraticBinarySearch(int[] A, int x) {
    int from = 0;
    int to = A.length - 1;
    int remainingTries = 1000;
    
    while (remainingTries > 0 && from >= 0 && to < A.length && from <= to) {
        int n = to - from + 1;
        
        if (A[from] < A[to]) {
            // Interpolation formula to estimate position
            int denominator = A[to] - A[from];
            int t;
            if (denominator == 0) {
                t = (from + to) / 2;
            } else {
                t = from + (int)Math.floor((to - from) * ((double)(x - A[from]) / denominator)); 
            }
            // Ensure t stays within bounds
            t = Math.max(from, Math.min(to, t));
            
            if (x == A[t]) {
                return t;
            } else if (x < A[t]) {
                // Jump back in steps of sqrt(n)
                int step = (int)Math.floor(Math.sqrt(n));
                int newT = t;
                while (newT >= from && x < A[newT] && remainingTries-- > 0) {
                    newT -= step;
                }
                newT = Math.max(from, newT);
                to = Math.min(t + step - 1, to);
                from = newT;
            } else {
                // Jump forward in steps of sqrt(n)
                int step = (int)Math.floor(Math.sqrt(n));
                int newT = t;
                while (newT <= to && x > A[newT] && remainingTries-- > 0) {
                    newT += step;
                }
                newT = Math.min(to, newT);
                from = Math.max(t - step + 1, from);
                to = newT;
            }
        } else {
            // All elements in the range are equal
            if (x == A[from]) {
                return from;
            } else {
                return -1;
            }
        }
    }
    return -1;
    
        }
}




    