import java.util.Iterator;
import java.util.Stack;

public class BST<K extends Comparable<K>, V> implements Iterable<BST.Entry<K, V>> {

    private Node root;
    private int size;

    // Public Entry class so the iterator can expose key+value
    public static class Entry<K, V> {
        private K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey()   { return key; }
        public V getValue() { return value; }
    }

    private class Node {
        private K key;
        private V val;
        private Node left, right;

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }

    public int size() {
        return size;
    }

    // ---- Part 2.1: put (iterative) ----
    public void put(K key, V val) {
        if (root == null) {
            root = new Node(key, val);
            size++;
            return;
        }
        Node current = root;
        while (true) {
            int cmp = key.compareTo(current.key);
            if (cmp < 0) {
                if (current.left == null) {
                    current.left = new Node(key, val);
                    size++;
                    return;
                }
                current = current.left;
            } else if (cmp > 0) {
                if (current.right == null) {
                    current.right = new Node(key, val);
                    size++;
                    return;
                }
                current = current.right;
            } else {
                // Key already exists — update value
                current.val = val;
                return;
            }
        }
    }

    // ---- Part 2.1: get (iterative) ----
    public V get(K key) {
        Node current = root;
        while (current != null) {
            int cmp = key.compareTo(current.key);
            if      (cmp < 0) current = current.left;
            else if (cmp > 0) current = current.right;
            else              return current.val;
        }
        return null;
    }

    // ---- Part 2.1: delete (iterative) ----
    public void delete(K key) {
        Node parent = null;
        Node current = root;
        boolean isLeftChild = false;

        // Find the node
        while (current != null && !current.key.equals(key)) {
            parent = current;
            int cmp = key.compareTo(current.key);
            if (cmp < 0) {
                current = current.left;
                isLeftChild = true;
            } else {
                current = current.right;
                isLeftChild = false;
            }
        }

        if (current == null) return; // Key not found

        // Case 1: Node has no children
        if (current.left == null && current.right == null) {
            if (parent == null) root = null;
            else if (isLeftChild) parent.left = null;
            else parent.right = null;

        // Case 2: Node has only right child
        } else if (current.left == null) {
            if (parent == null) root = current.right;
            else if (isLeftChild) parent.left = current.right;
            else parent.right = current.right;

        // Case 2b: Node has only left child
        } else if (current.right == null) {
            if (parent == null) root = current.left;
            else if (isLeftChild) parent.left = current.left;
            else parent.right = current.left;

        // Case 3: Node has two children — find in-order successor (iterative)
        } else {
            Node successorParent = current;
            Node successor = current.right;
            while (successor.left != null) {
                successorParent = successor;
                successor = successor.left;
            }
            // Replace current's key/val with successor's
            current.key = successor.key;
            current.val = successor.val;
            // Delete successor
            if (successorParent == current) {
                successorParent.right = successor.right;
            } else {
                successorParent.left = successor.right;
            }
        }
        size--;
    }

    // ---- Part 2.1 & 2.2: iterator() — in-order traversal using a Stack ----
    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new InOrderIterator();
    }

    private class InOrderIterator implements Iterator<Entry<K, V>> {
        private Stack<Node> stack = new Stack<>();

        public InOrderIterator() {
            pushLeft(root);
        }

        private void pushLeft(Node node) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public Entry<K, V> next() {
            Node node = stack.pop();
            pushLeft(node.right);
            return new Entry<>(node.key, node.val);
        }
    }

    // ---- Main: quick test ----
    public static void main(String[] args) {
        BST<Integer, String> tree = new BST<>();
        tree.put(5, "five");
        tree.put(3, "three");
        tree.put(7, "seven");
        tree.put(1, "one");
        tree.put(4, "four");
        tree.put(6, "six");
        tree.put(9, "nine");

        System.out.println("Size: " + tree.size());
        System.out.println("get(3): " + tree.get(3));
        System.out.println("get(7): " + tree.get(7));

        System.out.println("\nIn-order traversal (key and value):");
        for (var elem : tree) {
            System.out.println("key is " + elem.getKey() + " and value is " + elem.getValue());
        }

        tree.delete(3);
        System.out.println("\nAfter deleting 3, size: " + tree.size());
        System.out.println("In-order after delete:");
        for (var elem : tree) {
            System.out.println("key is " + elem.getKey() + " and value is " + elem.getValue());
        }
    }
}
