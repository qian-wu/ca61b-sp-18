public interface Deque<Item> {
    /** Add item at the first of LinkListDeque. */
    public void addFirst(Item x);

    public void addLast(Item x);

    public Item removeFirst();

    public Item removeLast();

    /** Returs the size of this LinkListDeque */
    public int size();

    public boolean isEmpty();

    public void printDeque();

    public Item get(int index);

    public Item getRecursive(int index);
}
