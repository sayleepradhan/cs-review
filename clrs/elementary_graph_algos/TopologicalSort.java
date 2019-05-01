package clrs.elementary_graph_algos;

import java.util.*;

public class TopologicalSort<E> {


    private List<DFSVertex<E>> toppologicalSort(DFSGraph<E> graph) {
        DepthFirstSearch<E> dfs = new DepthFirstSearch<>();
        dfs.search(graph);
        List<DFSVertex<E>> vertices = new LinkedList<>(graph.vertices);
        vertices.sort((u,v) -> {
            if (u.finishTime == v.finishTime) return 0;
            return (u.finishTime > v.finishTime) ? -1 : 1;
        });

        return vertices;
    }

    public static void main(String[] args) {
        DFSVertex<String> watch = new DFSVertex<>("Watch");
        DFSVertex<String> undershorts = new DFSVertex<>("Undershorts");
        DFSVertex<String> socks = new DFSVertex<>("Socks");
        DFSVertex<String> pants = new DFSVertex<>("Pants");
        DFSVertex<String> shoes = new DFSVertex<>("Shoes");
        DFSVertex<String> shirt = new DFSVertex<>("Shirt");
        DFSVertex<String> belt = new DFSVertex<>("Belt");
        DFSVertex<String> tie = new DFSVertex<>("Tie");
        DFSVertex<String> jacket = new DFSVertex<>("Jacket");

        undershorts.edges.addAll(Arrays.asList(pants, shoes));
        socks.edges.add(shoes);
        pants.edges.addAll(Arrays.asList(shoes, belt));
        belt.edges.add(jacket);
        shirt.edges.addAll(Arrays.asList(belt, tie));
        tie.edges.add(jacket);

        DFSGraph<String> graph = new DFSGraph<>();
        graph.vertices.addAll(Arrays.asList(
                 shirt, tie, jacket, belt, watch, undershorts, pants, shoes, socks
        ));

        TopologicalSort<String> solution = new TopologicalSort<>();
        List<DFSVertex<String>> list = solution.toppologicalSort(graph);

        for (DFSVertex<String> vertex: list) {
            System.out.print(vertex.data);
            if (list.indexOf(vertex) != list.size()-1) {
                System.out.print(" -> ");
            }
        }
    }
}
