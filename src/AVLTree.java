public class AVLTree extends BinaryTree {
    private void updateHeight(Node x) {
        x.height = 1 + Math.max(height(x.left), height(x.right));
    }

    private int height(Node x) {
        return x == null ? -1 : x.height;
    }

    private int getBalance(Node x) {
        return (x == null) ? 0 : height(x.right) - height(x.left);
    }

    private Node rotateRight(Node x) {
        Node y = x.left;
        Node z = y.right;
        y.right = x;
        x.left = z;
        updateHeight(x);
        updateHeight(y);
        return y;
    }

    private Node rotateLeft(Node x) {
        Node y = x.right;
        Node z = y.left;
        y.left = x;
        x.right = z;
        updateHeight(x);
        updateHeight(y);
        return y;
    }

    private Node rebalance(Node x) {
        updateHeight(x);
        int balance = getBalance(x);
        if (balance > 1) {
            if (height(x.right.right) <= height(x.right.left)) {
                x.right = rotateRight(x.right);
            }
            x = rotateLeft(x);
        } else if (balance < -1) {
            if (height(x.left.left) <= height(x.left.right)) {
                x.left = rotateLeft(x.left);
            }
            x = rotateRight(x);
        }
        return x;
    }

    @Override
    public void put(int key, int value) {
        root = this.put(root, key, value, null);
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
        return rebalance(x);
    }

    @Override
    public boolean delete(int value) {
        Node x = this.delete(root, value);
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
            x = rebalance(x);
            graph.removeNode(x.key + "");
            return x;
        }
        return null;
    }
}