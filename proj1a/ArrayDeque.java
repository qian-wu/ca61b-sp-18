import java.lang.reflect.Array;

public class ArrayDeque {
    private int[] item;
    private int nextFirst;
    private int nextLast;
    private int size;
    private static int MAX_SIZE = 8;
    private static double LOWER_CAP = 0.25;

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

    private void resize(int capasity) {
        int[] tmp = new int[MAX_SIZE + capasity];
        for(int i = 0; i < size; i ++ ) {
            tmp[i] = get(i);
        }

        this.item = tmp;
        MAX_SIZE *= 2;

        this.nextFirst = getNextFirst(0);
        this.nextLast = size;
    }

    public void addFirst(int item) {
        if(nextFirst == nextLast) {
            resize(MAX_SIZE);
        }
        this.item[nextFirst] = item;
        nextFirst = getNextFirst(nextFirst);
        size += 1;
    }

    public void addLast(int item) {
        if(nextFirst == nextLast) {
            resize(MAX_SIZE);
        }
        this.item[nextLast] = item;
        nextLast = getNextLast(nextLast);
        size += 1;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        for(int i = 0; i < size; i ++ ) {
            System.out.print(this.get(i) + " ");
        }
        System.out.println();
    }

    private void shrunk() {
        int[] tmp = new int[MAX_SIZE / 2];
        for(int i = 0; i < size; i ++ ) {
            tmp[i] = get(i);
        }

        this.item = tmp;
        MAX_SIZE /= 2;

        nextFirst = getNextFirst(0);
        nextLast = size;
    }

    private boolean isLowCapisity() {
        double percUsed = (double)size / (double)MAX_SIZE;
        if(percUsed < LOWER_CAP){
            System.out.println("Yes is low");
            return true;
        }else {
            return false;
        }
    }

    public int removeFirst() {
        if(isLowCapisity()) {
            shrunk();
        }
        nextFirst = getNext(nextFirst + 1);
        int item = this.item[nextFirst];
        this.item[nextFirst] = 0;
        size -= 1;
        return item;
    }

    public int removeLast() {
        if(isLowCapisity()) {
            shrunk();
        }
        nextLast = getNext(nextLast - 1);
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
        ArrayDeque a = new ArrayDeque();
        a.addFirst(2);
        a.addFirst(1);
        a.addLast(3);
        a.addLast(4);
        a.addLast(5);
        a.addLast(6);
        a.addLast(7);
        a.addLast(8);
        a.addLast(9);
        a.addLast(10);

        a.removeLast();
        a.removeLast();
        a.removeLast();
        a.removeLast();
        a.removeLast();
        a.removeLast();
        a.removeLast();

        System.out.println(a.size());
        a.printDeque();
    }
}
