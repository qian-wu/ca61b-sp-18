import java.util.HashMap;
import java.util.Map;

/**
 * Class with 2 ways of doing Counting sort, one naive way and one "better" way
 *
 * @author Akhil Batra, Alexander Hwang
 *
 **/
public class CountingSort {
    /**
     * Counting sort on the given int array. Returns a sorted version of the array.
     * Does not touch original array (non-destructive method).
     * DISCLAIMER: this method does not always work, find a case where it fails
     *
     * @param arr int array that will be sorted
     * @return the sorted array
     */
    public static int[] naiveCountingSort(int[] arr) {
        // find max
        int max = Integer.MIN_VALUE;
        for (int i : arr) {
            max = max > i ? max : i;
        }

        // gather all the counts for each value
        int[] counts = new int[max + 1];
        for (int i : arr) {
            counts[i]++;
        }

        // when we're dealing with ints, we can just put each value
        // count number of times into the new array
        int[] sorted = new int[arr.length];
        int k = 0;
        for (int i = 0; i < counts.length; i += 1) {
            for (int j = 0; j < counts[i]; j += 1, k += 1) {
                sorted[k] = i;
            }
        }

        // however, below is a more proper, generalized implementation of
        // counting sort that uses start position calculation
        int[] starts = new int[max + 1];
        int pos = 0;
        for (int i = 0; i < starts.length; i += 1) {
            starts[i] = pos;
            pos += counts[i];
        }

        int[] sorted2 = new int[arr.length];
        for (int i = 0; i < arr.length; i += 1) {
            int item = arr[i];
            int place = starts[item];
            sorted2[place] = item;
            starts[item] += 1;
        }

        // return the sorted array
        return sorted;
    }

    private static int getKeyIndex(int a[], int key) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] == key) return i;
        }
        return -1;
    }

    /**
     * Counting sort on the given int array, must work even with negative numbers.
     * Note, this code does not need to work for ranges of numbers greater
     * than 2 billion.
     * Does not touch original array (non-destructive method).
     *
     * @param arr int array that will be sorted
     */
    public static int[] betterCountingSort(int[] arr) {
        //get min and max
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int i : arr) {
            if (i < min) min = i;
            if (i > max) max = i;
        }

        //count keys
        int[] keys = new int[max - min + 1];

        int start = min;
        for (int i = 0; i < keys.length; i++) {
            keys[i] = start++;
        }

        int[] values = new int[max - min + 1];

        for (int i : arr) {
            int keyIdx = getKeyIndex(keys, i);
            values[keyIdx] += 1;
        }

        int[] count = new int[max - min + 1];
        int[] aux = new int[arr.length];

        //count rate
        for (int i = 1; i < count.length; i++) {
            count[i] = values[i - 1];
            count[i] = count[i - 1] + count[i];
        }
        System.out.println();
        //trans rate to index
        for (int i = 0; i < aux.length; i++) {
            aux[count[getKeyIndex(keys, arr[i])]++] = arr[i];
        }

        return aux;
    }
}
