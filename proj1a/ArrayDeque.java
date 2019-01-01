

public class ArrayDeque<T> {
    private T[] item;
    private int nextFirst;
    private int nextLast;
    private int size;
    private static int MAX_SIZE = 8;
    private static double LOWER_CAP = 0.25;

    public ArrayDeque() {
        item = (T[]) new Object[MAX_SIZE];
        nextFirst = 0;
        nextLast = 1;
    }

    private int getNext(int index) {
        if (index < 0) {
            return index + MAX_SIZE;
        }
        return index % MAX_SIZE;
    }

    private int getNextFirst(int index) {
        return getNext(index - 1);
    }

    private int getNextLast(int index) {
        return getNext(index + 1);
    }

    private void resize(int capasity) {
        T[] tmp = (T[]) new Object[MAX_SIZE + capasity];
        for (int i = 0; i < size; i++) {
            tmp[i] = get(i);
        }

        this.item = tmp;
        MAX_SIZE *= 2;

        this.nextFirst = getNextFirst(0);
        this.nextLast = size;
    }

    public void addFirst(T x) {
        if (nextFirst == nextLast) {
            resize(MAX_SIZE);
        }
        this.item[nextFirst] = x;
        nextFirst = getNextFirst(nextFirst);
        size += 1;
    }

    public void addLast(T x) {
        if (nextFirst == nextLast) {
            resize(MAX_SIZE);
        }
        this.item[nextLast] = x;
        nextLast = getNextLast(nextLast);
        size += 1;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(this.get(i) + " ");
        }
        System.out.println();
    }

    private void shrunk() {
        T[] tmp = (T[]) new Object[MAX_SIZE / 2];
        for (int i = 0; i < size; i++) {
            tmp[i] = get(i);
        }

        this.item = tmp;
        MAX_SIZE /= 2;

        nextFirst = getNextFirst(0);
        nextLast = size;
    }

    private boolean isLowCapisity() {
        if (this.item.length > 16) {
            double percUsed = (double) size / (double) MAX_SIZE;
            return percUsed < LOWER_CAP;
        } else {
            return false;
        }
    }

    public T removeFirst() {
        if (isEmpty()) {
            return (T) new Integer(19);
        }
        if (isLowCapisity()) {
            shrunk();
        }
        nextFirst = getNext(nextFirst + 1);
        T result = this.item[nextFirst];
        this.item[nextFirst] = null;
        size -= 1;
        return result;
    }

    public T removeLast() {
        if (isEmpty()) {
            return (T) new Integer(19);
        }
        if (isLowCapisity()) {
            shrunk();
        }
        nextLast = getNext(nextLast - 1);
        T result = this.item[nextLast];
        this.item[nextLast] = null;
        size -= 1;
        return result;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public T get(int index) {
        index = (index + nextFirst + 1) % MAX_SIZE;
        return this.item[index];
    }

//    public static void main(String[] args) {
//        ArrayDeque<Integer> a = new ArrayDeque<Integer>();
//        for (int i = 0; i < 50; i++) {
//            a.addFirst(i);
//        }
//        System.out.println(a.size());
//
//        for (int i = 0; i < 50 - 1; i++) {
//            a.removeLast();
//        }
//        System.out.println(a.size());
//        System.out.println(a.isEmpty());
//    }
}
