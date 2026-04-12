/**
 * Stack data structure (LIFO) built on top of MyArrayList.
 * MyArrayList is chosen because O(1) add/remove at the end.
 * @param <T> the type of elements
 */
public class MyStack<T> {

    private final MyArrayList<T> list = new MyArrayList<>();

    /**
     * Pushes item onto the top of the stack.
     * @param item element to push
     */
    public void push(T item) {
        list.addLast(item);
    }

    /**
     * Removes and returns the top element.
     * @return top element
     * @throws java.lang.IndexOutOfBoundsException if stack is empty
     */
    public T pop() {
        T value = list.getLast();
        list.removeLast();
        return value;
    }

    /**
     * Returns the top element without removing it.
     * @return top element
     */
    public T peek() {
        return list.getLast();
    }

    /** Returns true if the stack has no elements */
    public boolean isEmpty() {
        return list.size() == 0;
    }

    /** Returns number of elements in the stack */
    public int size() {
        return list.size();
    }

    @Override
    public String toString() {
        return "MyStack" + list.toString();
    }
}
