import java.io.FileWriter;
import java.io.IOException;

// Definition der Klasse RBTree (Red-Black Tree), eine generische Klasse mit Elementen, die Comparable sein müssen.
public class RBTree<T extends Comparable<T>> {

    // Konstanten für die Farben eines Knotens.
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    // Die Wurzel des Baumes.
    private Node root;

    // Konstruktor
    public RBTree() {
        root = null;
    }

    // Innere Klasse, die die Struktur eines Knotens im RB-Baum definiert.
    private class Node {
        T data; // Der Wert, den der Knoten speichert.
        Node left, right, parent; // Verweise auf den linken, rechten Kindknoten und den Elternknoten.
        boolean color; // Farbe des Knotens (RED oder BLACK).

        // Konstruktor für einen neuen Knoten.
        Node(T data) {
            this.data = data;
            this.left = null;
            this.right = null;
            this.color = RED; // Neue Knoten sind standardmäßig rot.
        }
    }

    // Methode zum Einfügen eines neuen Schlüssels in den Baum.
    public void insert(T key) {
        Node node = root; // Startpunkt ist die Wurzel.
        Node parent = null; // Verweis auf den Elternknoten des neuen Knotens.

        // Durchlaufen des Baumes, um die korrekte Einfügeposition zu finden.
        while (node != null) {
            parent = node;
            int cmp = key.compareTo(node.data); // Vergleich der Schlüssel.
            if (cmp < 0) {
                node = node.left; // Gehe nach links.
            } else if (cmp > 0) {
                node = node.right; // Gehe nach rechts.
            } else {
                throw new IllegalArgumentException("BST already contains a node with key " + key);
            }
        }

        // Erstellen und Hinzufügen des neuen Knotens.
        Node newNode = new Node(key);
        if (parent == null) { // Wenn der Baum leer ist.
            root = newNode;
        } else if (key.compareTo(parent.data) < 0) {
            parent.left = newNode; // Neuer Knoten wird links eingefügt.
        } else {
            parent.right = newNode; // Neuer Knoten wird rechts eingefügt.
        }
        newNode.parent = parent; // Setzen des Elternknotens.

        // Sicherstellen der Red-Black-Baum-Eigenschaften nach dem Einfügen.
        fixRedBlackPropertiesAfterInsert(newNode);
    }

    // Methode, um Red-Black-Baum-Eigenschaften nach einem Einfügen zu reparieren.
    private void fixRedBlackPropertiesAfterInsert(Node node) {
        // Fall 1: Der neue Knoten ist die Wurzel.
        if (node.parent == null) {
            node.color = BLACK;
            return;
        }

        // Fall 2: Der Elternknoten ist schwarz, kein Fix erforderlich.
        if (node.parent.color == BLACK) {
            return;
        }

        Node grandparent = node.parent.parent; // Großelternknoten.
        Node uncle = (grandparent.left == node.parent) ? grandparent.right : grandparent.left;

        // Fall 3: Eltern und Onkel sind rot.
        if (uncle != null && uncle.color == RED) {
            node.parent.color = BLACK; // Elternknoten schwarz machen.
            uncle.color = BLACK; // Onkel schwarz machen.
            grandparent.color = RED; // Großeltern rot machen.
            fixRedBlackPropertiesAfterInsert(grandparent); // Rekursive Reparatur am Großelternknoten.
            return;
        }

        // Fall 4: Eltern rot, Onkel schwarz, und der Knoten ist ein inneres Enkelkind.
        if (node == node.parent.right && node.parent == grandparent.left) {
            rotateLeft(node.parent); // Linksrotation um den Elternknoten.
            node = node.left;
        } else if (node == node.parent.left && node.parent == grandparent.right) {
            rotateRight(node.parent); // Rechtsrotation um den Elternknoten.
            node = node.right;
        }

        // Fall 5: Eltern rot, Onkel schwarz, und der Knoten ist ein äußeres Enkelkind.
        node.parent.color = BLACK; // Eltern schwarz machen.
        grandparent.color = RED; // Großeltern rot machen.
        if (node == node.parent.left) {
            rotateRight(grandparent); // Rechtsrotation um die Großeltern.
        } else {
            rotateLeft(grandparent); // Linksrotation um die Großeltern.
        }
    }

    // Methode für eine Linksrotation um einen Knoten.
    private void rotateLeft(Node node) {
        Node rightChild = node.right; // Rechte Kindknoten.
        node.right = rightChild.left; // Die linke Subtree des rechten Kindes wird das rechte Subtree von node.
        if (rightChild.left != null) {
            rightChild.left.parent = node;
        }
        rightChild.parent = node.parent; // Verbindung des rechten Kindes mit dem Elternknoten von node.
        if (node.parent == null) {
            root = rightChild; // Wenn node die Wurzel war, wird das rechte Kind die neue Wurzel.
        } else if (node == node.parent.left) {
            node.parent.left = rightChild;
        } else {
            node.parent.right = rightChild;
        }
        rightChild.left = node; // Der ursprüngliche Knoten wird das linke Kind.
        node.parent = rightChild;
    }

    // Methode für eine Rechtsrotation um einen Knoten.
    private void rotateRight(Node node) {
        Node leftChild = node.left; // Linkes Kind.
        node.left = leftChild.right; // Die rechte Subtree des linken Kindes wird die linke Subtree von node.
        if (leftChild.right != null) {
            leftChild.right.parent = node;
        }
        leftChild.parent = node.parent; // Verbindung des linken Kindes mit dem Elternknoten von node.
        if (node.parent == null) {
            root = leftChild; // Wenn node die Wurzel war, wird das linke Kind die neue Wurzel.
        } else if (node == node.parent.right) {
            node.parent.right = leftChild;
        } else {
            node.parent.left = leftChild;
        }
        leftChild.right = node; // Der ursprüngliche Knoten wird das rechte Kind.
        node.parent = leftChild;
    }

    // Methode, um den Baum im DOT-Format zu exportieren.
    public void printDOT(String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write("digraph RBTree {\n");
            writer.write("    node [shape=circle, fontcolor=white];\n");
            if (root != null) {
                writeDOTNode(writer, root);
            }
            writer.write("}\n");
        } catch (IOException e) {
            System.err.println("Error writing DOT file: " + e.getMessage());
        }
    }

    // Hilfsmethode, um Knoten im DOT-Format zu schreiben.
    private void writeDOTNode(FileWriter writer, Node node) throws IOException {
        if (node != null) {
            writer.write("    \"" + node.data + "\" [color=" + (node.color == RED ? "red" : "black") + ", style=filled];\n");
            if (node.left != null) {
                writer.write("    \"" + node.data + "\" -> \"" + node.left.data + "\";\n");
                writeDOTNode(writer, node.left);
            } else {
                writer.write("    \"" + node.data + "\" -> \"" + node.data + "_left_nil\" [color=black, style=filled, fontcolor=white];\n");
                writer.write("    \"" + node.data + "_left_nil\" [label=\"NIL\", shape=record, color=black, style=filled, fontcolor=white];\n");
            }
            if (node.right != null) {
                writer.write("    \"" + node.data + "\" -> \"" + node.right.data + "\";\n");
                writeDOTNode(writer, node.right);
            } else {
                writer.write("    \"" + node.data + "\" -> \"" + node.data + "_right_nil\" [color=black, style=filled, fontcolor=white];\n");
                writer.write("    \"" + node.data + "_right_nil\" [label=\"NIL\", shape=record, color=black, style=filled, fontcolor=white];\n");
            }
        }
    }
}
