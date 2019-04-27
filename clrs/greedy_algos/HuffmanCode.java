package clrs.greedy_algos;

import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class HuffmanCode {

    private Node runHuffman(Set<Node> setOfChars) {
        int length = setOfChars.size();
        PriorityQueue<Node> minPriorityQueue = new PriorityQueue<>(length,
                (Node n1, Node n2) -> {
                    if (n1.freq == n2.freq) return 0;

                    return n1.freq > n2.freq ? 1 : -1;
                });
        minPriorityQueue.addAll(setOfChars);
        while (minPriorityQueue.size() > 1) {
            Node x = minPriorityQueue.remove();
            Node y = minPriorityQueue.remove();
            if (x!=null && y!=null) {
                Node z = new Node(x.freq +y.freq,x,y);
                minPriorityQueue.add(z);
            }
        }
        return minPriorityQueue.remove();
    }

    private void printHuffmanTree(Node node, String code) {
        if (node == null) return;
        if (node.left == null && node.right == null) {
            System.out.println(node.character+": "+code);
        } else {
            printHuffmanTree(node.left, code.concat("0"));
            printHuffmanTree(node.right, code.concat("1"));
        }
    }



    public static void main(String[] args) {
        HuffmanCode solution = new HuffmanCode();
        Set<Node> charsToCode = new HashSet<>(Arrays.asList(
                new Node('a',45),
                new Node('b',13),
                new Node('c',12),
                new Node('d',16),
                new Node('e',9),
                new Node('f',5)
        ));
        Node root = solution.runHuffman(charsToCode);
        solution.printHuffmanTree(root, "");
    }

}

class Node {
    int freq;
    char character;
    Node left;
    Node right;

    Node(char c, int freq){
        this.character = c;
        this.freq = freq;
        this.left = null;
        this.right = null;
    }

    Node(int freq, Node left, Node right) {
        this.freq = freq;
        this.left = left;
        this.right = right;
    }
}