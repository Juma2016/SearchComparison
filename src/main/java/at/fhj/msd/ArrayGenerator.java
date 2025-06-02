package at.fhj.msd;

import java.util.*;

public class ArrayGenerator {
    /**
     * Generates an array of integers from 0 to size-1 in random order.
     * @param size
     * @return
     */
    public static int[] generateArray(int size) {
      List<Integer> list = new ArrayList<>();
      for (int i = 0; i < size; i++) {
          list.add(i);
      }
        Collections.shuffle(list);
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = list.get(i);
        }
        return array;
    }
    /**
     * @param array
     * @return
     */
    public static int [] sortedCopy(int[] array){
        int[] copy = Arrays.copyOf(array, array.length);
        Arrays.sort(copy);  
        return copy;
    }
}
