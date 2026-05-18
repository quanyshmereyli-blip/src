import java.util.*;

public class DijkstraSearch<V> implements Search<V> {

    @Override
    public void search(Vertex<V> start) {
        Map<Vertex<V>, Double> distances = new HashMap<>();
        Map<Vertex<V>, Vertex<V>> previous = new HashMap<>();
        PriorityQueue<Vertex<V>> pq = new PriorityQueue<>(
                Comparator.comparingDouble(v -> distances.getOrDefault(v, Double.MAX_VALUE))
        );
        Set<Vertex<V>> visited = new HashSet<>();

        distances.put(start, 0.0);
        pq.add(start);

        System.out.println("Dijkstra shortest paths from " + start + ":");

        while (!pq.isEmpty()) {
            Vertex<V> current = pq.poll();

            if (visited.contains(current)) continue;
            visited.add(current);

            for (Map.Entry<Vertex<V>, Double> entry : current.getAdjacentVertices().entrySet()) {
                Vertex<V> neighbor = entry.getKey();
                double newDist = distances.getOrDefault(current, Double.MAX_VALUE) + entry.getValue();

                if (newDist < distances.getOrDefault(neighbor, Double.MAX_VALUE)) {
                    distances.put(neighbor, newDist);
                    previous.put(neighbor, current);
                    pq.add(neighbor);
                }
            }
        }

        // Print results
        for (Map.Entry<Vertex<V>, Double> entry : distances.entrySet()) {
            System.out.println("  Distance to " + entry.getKey().getData() + ": " + entry.getValue()
                    + " | Path: " + buildPath(previous, entry.getKey()));
        }
    }

    private String buildPath(Map<Vertex<V>, Vertex<V>> previous, Vertex<V> target) {
        List<V> path = new ArrayList<>();
        Vertex<V> step = target;
        while (step != null) {
            path.add(0, step.getData());
            step = previous.get(step);
        }
        return path.toString();
    }
}
