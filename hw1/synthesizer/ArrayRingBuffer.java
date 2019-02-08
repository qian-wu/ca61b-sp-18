package synthesizer;

import java.util.Iterator;

public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    private class RingBufferIterator<T> implements Iterator {
        private int cur = first;

        @Override
        public boolean hasNext() {
            return cur != last;
        }

        @Override
        public T next() {
            T result = (T) rb[cur];
            if (cur == capacity - 1) {
                cur = -1;
            }
            cur += 1;
            return result;
        }
    }

    public Iterator<T> iterator() {
        return new RingBufferIterator();
    }

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        this.capacity = capacity + 1;
        rb = (T[]) new Object[this.capacity];
        this.fillCount = 0;
        this.first = 0;
        this.last = 0;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    public void enqueue(T x) {
        if (isFull()) {
            throw new RuntimeException("Ring Buffer Overflow");
        }
        if (last == capacity) {
            last = 0;
        }
        rb[last] = x;
        last += 1;
        fillCount += 1;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Ring Buffer Underflow");
        }
        T result = rb[first];
        rb[first] = null;
        first += 1;
        fillCount -= 1;
        if (first == capacity) {
            first = 0;
        }
        return result;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Ring Buffer Underflow");
        }
        return rb[first];
    }

}
