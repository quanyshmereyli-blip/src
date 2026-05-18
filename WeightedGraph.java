import java.util.HashMap;
import java.util.Map;

public class WeightedGraph<V> {
    private Map<Vertex<V>, Integer> map = new HashMap<>();

    public void addVertex(Vertex<V> vertex) {
        map.put(vertex, map.size());
    }

    public void addEdge(Vertex<V> source, Vertex<V> destination, double weight) {
        source.addAdjacentVertex(destination, weight);
    }

    public Map<Vertex<V>, Integer> getMap() {
        return map;
    }
}
