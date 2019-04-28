package clrs.advanced_data_structures;

import java.util.*;

public class DisjointSet<E>{

    private Map<E, DisjointNode<E>> map;

    public DisjointSet(Collection<E> objects) {
        map = new HashMap<>();
       for (E e: objects) {
           map.put(e, new DisjointNode<>(e));
       }
    }

    private DisjointNode<E> get(E data) {
        return map.get(data);
    }

    private boolean contains(E e) {
        return map.containsKey(e);
    }

    public void makeSet(E x) {
        DisjointNode<E> node = get(x);
        node.parent = node;
        node.rank = 0;
    }

    public  DisjointNode<E> findSet(E data) {
        if (!contains(data)) return null;

        DisjointNode<E> node = get(data);
        if (!node.parent.equals(node)){
            node.parent = findSet(node.parent.data);
        }
        return node.parent;
    }

    public void union(E x, E y) {
        if (x == null || y == null) return;
        link(findSet(x), findSet(y));
    }

    private void link(DisjointNode<E> x, DisjointNode<E> y) {
        if (x.rank > y.rank) {
            y.parent = x;
        } else {
            x.parent = y;
            if (x.rank == y.rank) {
                y.rank += 1;
            }
        }
    }
}

class DisjointNode<E> {
    E data;
    DisjointNode<E> parent;
    int rank;

    DisjointNode(E data) {
        this.data = data;
    }
}