package clrs.minimum_spanning_trees;


import clrs.advanced_data_structures.DisjointSet;

import java.util.*;

public class KruskalImpl {

    private Set<Edge> findMST(Graph graph) {
        Set<Edge> minimumSpanningTree = new HashSet<>();

        DisjointSet<Vertex> disjointSet = new DisjointSet<>(graph.vertices);

        for (Vertex v: graph.vertices) {
            disjointSet.makeSet(v);
        }

        ArrayList<Edge> sortedEdges = new ArrayList<>(graph.edges);
        sortedEdges.sort((Edge e1, Edge e2)->{
            if (e1.weight == e2.weight) return 0;
            return (e1.weight > e2.weight) ? 1 : -1;
        });

        for (Edge edge: sortedEdges) {
            if (disjointSet.findSet(edge.u) != disjointSet.findSet(edge.v)) {
                minimumSpanningTree.add(edge);
                disjointSet.union(edge.u,edge.v);
            }
        }

        return minimumSpanningTree;
    }

    public static void main(String[] args) {
        KruskalImpl solution = new KruskalImpl();
        Vertex a = new Vertex('a');
        Vertex b = new Vertex('b');
        Vertex c = new Vertex('c');
        Vertex d = new Vertex('d');
        Vertex e = new Vertex('e');
        Vertex f = new Vertex('f');
        Vertex g = new Vertex('g');
        Vertex h = new Vertex('h');
        Vertex i = new Vertex('i');
        ArrayList<Vertex> vertices = new ArrayList<>(Arrays.asList(
                a,b,c,d,e,f,g,h,i
        ));
        ArrayList<Edge> edges = new ArrayList<>(Arrays.asList(
                new Edge(a,b,4),
                new Edge(a,h,8),
                new Edge(b,h,11),
                new Edge(b,c,8),
                new Edge(c,d,7),
                new Edge(c,f,4),
                new Edge(c,i,2),
                new Edge(d,e,9),
                new Edge(d,f,14),
                new Edge(e,f,10),
                new Edge(f,g,2),
                new Edge(g,i,6),
                new Edge(g,h,1),
                new Edge(h,i,7)
        ));
        Graph graph = new Graph(edges, vertices);

        Set<Edge> mstEdges = solution.findMST(graph);

        for (Edge edge: mstEdges) {
            System.out.println("("+edge.u.data+","+edge.v.data+"): "+edge.weight);
        }
    }
}

class Graph {
    List<Edge> edges;
    List<Vertex> vertices;

    Graph(ArrayList<Edge> edges, ArrayList<Vertex> vertices) {
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
    Vertex(char data) {
        this.data = data;
    }
}