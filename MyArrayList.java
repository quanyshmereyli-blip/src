import java.util.Iterator;

/**
 * Dynamic array-based implementation of MyList.
 * Automatically resizes when capacity is exceeded.
 * @param <T> the type of elements
 */
public class MyArrayList<T> implements MyList<T> {

    private Object[] data;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    /** Creates an empty list with default capacity */
    public MyArrayList() {
        data = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    // ── helpers ────────────────────────────────────────────────────────────────

    /** Throws if index is out of [0, size) */
    private void checkIndex(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }

    /** Doubles capacity when the array is full */
    private void grow() {
        Object[] bigger = new Object[data.length * 2];
        for (int i = 0; i < size; i++) bigger[i] = data[i];
        data = bigger;
    }

    // ── MyList methods ─────────────────────────────────────────────────────────

    @Override
    public void add(T item) {
        if (size == data.length) grow();
        data[size++] = item;
    }

    @Override
    public void set(int index, T item) {
        checkIndex(index);
        data[index] = item;
    }

    @Override
    public void add(int index, T item) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Index: " + index);
        if (size == data.length) grow();
        // shift elements right
        for (int i = size; i > index; i--) data[i] = data[i - 1];
        data[index] = item;
        size++;
    }

    @Override
    public void addFirst(T item) { add(0, item); }

    @Override
    public void addLast(T item) { add(item); }

    @Override
    @SuppressWarnings("unchecked")
    public T get(int index) {
        checkIndex(index);
        return (T) data[index];
    }

    @Override
    public T getFirst() { return get(0); }

    @Override
    public T getLast() { return get(size - 1); }

    @Override
    public void remove(int index) {
        checkIndex(index);
        for (int i = index; i < size - 1; i++) data[i] = data[i + 1];
        data[--size] = null; // help GC
    }

    @Override
    public void removeFirst() { remove(0); }

    @Override
    public void removeLast() { remove(size - 1); }

    /**
     * Sorts using insertion sort with natural ordering.
     * T must implement Comparable.
     */
    @Override
    @SuppressWarnings("unchecked")
    public void sort() {
        for (int i = 1; i < size; i++) {
            T key = (T) data[i];
            int j = i - 1;
            while (j >= 0 && ((Comparable<T>) data[j]).compareTo(key) > 0) {
                data[j + 1] = data[j];
                j--;
            }
            data[j + 1] = key;
        }
    }

    @Override
    public int indexOf(Object object) {
        for (int i = 0; i < size; i++) {
            if (object == null ? data[i] == null : object.equals(data[i])) return i;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object object) {
        for (int i = size - 1; i >= 0; i--) {
            if (object == null ? data[i] == null : object.equals(data[i])) return i;
        }
        return -1;
    }

    @Override
    public boolean exists(Object object) { return indexOf(object) != -1; }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[size];
        for (int i = 0; i < size; i++) result[i] = data[i];
        return result;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) data[i] = null;
        size = 0;
    }

    @Override
    public int size() { return size; }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int cursor = 0;

            @Override public boolean hasNext() { return cursor < size; }

            @Override
            @SuppressWarnings("unchecked")
            public T next() { return (T) data[cursor++]; }
        };
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(data[i]);
            if (i < size - 1) sb.append(", ");
        }
        return sb.append("]").toString();
    }
}
