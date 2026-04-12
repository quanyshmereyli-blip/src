/**
 * Min-Heap built on top of MyArrayList.
 * MyArrayList is chosen because index-based access (parent/child
 * calculations) is O(1), which is essential for heap operations.
 *
 * Heap property: every parent is smaller than or equal to its children.
 * Parent of i  → (i-1)/2
 * Left child   → 2*i + 1
 * Right child  → 2*i + 2
 *
 * @param <T> element type, must be Comparable
 */
public class MyMinHeap<T extends Comparable<T>> {

    private final MyArrayList<T> heap = new MyArrayList<>();

    // ── Helpers ────────────────────────────────────────────────────────────────

    private int parent(int i) { return (i - 1) / 2; }
    private int left(int i)   { return 2 * i + 1; }
    private int right(int i)  { return 2 * i + 2; }

    /** Swaps elements at positions i and j */
    private void swap(int i, int j) {
        T tmp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, tmp);
    }

    /**
     * Moves element at index up until heap property is restored.
     * Called after inserting at the end.
     */
    private void heapifyUp(int i) {
        while (i > 0 && heap.get(i).compareTo(heap.get(parent(i))) < 0) {
            swap(i, parent(i));
            i = parent(i);
        }
    }

    /**
     * Moves element at index down until heap property is restored.
     * Called after removing the root.
     */
    private void heapifyDown(int i) {
        int size = heap.size();
        while (true) {
            int smallest = i;
            int l = left(i);
            int r = right(i);

            if (l < size && heap.get(l).compareTo(heap.get(smallest)) < 0)
                smallest = l;
            if (r < size && heap.get(r).compareTo(heap.get(smallest)) < 0)
                smallest = r;

            if (smallest == i) break;  // heap property satisfied

            swap(i, smallest);
            i = smallest;
        }
    }

    // ── Public API ─────────────────────────────────────────────────────────────

    /**
     * Inserts a new element into the heap.
     * O(log n)
     * @param item element to insert
     */
    public void insert(T item) {
        heap.addLast(item);
        heapifyUp(heap.size() - 1);
    }

    /**
     * Returns the minimum element (root) without removing it.
     * O(1)
     * @return minimum element
     */
    public T getMin() {
        if (heap.size() == 0) throw new IndexOutOfBoundsException("Heap is empty");
        return heap.getFirst();
    }

    /**
     * Removes and returns the minimum element.
     * O(log n)
     * @return minimum element
     */
    public T extractMin() {
        if (heap.size() == 0) throw new IndexOutOfBoundsException("Heap is empty");
        T min = heap.getFirst();
        T last = heap.getLast();
        heap.removeLast();

        if (heap.size() > 0) {
            heap.set(0, last);
            heapifyDown(0);
        }
        return min;
    }

    /** Returns true if the heap is empty */
    public boolean isEmpty() { return heap.size() == 0; }

    /** Returns number of elements */
    public int size() { return heap.size(); }

    @Override
    public String toString() {
        return "MyMinHeap" + heap.toString();
    }
}
