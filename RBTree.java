public class RBTree <T extends Comparable<T>>{

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private Node root;

    private class Node {
        T data;
        Node left, right, parent;
        boolean color;

        Node(T data) {
            this.data = data;
            this.color = RED;
        }

    }

    public void insert(T key) {
        Node node = root;
        Node parent = null;

        // Traverse the tree to find the correct spot
        while (node != null) {
            parent = node;
            int cmp = key.compareTo(node.data);
            if (cmp < 0) {
                node = node.left;
            } else if (cmp > 0) {
                node = node.right;
            } else {
                throw new IllegalArgumentException("BST already contains a node with key " + key);
                // Kein Abbruch. Einfach Zahl ignorieren
            }
        }

        // Insert the new node
        Node newNode = new Node(key);
        if (parent == null) {
            root = newNode;
        } else if (key.compareTo(parent.data) < 0) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
        newNode.parent = parent;

        // Fix the red-black properties
        fixRedBlackPropertiesAfterInsert(newNode);
    }

    private void fixRedBlackPropertiesAfterInsert(Node node) {
        // Case 1: The new node is the root
        if (node.parent == null) {
            node.color = BLACK;
            return;
        }

        // Case 2: The parent is black
        if (node.parent.color == BLACK) {
            return;
        }

        Node grandparent = node.parent.parent;
        Node uncle = (grandparent.left == node.parent) ? grandparent.right : grandparent.left;

        // Case 3: Both the parent and uncle are red
        if (uncle != null && uncle.color == RED) {
            node.parent.color = BLACK;
            uncle.color = BLACK;
            grandparent.color = RED;
            fixRedBlackPropertiesAfterInsert(grandparent);
            return;
        }

        // Case 4: Parent is red, uncle is black, node is an "inner" grandchild
        if (node == node.parent.right && node.parent == grandparent.left) {
            rotateLeft(node.parent);
            node = node.left;
        } else if (node == node.parent.left && node.parent == grandparent.right) {
            rotateRight(node.parent);
            node = node.right;
        }

        // Case 5: Parent is red, uncle is black, node is an "outer" grandchild
        node.parent.color = BLACK;
        grandparent.color = RED;
        if (node == node.parent.left) {
            rotateRight(grandparent);
        } else {
            rotateLeft(grandparent);
        }
    }

    private void rotateLeft(Node node) {
        Node rightChild = node.right;
        node.right = rightChild.left;
        if (rightChild.left != null) {
            rightChild.left.parent = node;
        }
        rightChild.parent = node.parent;
        if (node.parent == null) {
            root = rightChild;
        } else if (node == node.parent.left) {
            node.parent.left = rightChild;
        } else {
            node.parent.right = rightChild;
        }
        rightChild.left = node;
        node.parent = rightChild;
    }

    private void rotateRight(Node node) {
        Node leftChild = node.left;
        node.left = leftChild.right;
        if (leftChild.right != null) {
            leftChild.right.parent = node;
        }
        leftChild.parent = node.parent;
        if (node.parent == null) {
            root = leftChild;
        } else if (node == node.parent.right) {
            node.parent.right = leftChild;
        } else {
            node.parent.left = leftChild;
        }
        leftChild.right = node;
        node.parent = leftChild;
    }


    
}
