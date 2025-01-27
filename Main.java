import java.util.Random;


public class Main {
    public static void main(String[] args) {
        RBTree<IntComparable> tree = new RBTree<>();
        Random random = new Random();

        for (int i = 0; i < 15; i++) {
            int randomValue = random.nextInt(100); // Zufällige Zahl zwischen 0 und 99
            IntComparable intComparable = new IntComparable(randomValue);
            System.out.println("Inserting: " + intComparable);
            tree.insert(intComparable);

            // DOT-Datei nach jedem Schritt speichern
            String filename = "tree_step_" + i + ".dot";
            tree.printDOT(filename);
            System.out.println("DOT file written: " + filename);
        }
    }
    
    public static class IntComparable implements Comparable<IntComparable> {
        private int value;

        public IntComparable(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        @Override
        public int compareTo(IntComparable other) {
            return Integer.compare(this.value, other.value);
        }

        @Override
        public String toString() {
            return Integer.toString(value);
        }
    }
}
