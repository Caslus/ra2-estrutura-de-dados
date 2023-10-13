public class Node {
    final int key;
    int value, height;
    Node left, right, parent;
    int N;

    public Node(int key, int val, int N, Node parent) {
        this.key = key;
        this.value = val;
        this.N = N;
        this.parent = parent;
    }
}