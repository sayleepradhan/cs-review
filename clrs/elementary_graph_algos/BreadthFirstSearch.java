package clrs.elementary_graph_algos;

import java.util.*;

enum Color {
    Black, Gray, White
}

public class BreadthFirstSearch<E> {

    private boolean search(
            Graph<E> graph,
            Vertex<E> source,
            Vertex<E> searchKey
    ) {

        Queue<Vertex<E>> queue = new LinkedList<>();

        for (Vertex<E> u: graph.vertices) {
            if (!u.equals(source)) {
                u.color = Color.White;
                u.distance = Integer.MAX_VALUE;
                u.parent = null;
            }
        }

        source.color = Color.Gray;
        source.distance = 0;
        source.parent = null;

        queue.add(source);

        while (!queue.isEmpty()) {
            Vertex<E> u = queue.remove();

            if (u.equals(searchKey)) {
                return true;
            }
            for (Vertex<E> v : u.edges) {
                v.color = Color.Gray;
                v.parent = u;
                v.distance = u.distance + 1;
                queue.add(v);
            }
            u.color = Color.Black;
        }

        return false;
    }

    public static void main(String[] args) {
        Vertex<String> r = new Vertex<>("Dallas");
        Vertex<String> s = new Vertex<>("San Francisco");
        Vertex<String> t = new Vertex<>("Seattle");
        Vertex<String> u = new Vertex<>("Los Angeles");
        Vertex<String> v = new Vertex<>("St. Louis");
        Vertex<String> w = new Vertex<>("New York");
        Vertex<String> x = new Vertex<>("Boston");
        Vertex<String> y = new Vertex<>("Chicago");

        r.edges.add(s);
        r.edges.add(v);

        s.edges.add(r);
        s.edges.add(w);

        t.edges.add(w);
        t.edges.add(x);
        t.edges.add(u);

        u.edges.add(t);
        u.edges.add(x);
        u.edges.add(y);

        v.edges.add(r);

        w.edges.add(s);
        w.edges.add(x);
        w.edges.add(t);

        x.edges.add(w);
        x.edges.add(t);
        x.edges.add(y);

        y.edges.add(x);
        y.edges.add(u);

        Graph<String> graph = new Graph<>();
        graph.vertices.add(r);
        graph.vertices.add(s);
        graph.vertices.add(t);
        graph.vertices.add(u);
        graph.vertices.add(v);
        graph.vertices.add(w);
        graph.vertices.add(x);
        graph.vertices.add(y);

        BreadthFirstSearch<String> bfs = new BreadthFirstSearch<>();

        boolean exists = bfs.search(graph, s, y);

        System.out.print(exists);
    }
}

class Graph<E> {
    Set<Vertex<E>> vertices = new HashSet<>();
}

class Vertex<E> {
    E data;
    Color color;
    Integer distance;
    Vertex<E> parent;
    List<Vertex<E>> edges;

    Vertex(E data) {
        this.data = data;
        this.edges = new ArrayList<>();
        this.parent = null;
    }
}