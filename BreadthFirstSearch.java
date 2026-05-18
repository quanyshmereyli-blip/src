import java.util.*;

public class BreadthFirstSearch<V> implements Search<V> {

    @Override
    public void search(Vertex<V> start) {
        Set<Vertex<V>> visited = new HashSet<>();
        Queue<Vertex<V>> queue = new LinkedList<>();

        visited.add(start);
        queue.add(start);

        System.out.println("BFS traversal starting from " + start + ":");

        while (!queue.isEmpty()) {
            Vertex<V> current = queue.poll();
            System.out.print(current.getData() + " ");

            for (Vertex<V> neighbor : current.getAdjacentVertices().keySet()) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
        System.out.println();
    }
}
