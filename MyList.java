import java.util.Iterator;

/**
 * Generic interface for list data structures.
 * @param <T> the type of elements in this list
 */
public interface MyList<T> extends Iterable<T> {
    /** Adds item to the end of the list */
    void add(T item);

    /** Replaces element at given index */
    void set(int index, T item);

    /** Inserts item at given index */
    void add(int index, T item);

    /** Adds item to the beginning */
    void addFirst(T item);

    /** Adds item to the end */
    void addLast(T item);

    /** Returns element at given index */
    T get(int index);

    /** Returns first element */
    T getFirst();

    /** Returns last element */
    T getLast();

    /** Removes element at given index */
    void remove(int index);

    /** Removes first element */
    void removeFirst();

    /** Removes last element */
    void removeLast();

    /** Sorts the list in natural order */
    void sort();

    /** Returns first index of object, or -1 */
    int indexOf(Object object);

    /** Returns last index of object, or -1 */
    int lastIndexOf(Object object);

    /** Returns true if object exists in list */
    boolean exists(Object object);

    /** Returns all elements as Object array */
    Object[] toArray();

    /** Removes all elements */
    void clear();

    /** Returns number of elements */
    int size();
}

