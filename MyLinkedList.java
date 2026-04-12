import java.util.Iterator;

/**
 * Doubly-linked list implementation of MyList.
 * Each node holds a reference to the next and previous node.
 * @param <T> the type of elements
 */
public class MyLinkedList<T> implements MyList<T> {

    // ── Inner node class ───────────────────────────────────────────────────────

    /**
     * A single node in the doubly-linked list.
     */
    private class MyNode {
        T data;
        MyNode next;
        MyNode prev;

        MyNode(T data) { this.data = data; }
    }

    // ── Fields ─────────────────────────────────────────────────────────────────

    private MyNode head;
    private MyNode tail;
    private int size;

    // ── Helpers ────────────────────────────────────────────────────────────────

    /** Throws if index is out of [0, size) */
    private void checkIndex(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }

    /**
     * Navigates to node at given index.
     * Starts from head or tail depending on which end is closer.
     */
    private MyNode nodeAt(int index) {
        MyNode curr;
        if (index < size / 2) {
            curr = head;
            for (int i = 0; i < index; i++) curr = curr.next;
        } else {
            curr = tail;
            for (int i = size - 1; i > index; i--) curr = curr.prev;
        }
        return curr;
    }

    // ── MyList methods ─────────────────────────────────────────────────────────

    @Override
    public void add(T item) { addLast(item); }

    @Override
    public void addFirst(T item) {
        MyNode node = new MyNode(item);
        if (head == null) {
            head = tail = node;
        } else {
            node.next = head;
            head.prev = node;
            head = node;
        }
        size++;
    }

    @Override
    public void addLast(T item) {
        MyNode node = new MyNode(item);
        if (tail == null) {
            head = tail = node;
        } else {
            tail.next = node;
            node.prev = tail;
            tail = node;
        }
        size++;
    }

    @Override
    public void add(int index, T item) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Index: " + index);
        if (index == 0) { addFirst(item); return; }
        if (index == size) { addLast(item); return; }

        MyNode successor = nodeAt(index);
        MyNode predecessor = successor.prev;
        MyNode node = new MyNode(item);

        node.next = successor;
        node.prev = predecessor;
        predecessor.next = node;
        successor.prev = node;
        size++;
    }

    @Override
    public void set(int index, T item) {
        checkIndex(index);
        nodeAt(index).data = item;
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return nodeAt(index).data;
    }

    @Override
    public T getFirst() {
        if (head == null) throw new IndexOutOfBoundsException("List is empty");
        return head.data;
    }

    @Override
    public T getLast() {
        if (tail == null) throw new IndexOutOfBoundsException("List is empty");
        return tail.data;
    }

    @Override
    public void remove(int index) {
        checkIndex(index);
        MyNode node = nodeAt(index);
        unlink(node);
    }

    /** Detaches a node from the chain */
    private void unlink(MyNode node) {
        if (node.prev != null) node.prev.next = node.next;
        else head = node.next;           // node was head

        if (node.next != null) node.next.prev = node.prev;
        else tail = node.prev;           // node was tail

        node.next = node.prev = null;    // prevent memory leaks / loops
        size--;
    }

    @Override
    public void removeFirst() {
        if (head == null) throw new IndexOutOfBoundsException("List is empty");
        unlink(head);
    }

    @Override
    public void removeLast() {
        if (tail == null) throw new IndexOutOfBoundsException("List is empty");
        unlink(tail);
    }

    /**
     * Sorts using insertion sort with natural ordering.
     * T must implement Comparable.
     */
    @Override
    @SuppressWarnings("unchecked")
    public void sort() {
        if (size <= 1) return;
        // We sort by swapping data values only (nodes stay in place)
        MyNode i = head.next;
        while (i != null) {
            T key = i.data;
            MyNode j = i.prev;
            while (j != null && ((Comparable<T>) j.data).compareTo(key) > 0) {
                j.next.data = j.data;
                j = j.prev;
            }
            (j == null ? head : j.next).data = key;
            i = i.next;
        }
    }

    @Override
    public int indexOf(Object object) {
        int index = 0;
        for (MyNode n = head; n != null; n = n.next, index++) {
            if (object == null ? n.data == null : object.equals(n.data)) return index;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object object) {
        int index = size - 1;
        for (MyNode n = tail; n != null; n = n.prev, index--) {
            if (object == null ? n.data == null : object.equals(n.data)) return index;
        }
        return -1;
    }

    @Override
    public boolean exists(Object object) { return indexOf(object) != -1; }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[size];
        int i = 0;
        for (MyNode n = head; n != null; n = n.next) result[i++] = n.data;
        return result;
    }

    @Override
    public void clear() {
        // Null out links to prevent memory leaks
        MyNode curr = head;
        while (curr != null) {
            MyNode next = curr.next;
            curr.data = null;
            curr.next = curr.prev = null;
            curr = next;
        }
        head = tail = null;
        size = 0;
    }

    @Override
    public int size() { return size; }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private MyNode cursor = head;

            @Override public boolean hasNext() { return cursor != null; }

            @Override
            public T next() {
                T val = cursor.data;
                cursor = cursor.next;
                return val;
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (MyNode n = head; n != null; n = n.next) {
            sb.append(n.data);
            if (n.next != null) sb.append(", ");
        }
        return sb.append("]").toString();
    }
}
