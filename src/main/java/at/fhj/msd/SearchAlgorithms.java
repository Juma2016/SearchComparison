package at.fhj.msd;

public class SearchAlgorithms {
    public static int linearSearch(int[] a, int x) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] == x) return i;
        }
        return -1;
    }

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

    public static int interpolationSearch(int[] a, int x) {
        int low = 0, high = a.length - 1;
        while (low <= high && x >= a[low] && x <= a[high]) {
            int pos = low + ((x - a[low]) * (high - low)) / (a[high] - a[low]);
            if (pos < 0 || pos >= a.length) break;
            if (a[pos] == x) return pos;
            if (a[pos] < x) low = pos + 1;
            else high = pos - 1;
        }
        return -1;
    }

    public static int quadraticBinarySearch(int[] a, int x) {
        int left = 0, right = a.length - 1;
        while (left <= right) {
            int mid = left + (right - left) * (right - left) / (a.length * a.length / 4);
            if (mid >= a.length) mid = (left + right) / 2;
            if (a[mid] == x) return mid;
            if (a[mid] < x) left = mid + 1;
            else right = mid - 1;
        }
        return -1;
    }
}