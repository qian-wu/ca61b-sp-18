package hw2;

import org.junit.Test;
import static org.junit.Assert.*;

public class PercolationTest {
    @Test
    public void testPercolation() {
        Percolation p = new Percolation(5);
        p.open(1, 2);
        assertTrue(p.isOpen(1, 2));
        p.open(2, 4);
        assertTrue(p.isOpen(2, 4));
        assertFalse(p.isFull(2, 4));
        p.open(2, 3);
        p.open(3, 3);
        p.open(0, 2);
        assertTrue(p.isFull(0, 2));
        assertTrue(p.isFull(1, 2));
        p.open(4, 3);
        p.open(2, 2);
        assertTrue(p.isFull(2, 2));
        assertTrue(p.isFull(3, 3));
        assertTrue(p.percolates());
    }
}
