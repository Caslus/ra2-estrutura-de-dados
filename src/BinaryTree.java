import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.view.Viewer;

public class BinaryTree {
    Node root;
    Graph graph = new SingleGraph("Binary Tree");

    public void put(int key, int value) {
        root = put(root, key, value, null);
    }

    private Node put(Node x, int key, int value, Node parent) {
        if (x == null) {
            return new Node(key, value, 1, parent);
        }
        if (value < x.value) {
            x.left = put(x.left, key, value, x);
        } else if (value > x.value) {
            x.right = put(x.right, key, value, x);
        } else {
            x.right = put(x.right, key, value, x);
        }

        x.N = size(x.left) + size(x.right) + 1;

        return x;
    }

    public boolean delete(int value) {
        Node x = delete(root, value);
        return x != null;
    }

    private Node delete(Node x, int value) {
        if (x == null) {
            return null;
        }
        if (value < x.value) {
            delete(x.left, value);
        } else if (value > x.value) {
            delete(x.right, value);
        } else {
            if (x.right == null && x.left == null) {
                if (x.parent.left == x) {
                    x.parent.left = null;
                }
                if (x.parent.right == x) {
                    x.parent.right = null;
                }
            } else if (x.right != null && x.left == null) {
                x.value = x.right.value;
                x.right = null;
            } else if (x.right == null) {
                x.value = x.left.value;
                x.left = null;
            } else {
                Node parent = x;
                Node succ = x.right;
                while (succ.left != null) {
                    parent = succ;
                    succ = succ.left;
                }
                if (parent != x) {
                    parent.left = succ.right;
                } else {
                    parent.right = succ.right;
                }
                x.value = succ.value;
            }
            graph.removeNode(x.key + "");
            return x;
        }
        return null;
    }

    public boolean search(int value) {
        Node x = search(root, value);
        return x != null;
    }

    private Node search(Node x, int value) {
        if (x == null) {
            return null;
        }
        if (x.value == value) {
            return x;
        }
        if (x.value < value) {
            return search(x.right, value);
        }
        return search(x.left, value);
    }

    public int size() {
        return size(root);
    }

    protected int size(Node n) {
        if (n == null) return 0;
        else return n.N;
    }

    public int rank(int value) {
        return rank(value, root);
    }

    private int rank(int value, Node x) {
        if (x == null) {
            return 0;
        }

        if (value < x.value) {
            return rank(value, x.left);
        } else if (value > x.value) {
            return 1 + size(x.left) + rank(value, x.right);
        } else {
            return size(x.left);
        }
    }

    public int depth(int key) {
        return depth(key, root, 1);
    }

    private int depth(int key, Node x, int level) {
        if (x == null) return 0;

        if (x.key == key) return level;

        int leftLevel = depth(key, x.left, level + 1);

        if (leftLevel > 0) {
            return leftLevel;
        } else {
            return depth(key, x.right, level + 1);
        }
    }

    public void print() {
        graph.clear();
        String styleSheet =
                "node {" +
                        "	text-alignment: right;" +
                        "	text-offset: 10px, 0px;" +
                        "   text-size: 15;" +
                        "   size: 7px, 7px;" +
                        "}" +
                        "node.marked {" +
                        "	fill-color: red;" +
                        "}";
        graph.setAttribute("ui.stylesheet", styleSheet);

        print(root, graph);
        Viewer viewer = graph.display();
        viewer.disableAutoLayout();

    }

    private void print(Node x, Graph graph) {
        if (x == null) return;

        org.graphstream.graph.Node n = graph.addNode(x.key + "");

        if (size() <= 50) {
            n.setAttribute("ui.label", x.value + (root == x ? " (root)" : ""));
        }

        n.setAttribute("x", rank(x.value));
        n.setAttribute("y", depth(x.key) * -1);

        print(x.left, graph);
        print(x.right, graph);

        if (x.left != null) {
            String leftKey = x.left.key + "";
            graph.addEdge(x.key + leftKey, x.key + "", leftKey);
        }

        if (x.right != null) {
            String rightKey = x.right.key + "";
            graph.addEdge(x.key + rightKey, x.key + "", rightKey);
        }
    }
}