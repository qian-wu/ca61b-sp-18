public class ArrayDeque {
    private int[] item;
    private int nextFirst;
    private int nextLast;
    private int size;
    private static int MAX_SIZE = 8;

    public ArrayDeque() {
        item = new int[MAX_SIZE];
        nextFirst = 0;
        nextLast = 1;
    }

    private int getNext(int index) {
        if(index < 0) {
            return index + MAX_SIZE;
        }
        return index % MAX_SIZE;
    }

    public int getNextFirst(int index) {
        return getNext(index - 1);
    }

    public int getNextLast(int index) {
        return getNext(index + 1);
    }

    public void addFirst(int item) {
        if(nextFirst == nextLast) {
            System.out.println("Deque is full, can not add any more");
        }
        this.item[nextFirst] = item;
        nextFirst = getNextFirst(nextFirst);
    }

    public void addLast(int item) {
        if(nextFirst == nextLast) {
            System.out.println("Deque is full, can not add any more");
        }
        this.item[nextLast] = item;
        nextLast = getNextLast(nextLast);
    }

    public int size() {
        return size;
    }

    public void printDeque() {

    }

    public int removeFirst() {
        nextFirst = getNext(nextFirst + 1);
        int item = this.item[nextFirst];
        this.item[nextFirst] = 0;
        size -= 1;
        return item;
    }

    public int removeLast() {
        nextLast = getNext(nextLast + 1);
        int item = this.item[nextLast];
        this.item[nextLast] = 0;
        size -= 1;
        return item;
    }

    public boolean isEmpty() {
        if(item.length == 0) {
            return true;
        }
        return false;
    }

    public int get(int index) {
        index = (index + nextFirst + 1) % MAX_SIZE;
        return this.item[index];
    }

    public static void main(String[] args) {
    }
}
