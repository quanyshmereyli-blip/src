/**
 * Queue data structure (FIFO) built on top of MyLinkedList.
 * MyLinkedList is chosen because both enqueue (addLast) and
 * dequeue (removeFirst) run in O(1).
 * @param <T> the type of elements
 */
public class MyQueue<T> {

    private final MyLinkedList<T> list = new MyLinkedList<>();

    /**
     * Adds item to the back of the queue.
     * @param item element to enqueue
     */
    public void enqueue(T item) {
        list.addLast(item);
    }

    /**
     * Removes and returns the front element.
     * @return front element
     * @throws java.lang.IndexOutOfBoundsException if queue is empty
     */
    public T dequeue() {
        T value = list.getFirst();
        list.removeFirst();
        return value;
    }

    /**
     * Returns the front element without removing it.
     * @return front element
     */
    public T peek() {
        return list.getFirst();
    }

    /** Returns true if the queue has no elements */
    public boolean isEmpty() {
        return list.size() == 0;
    }

    /** Returns number of elements in the queue */
    public int size() {
        return list.size();
    }

    @Override
    public String toString() {
        return "MyQueue" + list.toString();
    }
}
