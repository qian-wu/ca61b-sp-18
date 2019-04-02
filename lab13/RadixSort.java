/**
 * Class for doing Radix sort
 *
 * @author Akhil Batra, Alexander Hwang
 *
 */
public class RadixSort {
    /**
     * Does LSD radix sort on the passed in array with the following restrictions:
     * The array can only have ASCII Strings (sequence of 1 byte characters)
     * The sorting is stable and non-destructive
     * The Strings can be variable length (all Strings are not constrained to 1 length)
     *
     * @param asciis String[] that needs to be sorted
     *
     * @return String[] the sorted array
     */
    public static String[] sort(String[] asciis) {
        //get max length of arry strings
        int maxLength = 0;
        int[] lengths = new int[asciis.length];

        for (int i = 0; i < asciis.length; i++) {
            int le = asciis[i].length();
            lengths[i] = le;
            if (le > maxLength) maxLength = le;
        }

        //get all string has same length with max
        String[] aux = new String[asciis.length];
        for (int i = 0; i < lengths.length; i++) {
            aux[i] = addEmpty(asciis[i], maxLength - lengths[i]);
        }

        //send copy array to helper function
        for (int i = maxLength - 1; i >= 0; i--) {
            sortHelperLSD(aux, i);
        }
        //get all strings with out empty
        for (int i = 0; i < aux.length; i++) {
            aux[i] = removeEmpty(aux[i]);
        }
//        System.out.println();
        return aux;
    }

    private static String addEmpty(String s, int n) {
        for (int j = 0; j < n; j++) {
            s += (char) 0;
        }
        return s;
    }

    private static String removeEmpty(String s) {
        for (int i = s.length() - 1; i >= 0; i--) {
            if ((int) s.charAt(i) == 0) {
                s = s.substring(0, i);
            } else {
                break;
            }
        }
        return s;
    }

    /**
     * LSD helper method that performs a destructive counting sort the array of
     * Strings based off characters at a specific index.
     * @param asciis Input array of Strings
     * @param index The position to sort the Strings on.
     */
    private static void sortHelperLSD(String[] asciis, int index) {
        //initial count array size 255
        int[] count = new int[257 + 1];

        //count string rate
        for (int i = 0; i < asciis.length; i++) {
            count[(int) asciis[i].charAt(index) + 1]++;
        }

        //add count to index
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }
        //add string to aux
        String[] aux = new String[asciis.length];
        for (int i = 0; i < asciis.length; i++) {
            aux[i] = asciis[i];
        }

        for (int i = 0; i < asciis.length; i++) {
            asciis[count[(int) aux[i].charAt(index)]++] = aux[i];
        }
        return;
    }

    /**
     * MSD radix sort helper function that recursively calls itself to achieve the sorted array.
     * Destructive method that changes the passed in array, asciis.
     *
     * @param asciis String[] to be sorted
     * @param start int for where to start sorting in this method (includes String at start)
     * @param end int for where to end sorting in this method (does not include String at end)
     * @param index the index of the character the method is currently sorting on
     *
     **/
    private static void sortHelperMSD(String[] asciis, int start, int end, int index) {
        // Optional MSD helper method for optional MSD radix sort
        return;
    }

    public static void main(String[] args) {
        String[] s = {"ab", "cba", "a", "cdb", "bbb", "1", "100"};
        String[] x = RadixSort.sort(s);

        for (String i: x) {
            System.out.print(i + " ");
        }
    }
}
