

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
        double percUsed = (double) size / (double) MAX_SIZE;
        if (percUsed < LOWER_CAP) {
            System.out.println("Yes is low");
            return true;
        } else {
            return false;
        }
    }

    public T removeFirst() {
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
        if (item.length == 0) {
            return true;
        }
        return false;
    }

    public T get(int index) {
        index = (index + nextFirst + 1) % MAX_SIZE;
        return this.item[index];
    }

//    public static void main(String[] args) {
//        ArrayDeque<String> a = new ArrayDeque<String>();
//        a.addFirst("2");
//        a.addFirst("1");
//        a.addLast("3");
//        a.addLast("4");
//        a.addLast("5");
//        a.addLast("6");
//        a.addLast("7");
//        a.addLast("8");
//        a.addLast("9");
//        a.addLast("10");
//
//        a.removeLast();
//        a.removeLast();
//        a.removeLast();
//        a.removeLast();
//        a.removeLast();
//        a.removeLast();
//        a.removeLast();
//
//        System.out.println(a.size());
//        a.printDeque();
//    }
}
