package clrs.minimum_spanning_trees;

import java.util.*;

public class PrimImpl {
    private Set<Edge> findMST(ArrayList<PrimVertex> vertices, PrimVertex root) {
        Set<Edge> mstEdges = new HashSet<>();
        PriorityQueue<PrimVertex> minQueue = new PriorityQueue<>(
                vertices.size(),
                (PrimVertex u, PrimVertex v) -> {
                    if (u.key == v.key) return 0;
                    return u.key > v.key ? 1 : -1;
                });
        minQueue.addAll(vertices);

        for (PrimVertex u: vertices) {
            u.key = Integer.MAX_VALUE;
            u.parent = null;
        }

        root.key = 0;

        while (minQueue.iterator().hasNext()) {
            PrimVertex u = minQueue.poll();
            mstEdges.add(
                    new Edge(u.parent == null ? new PrimVertex(): u.parent
                            ,u
                            ,u.key)
            );
            for (Map.Entry<PrimVertex, Integer> edge: u.edges.entrySet()) {
                PrimVertex w = edge.getKey();
                int edgeWeight = edge.getValue();
                if (minQueue.contains(w) && edgeWeight < w.key) {
                    w.key = edgeWeight;
                    w.parent = u;
                    minQueue.remove(w);
                    minQueue.add(w);
                }
            }
        }


        root.key = 0;


        return mstEdges;
    }

    public static void main(String[] args) {
        PrimImpl solution = new PrimImpl();
        PrimVertex a = new PrimVertex('a');
        PrimVertex b = new PrimVertex('b');
        PrimVertex c = new PrimVertex('c');
        PrimVertex d = new PrimVertex('d');
        PrimVertex e = new PrimVertex('e');
        PrimVertex f = new PrimVertex('f');
        PrimVertex g = new PrimVertex('g');
        PrimVertex h = new PrimVertex('h');
        PrimVertex i = new PrimVertex('i');

        a.edges = new HashMap<>(Map.of(b,4, h, 8));
        b.edges = new HashMap<>(Map.of(a,4, h, 11, c, 8));
        c.edges = new HashMap<>(Map.of(b,8, i, 2, d, 7, f, 4));
        d.edges = new HashMap<>(Map.of(c,7, f, 14, e, 9));
        e.edges = new HashMap<>(Map.of(d,9,f,10));
        f.edges = new HashMap<>(Map.of(d, 14, e, 10, c, 4, g, 2));
        g.edges = new HashMap<>(Map.of(f,2,i,6,h,1));
        h.edges = new HashMap<>(Map.of(a,8,b,11,i,7,g,1));
        i.edges = new HashMap<>(Map.of(c,2,g,6, h,7));

        ArrayList<PrimVertex> vertices = new ArrayList<>(Arrays.asList(
                a,b,c,d,e,f,g,h,i
        ));

        Set<Edge> mstSolution = solution.findMST(vertices, a);

        for (Edge edge: mstSolution) {
            System.out.println("("+edge.u.data+","+edge.v.data+"): "+edge.weight);
        }
    }
}

class PrimVertex extends Vertex {
    int key;
    PrimVertex parent;
    Map<PrimVertex, Integer> edges;

    PrimVertex(){}

    PrimVertex(char data)
    {
        super(data);
        edges = new HashMap<>();
    }
}