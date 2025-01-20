import java.util.Random;


public class Main {
    public static void main(String[] args) {
        RBTree<IntComparable> tree = new RBTree<>();
        Random random = new Random();

        for (int i = 0; i < 15; i++) {
            int randomValue = random.nextInt(100); // Erzeugen einer zufälligen Zahl zwischen 0 und 99
            IntComparable intComparable = new IntComparable(randomValue);
            System.out.println("Inserting: " + intComparable);
            tree.insert(intComparable);
        }

        // Optional: Implement a method to display the tree structure or its in-order traversal
        // tree.display(); // Uncomment this line if you have a display method implemented
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
