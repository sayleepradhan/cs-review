package clrs.single_source_shortest_paths;

import java.util.Arrays;
import java.util.List;

public class BellmanFordAlgorithm {
    private void relax(Edge edge) {
        if (edge.v.d > (edge.u.d + edge.weight)) {
            edge.v.d = (edge.u.d + edge.weight);
        }
    }

    private void initialize(List<Vertex> vertices) {
        for (Vertex v: vertices) {
            v.d = Integer.MAX_VALUE;
            v.parent = null;
        }
    }

    private boolean runBellmanFord(Graph<Edge, Vertex> graph) {
        initialize(graph.vertices);
        for (Vertex vertex: graph.vertices) {
            for (Edge edge: graph.edges) {
                relax(edge);
            }
        }

        for (Edge edge: graph.edges) {
            if (edge.v.d > (edge.u.d + edge.weight))
                    return false;
        }
        return true;
    }

    public static void main(String[] args) {
        BellmanFordAlgorithm solution = new BellmanFordAlgorithm();
        Vertex s = new Vertex('s');
        Vertex t = new Vertex('t');
        Vertex x = new Vertex('x');
        Vertex y = new Vertex('y');
        Vertex z = new Vertex('z');

        Graph<Edge,Vertex> graph = new Graph<>(
                Arrays.asList(
                        new Edge(s,t, 6),
                        new Edge(s,y, 7),
                        new Edge(t,x, 5),
                        new Edge(t,y, 8),
                        new Edge(t,z, -4),
                        new Edge(y,x, -3),
                        new Edge(y,z, 9),
                        new Edge(x,t, -2),
                        new Edge(z,s, 2),
                        new Edge(z,x, 7)
                ),
                Arrays.asList(
                        s,t,x,y,z
                ));
        boolean pathExists = solution.runBellmanFord(graph);
        System.out.println("Bellman ford path exists: "+pathExists);
    }
}

class Graph<E,V> {
    List<E> edges;
    List<V> vertices;

    Graph(List<E> edges, List<V> vertices) {
        this.edges = edges;
        this.vertices = vertices;
    }
}
class Edge {
    Vertex u, v;
    int weight;

    Edge(Vertex u, Vertex v, int weight) {
        this.u = u;
        this.v = v;
        this.weight = weight;
    }
}

class Vertex {
    char data;
    int d;
    Vertex parent;

    Vertex(char data) {
        this.data = data;
    }
}