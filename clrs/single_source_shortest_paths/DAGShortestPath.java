package clrs.single_source_shortest_paths;

import java.util.*;

public class DAGShortestPath<E> {

    int time = 0;
    private void relax(DFSVertex<E> u, DFSVertex<E> v, int weight) {
        if (v.d > (u.d + weight)) {
            v.d = (u.d + weight);
        }
    }

    private void initialize(List<DFSVertex<E>> vertices) {
        for (DFSVertex v: vertices) {
            v.d = Integer.MAX_VALUE;
            v.parent = null;
        }
    }

    public void search(DFSGraph<E> graph) {

        for (DFSVertex<E> u : graph.vertices ) {
            u.color = Color.White;
            u.parent = null;
        }
        time = 0;

        for (DFSVertex<E> u : graph.vertices ) {
            if (u.color == Color.White) {
                visit(u);
            }
        }
    }

    private void visit(DFSVertex<E> u) {
        time += 1;
        u.color = Color.Gray;
        u.discoveryTime = time;
        for (Map.Entry<DFSVertex<E>, Integer> entry: u.edges.entrySet()) {
            DFSVertex<E> vertex = entry.getKey();
            if (vertex.color == Color.White) {
                vertex.parent = u;
                visit(vertex);
            }
        }
        time += 1;
        u.color = Color.Black;
        u.finishTime = time;
    }

    private List<DFSVertex<E>> topologicalSort(DFSGraph<E> graph) {
        search(graph);
        List<DFSVertex<E>> vertices = new LinkedList<>(graph.vertices);
        vertices.sort((u,v) -> {
            if (u.finishTime == v.finishTime) return 0;
            return (u.finishTime > v.finishTime) ? -1 : 1;
        });

        return vertices;
    }
    private void dagShortestPath(DFSGraph<E> graph){
        List<DFSVertex<E>> topologicalOrder = topologicalSort(graph);
        initialize(topologicalOrder);
        for (DFSVertex<E> u: topologicalOrder) {
            for (Map.Entry<DFSVertex<E>, Integer> entry: u.edges.entrySet()) {
                DFSVertex<E> v = entry.getKey();
                int weight = entry.getValue();
                relax(u, v, weight);
            }
        }
    }

}

enum Color {
    Black, Gray, White
}

class DFSVertex<E>{

    E data;
    Color color;
    DFSVertex<E> parent;
    Map<DFSVertex<E>, Integer> edges;
    int discoveryTime;
    int finishTime;

    int d;

    DFSVertex() {
        this.edges = new HashMap<>();
        discoveryTime = -1;
        finishTime = -1;
    }

    DFSVertex(E data) {
        this();
        this.data = data;
    }
}

class DFSGraph<E> {
    Set<DFSVertex<E>> vertices = new HashSet<>();
}