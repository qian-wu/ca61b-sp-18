import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayDequeTest {

    @Test
    public void testGetIndex() {
        ArrayDeque a = new ArrayDeque();
        assertEquals(7, a.getNextFirst(0));
        assertEquals(0, a.getNextLast(7));
    }

    @Test
    public void testAddFirst() {
        ArrayDeque a = new ArrayDeque();
        a.addFirst(2);
        assertEquals(2, a.get(0));
        a.addFirst(3);
        assertEquals(3, a.get(0));
        assertEquals(2, a.get(1));
    }

    @Test
    public void testRemoveFirst() {
        ArrayDeque a = new ArrayDeque();
        a.addFirst(2);
        a.addFirst(3);
        assertEquals(3, a.removeFirst());
        assertEquals(2, a.removeFirst());
    }

    @Test
    public void testAddLast() {
        ArrayDeque a = new ArrayDeque();
        int i = 1;
        while (i < 8) {
            a.addLast(i);
            i++;
        }
        assertEquals(1, a.get(0));
        assertEquals(7, a.get(6));
    }

    @Test
    public void testRemoveLast() {
        ArrayDeque a = new ArrayDeque();
        a.addFirst(2);
        a.addFirst(3);
        assertEquals(2, a.removeLast());
        assertEquals(3, a.removeLast());
    }

    public static void main(String[] args) {
        jh61b.junit.TestRunner.runTests("all", ArrayDequeTest.class);
    }
}
