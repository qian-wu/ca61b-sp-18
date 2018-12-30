import sun.awt.image.ImageWatched;

public class LinkedListDeque<Labor> {
    private class IntNode{
        private Labor item;
        private IntNode next;
        private IntNode prev;

        public IntNode(Labor x, IntNode prev, IntNode next) {
            item = x;
            this.prev = prev;
            this.next = next;
        }

        public IntNode(Labor x) {
            item = x;
        }
    }
    private int size;
    private IntNode sentinal;

    public LinkedListDeque() {
        sentinal = new IntNode(null);
        sentinal.prev = sentinal;
        sentinal.next = sentinal;
    }

    /** Add item at the first of LinkListDeque. */
    public void addFirst(Labor x) {
        IntNode newNode = new IntNode(x, sentinal, sentinal.next);
        sentinal.next = newNode;
        newNode.next.prev = newNode;
        size += 1;
    }

    public void addLast(Labor x) {
        IntNode newNode = new IntNode(x, sentinal.prev, sentinal);
        sentinal.prev = newNode;
        newNode.prev.next = newNode;
        size += 1;
    }

    public Labor removeFirst() {
        if(this.isEmpty()) return null;

        IntNode p = sentinal.next;
        Labor item = p.item;

        sentinal.next = p.next;
        p.next.prev = sentinal;

        p = null;
        size -= 1;

        return item;
    }

    public Labor removeLast() {
        if(this.isEmpty()) return null;

        IntNode p = sentinal.prev;
        Labor item = p.item;

        sentinal.prev = p.prev;
        p.prev.next = sentinal;

        p = null;
        size -= 1;

        return item;
    }

    /** Returs the size of this LinkListDeque */
    public int size() {
        return size;
    }

    public boolean isEmpty() {
        if(sentinal.prev.equals(sentinal)) {
            return true;
        }else {
            return false;
        }
    }

    public void printDeque() {
        IntNode p = sentinal.next;
        while(!p.equals(sentinal)) {
            System.out.print(p.item + " ");
            p = p.next;
        }
        System.out.println();
    }

    public Labor get(int index) {
        if(this.isEmpty()) return null;

        IntNode p = sentinal.next;
        while(index > 0) {
            p = p.next;
            index -= 1;
        }
        return p.item;
    }

    public Labor getRecursive(IntNode p, int index) {
        if(index == 0) {
            return p.item;
        }
        return getRecursive(p.next, index - 1);
    }

    public Labor getRecursive(int index) {
        return getRecursive(sentinal.next, index);
    }

    public static void main(String[] args) {
        LinkedListDeque<String> L = new LinkedListDeque<String>();
        System.out.println(L.isEmpty());

        L.addFirst("0");
        L.addFirst("5");
        L.addLast("a");
        L.addLast("b");
        L.printDeque();

        System.out.println(L.getRecursive(0));
        System.out.println(L.getRecursive(3));

        L.removeFirst();
        L.printDeque();
        L.removeLast();
        L.printDeque();
    }
}
