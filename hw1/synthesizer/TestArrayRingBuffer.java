package synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer arb = new ArrayRingBuffer(5);
        arb.enqueue(1);
        arb.enqueue(2);
        arb.enqueue(3);
        assertEquals(1,arb.dequeue());
        assertEquals(2, arb.peek());
        assertEquals(2, arb.dequeue());
        arb.enqueue(4);
        arb.enqueue(5);
        assertEquals(3, arb.dequeue());
        assertEquals(4, arb.dequeue());
        assertEquals(5, arb.peek());
        assertEquals(5, arb.dequeue());
        arb.enqueue(6);
        arb.enqueue(7);
        assertEquals(6, arb.peek());
        assertEquals(6, arb.dequeue());
    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {

        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
