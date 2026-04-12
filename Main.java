/**
 * Tests for all data structures.
 * Run with: javac src/*.java -d out && java -cp out Main
 */
public class Main {

    public static void main(String[] args) {
        testMyArrayList();
        testMyLinkedList();
        testMyStack();
        testMyQueue();
        testMyMinHeap();
    }

    // ── MyArrayList ────────────────────────────────────────────────────────────

    static void testMyArrayList() {
        System.out.println("=== MyArrayList ===");
        MyArrayList<Integer> list = new MyArrayList<>();

        list.add(3);
        list.add(1);
        list.add(4);
        list.add(1);
        list.add(5);
        System.out.println("After add:       " + list);          // [3, 1, 4, 1, 5]

        list.addFirst(0);
        list.addLast(9);
        System.out.println("addFirst/Last:   " + list);          // [0, 3, 1, 4, 1, 5, 9]

        list.add(3, 99);
        System.out.println("add(3, 99):      " + list);          // [0, 3, 1, 99, 4, 1, 5, 9]

        list.set(0, 100);
        System.out.println("set(0, 100):     " + list);          // [100, 3, 1, 99, ...]

        System.out.println("get(2):          " + list.get(2));   // 1
        System.out.println("getFirst:        " + list.getFirst());
        System.out.println("getLast:         " + list.getLast());

        list.remove(0);
        System.out.println("remove(0):       " + list);
        list.removeFirst();
        System.out.println("removeFirst:     " + list);
        list.removeLast();
        System.out.println("removeLast:      " + list);

        System.out.println("indexOf(1):      " + list.indexOf(1));
        System.out.println("lastIndexOf(1):  " + list.lastIndexOf(1));
        System.out.println("exists(99):      " + list.exists(99));
        System.out.println("exists(999):     " + list.exists(999));
        System.out.println("size:            " + list.size());

        list.sort();
        System.out.println("sorted:          " + list);

        System.out.println("iterator:        ");
        for (int n : list) System.out.print(n + " ");
        System.out.println();

        list.clear();
        System.out.println("after clear:     " + list + " size=" + list.size());
        System.out.println();
    }

    // ── MyLinkedList ───────────────────────────────────────────────────────────

    static void testMyLinkedList() {
        System.out.println("=== MyLinkedList ===");
        MyLinkedList<String> list = new MyLinkedList<>();

        list.add("banana");
        list.add("apple");
        list.add("mango");
        System.out.println("After add:       " + list);

        list.addFirst("cherry");
        list.addLast("grape");
        System.out.println("addFirst/Last:   " + list);

        list.add(2, "kiwi");
        System.out.println("add(2, kiwi):    " + list);

        list.set(0, "CHERRY");
        System.out.println("set(0, CHERRY):  " + list);

        System.out.println("get(1):          " + list.get(1));
        System.out.println("getFirst:        " + list.getFirst());
        System.out.println("getLast:         " + list.getLast());

        list.remove(2);
        System.out.println("remove(2):       " + list);
        list.removeFirst();
        System.out.println("removeFirst:     " + list);
        list.removeLast();
        System.out.println("removeLast:      " + list);

        System.out.println("indexOf(apple):  " + list.indexOf("apple"));
        System.out.println("exists(mango):   " + list.exists("mango"));
        System.out.println("size:            " + list.size());

        list.sort();
        System.out.println("sorted:          " + list);

        list.clear();
        System.out.println("after clear:     " + list + " size=" + list.size());
        System.out.println();
    }

    // ── MyStack ────────────────────────────────────────────────────────────────

    static void testMyStack() {
        System.out.println("=== MyStack ===");
        MyStack<Integer> stack = new MyStack<>();

        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println("After push 1,2,3: " + stack);
        System.out.println("peek:             " + stack.peek());  // 3
        System.out.println("pop:              " + stack.pop());   // 3
        System.out.println("pop:              " + stack.pop());   // 2
        System.out.println("size:             " + stack.size());  // 1
        System.out.println("isEmpty:          " + stack.isEmpty());
        stack.pop();
        System.out.println("isEmpty after pop:" + stack.isEmpty());
        System.out.println();
    }

    // ── MyQueue ────────────────────────────────────────────────────────────────

    static void testMyQueue() {
        System.out.println("=== MyQueue ===");
        MyQueue<String> queue = new MyQueue<>();

        queue.enqueue("first");
        queue.enqueue("second");
        queue.enqueue("third");
        System.out.println("After enqueue:    " + queue);
        System.out.println("peek:             " + queue.peek());      // first
        System.out.println("dequeue:          " + queue.dequeue());   // first
        System.out.println("dequeue:          " + queue.dequeue());   // second
        System.out.println("size:             " + queue.size());      // 1
        System.out.println();
    }

    // ── MyMinHeap ──────────────────────────────────────────────────────────────

    static void testMyMinHeap() {
        System.out.println("=== MyMinHeap ===");
        MyMinHeap<Integer> heap = new MyMinHeap<>();

        heap.insert(5);
        heap.insert(3);
        heap.insert(8);
        heap.insert(1);
        heap.insert(4);
        System.out.println("After inserts:    " + heap);
        System.out.println("getMin:           " + heap.getMin());         // 1
        System.out.println("extractMin:       " + heap.extractMin());     // 1
        System.out.println("extractMin:       " + heap.extractMin());     // 3
        System.out.println("extractMin:       " + heap.extractMin());     // 4
        System.out.println("Remaining:        " + heap);
        System.out.println("size:             " + heap.size());
        System.out.println();
    }
}
