
public class LinkedListDeque<Item> implements Deque<Item>{
    private class IntNode {
        private Item item;
        private IntNode next;
        private IntNode prev;

        public IntNode(Item x, IntNode prev, IntNode next) {
            item = x;
            this.prev = prev;
            this.next = next;
        }

        public IntNode(Item x) {
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
    @Override
    public void addFirst(Item x) {
        IntNode newNode = new IntNode(x, sentinal, sentinal.next);
        sentinal.next = newNode;
        newNode.next.prev = newNode;
        size += 1;
    }

    @Override
    public void addLast(Item x) {
        IntNode newNode = new IntNode(x, sentinal.prev, sentinal);
        sentinal.prev = newNode;
        newNode.prev.next = newNode;
        size += 1;
    }

    @Override
    public Item removeFirst() {
        if (this.isEmpty()) {
            return null;
        }

        IntNode p = sentinal.next;
        Item item = p.item;

        sentinal.next = p.next;
        p.next.prev = sentinal;

        p = null;
        size -= 1;

        return item;
    }

    @Override
    public Item removeLast() {
        if (this.isEmpty()) {
            return null;
        }

        IntNode p = sentinal.prev;
        Item item = p.item;

        sentinal.prev = p.prev;
        p.prev.next = sentinal;

        p = null;
        size -= 1;

        return item;
    }

    /** Returs the size of this LinkListDeque */
    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return sentinal.prev.equals(sentinal);
    }

    @Override
    public void printDeque() {
        IntNode p = sentinal.next;
        while (!p.equals(sentinal)) {
            System.out.print(p.item + " ");
            p = p.next;
        }
        System.out.println();
    }

    @Override
    public Item get(int index) {
        if (this.isEmpty()) {
            return null;
        }

        IntNode p = sentinal.next;
        while (index > 0) {
            p = p.next;
            index -= 1;
        }
        return p.item;
    }

    private Item getRecursive(IntNode p, int index) {
        if (index == 0) {
            return p.item;
        }
        return getRecursive(p.next, index - 1);
    }

    @Override
    public Item getRecursive(int index) {
        return getRecursive(sentinal.next, index);
    }

}
