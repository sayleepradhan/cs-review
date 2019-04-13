package clrs.red_black_trees;

enum Color {
    Red, Black
}
class RedBlackNode {
    Integer data;
    RedBlackNode parent;
    RedBlackNode left;
    RedBlackNode right;
    Color color;

    RedBlackNode(int value) {
        super();
        this.data = value;
    }
    RedBlackNode() {}
}