package clrs.advanced_data_structures;

public class DisjointSet {

    private void makeSet(Node x) {
        x.parent = x;
        x.rank = 0;
    }

    private Node findSet(Node x) {
        if (x.parent != x){
            x.parent = findSet(x.parent);
        }
        return x.parent;
    }

    private void union(Node x, Node y) {
        link(findSet(x), findSet(y));
    }

    private void link(Node x, Node y) {
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

class Node {
    Node parent;
    int rank;
}