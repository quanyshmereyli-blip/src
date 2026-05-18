public class Main {
    public static void main(String[] args) {
        // Create vertices
        Vertex<String> almaty   = new Vertex<>("Almaty");
        Vertex<String> astana   = new Vertex<>("Astana");
        Vertex<String> shymkent = new Vertex<>("Shymkent");
        Vertex<String> karaganda= new Vertex<>("Karaganda");
        Vertex<String> aktobe   = new Vertex<>("Aktobe");

        // Build weighted graph
        WeightedGraph<String> graph = new WeightedGraph<>();
        graph.addVertex(almaty);
        graph.addVertex(astana);
        graph.addVertex(shymkent);
        graph.addVertex(karaganda);
        graph.addVertex(aktobe);

        graph.addEdge(almaty,    shymkent,  5.0);
        graph.addEdge(almaty,    karaganda, 10.0);
        graph.addEdge(shymkent,  astana,    7.0);
        graph.addEdge(karaganda, astana,    3.0);
        graph.addEdge(karaganda, aktobe,    8.0);
        graph.addEdge(astana,    aktobe,    2.0);

        // BFS
        Search<String> bfs = new BreadthFirstSearch<>();
        bfs.search(almaty);

        System.out.println();

        // Dijkstra
        Search<String> dijkstra = new DijkstraSearch<>();
        dijkstra.search(almaty);
    }
}
