/** Array based list.
 *  @author Josh Hug
 */

public class AList {
    private int MAX_SIZE = 100;
    private int[] a = new int[MAX_SIZE];
    private int size = 0;
    /** Creates an empty list. */
    public AList() {
    }

    /** Inserts X into the back of the list. */
    public void addLast(int x) {
        if(size == MAX_SIZE) {
            MAX_SIZE = 10 * MAX_SIZE;
            int[] b = new int[MAX_SIZE];
            for(int i = 0; i < a.length; i ++) {
                b[i] = a[i];
            }
            this.a = b;
        }
        a[size] = x;
        size += 1;
    }

    /** Returns the item from the back of the list. */
    public int getLast() {
        return a[size - 1];
    }
    /** Gets the ith item in the list (0 is the front). */
    public int get(int i) {
        return a[i];
    }

    /** Returns the number of items in the list. */
    public int size() {
        return this.size;
    }

    /** Deletes item from back of the list and
     * returns deleted item. */
    public int removeLast() {
        size -= 1;
        return a[size];
    }
} 