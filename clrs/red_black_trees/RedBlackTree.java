package clrs.red_black_trees;

public class RedBlackTree {

    private RedBlackNode root;
    private RedBlackNode nilNode;

    public RedBlackTree(int root) {
        this();

        this.root = new RedBlackNode(root);
    }

    public RedBlackTree() {
        this.nilNode = new RedBlackNode();
        this.nilNode.color = Color.Black;

        this.root = nilNode;
        this.root.parent = this.nilNode;
    }

    private void leftRotate(RedBlackNode node) {
        RedBlackNode rightChild = node.right;

        node.right = rightChild.left;
        if (rightChild.left != nilNode) {
            rightChild.left.parent = node;
        }

        rightChild.parent = node.parent;
        if (node.parent == nilNode) {
            this.root = rightChild;
        } else if (node.parent.left == node) {
            node.parent.left = rightChild;
        } else {
            node.parent.right = rightChild;
        }

        rightChild.left = node;
        node.parent = rightChild;
    }

    private void rightRotate(RedBlackNode node) {
        RedBlackNode leftChild = node.left;

        node.left = leftChild.right;
        if (leftChild.right != nilNode) {
            leftChild.right.parent = node;
        }
        leftChild.parent = node.parent;
        if (node.parent == nilNode) {
            this.root = leftChild;
        } else if (node.parent.left == node) {
            node.parent.left = leftChild;
        } else {
            node.parent.right = leftChild;
        }
        node.parent = leftChild;
        leftChild.right = node;
    }

    private void insert(RedBlackNode node) {
        RedBlackNode parent = nilNode;
        RedBlackNode newNodePos = this.root;
        while (newNodePos != nilNode) {
            parent = newNodePos;
            if (node.data < newNodePos.data) {
                newNodePos = newNodePos.left;
            } else {
                newNodePos = newNodePos.right;
            }
        }

        node.parent = parent;
        node.left = nilNode;
        node.right = nilNode;
        if (parent == nilNode) {
            this.root = node;
        } else if (node.data < parent.data) {
            parent.left = node;
        } else {
            parent.right = node;
        }

        node.color = Color.Red;
        insertFixUp(node);
    }

    private void insertFixUp(RedBlackNode node) {
        while (node.parent.color == Color.Red) {
            if (node.parent != nilNode && node.parent.parent != nilNode) {
                if (node.parent == node.parent.parent.left) {
                    // node exists on it's grandparent's left subtree
                    RedBlackNode rightUncle = node.parent.parent.right;

                    if (rightUncle.color == Color.Red) {
                        // Case 1: node's uncle is Red

                        rightUncle.color = Color.Black;
                        node.parent.color = Color.Black;
                        node.parent.parent.color = Color.Red;
                        node = node.parent.parent;
                    } else if (node == node.parent.right) {
                        // Case 2: node's uncle is black and node is the right child (forming a triangle)

                        node = node.parent;
                        leftRotate(node);
                    } else {
                        // Case 3: node's uncle is black and node is the left child (forming a line)

                        node.parent.color = Color.Black;
                        node.parent.parent.color = Color.Red;
                        rightRotate(node.parent.parent);
                    }
                } else {
                    // node exists on it's grandparent's right subtree

                    RedBlackNode leftUncle = node.parent.parent.left;

                    if (leftUncle.color == Color.Red) {
                        // Case 4: node's uncle is Red

                        leftUncle.color = Color.Black;
                        node.parent.color = Color.Black;
                        node.parent.parent.color = Color.Red;
                    } else if (node == node.parent.left) {
                        // Case 5: node's uncle is black and node is the left child (forming a triangle)

                        node = node.parent;
                        rightRotate(node);
                    } else {
                        // Case 6: node's uncle is black and node is the right child (forming a line)

                        node.parent.color = Color.Black;
                        node.parent.parent.color = Color.Red;
                        leftRotate(node.parent.parent);
                    }
                }
            }
        }
        this.root.color = Color.Black;
    }

    private void printAllElements(RedBlackNode node) {
        if (node != nilNode) {
            printAllElements(node.left);
            System.out.print(node.data+ " ");
            printAllElements(node.right);
        }
    }

    public static void main(String[] args) {
        RedBlackTree rbTree = new RedBlackTree();
        RedBlackNode x = new RedBlackNode(10);
        RedBlackNode y = new RedBlackNode(14);
        RedBlackNode z = new RedBlackNode(1);
        RedBlackNode a = new RedBlackNode(8);
        RedBlackNode b = new RedBlackNode(4);
        rbTree.insert(x);
        rbTree.insert(y);
        rbTree.insert(z);
        rbTree.insert(a);
        rbTree.insert(b);
        rbTree.printAllElements(rbTree.root);
    }

}
