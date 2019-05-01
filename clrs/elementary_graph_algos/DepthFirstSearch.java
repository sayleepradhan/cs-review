package clrs.elementary_graph_algos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DepthFirstSearch<E> {

    private int time = 0;

    public void search(DFSGraph<E> graph) {

        for (DFSVertex<E> u : graph.vertices ) {
            u.color = Color.White;
            u.parent = null;
        }
        time = 0;

        for (DFSVertex<E> u : graph.vertices ) {
            if (u.color == Color.White) {
                visit(graph, u);
            }
        }
    }

    private void visit(DFSGraph<E> graph, DFSVertex<E> u) {
        time += 1;
        u.color = Color.Gray;
        u.discoveryTime = time;
        for (DFSVertex<E> v : u.edges) {
            if (v.color == Color.White) {
                v.parent = u;
                visit(graph, v);
            }
        }
        time += 1;
        u.color = Color.Black;
        u.finishTime = time;
        System.out.println(u.data+ "\tstart: "+u.discoveryTime
                +"\tfinish:"+u.finishTime);
    }

    public static void main(String[] args) {
        DFSVertex<String> u = new DFSVertex<>("u");
        DFSVertex<String> v = new DFSVertex<>("v");
        DFSVertex<String> w = new DFSVertex<>("w");
        DFSVertex<String> x = new DFSVertex<>("x");
        DFSVertex<String> y = new DFSVertex<>("y");
        DFSVertex<String> z = new DFSVertex<>("z");

        u.edges.add(v);
        u.edges.add(x);

        v.edges.add(y);

        w.edges.add(y);
        w.edges.add(z);

        x.edges.add(v);

        y.edges.add(x);

        z.edges.add(z);

        DFSGraph<String> graph = new DFSGraph<>();

        graph.vertices.add(u);
        graph.vertices.add(v);
        graph.vertices.add(w);
        graph.vertices.add(x);
        graph.vertices.add(y);
        graph.vertices.add(z);

        DepthFirstSearch<String> dfs = new DepthFirstSearch<>();
        dfs.search(graph);
    }
}

class DFSVertex<E>{

    E data;
    Color color;
    DFSVertex<E> parent;
    List<DFSVertex<E>> edges;
    int discoveryTime;
    int finishTime;

    DFSVertex() {
        this.edges = new ArrayList<>();
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