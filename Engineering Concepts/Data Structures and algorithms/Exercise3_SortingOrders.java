import java.util.Arrays;

// Exercise 3: Sorting Customer Orders

class Order {
    int orderId;
    String customerName;
    double totalPrice;

    public Order(int orderId, String customerName, double totalPrice) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Order{ID=" + orderId + ", Customer='" + customerName + "', Total=$" + totalPrice + "}";
    }
}

public class Exercise3_SortingOrders {

    // ---- Bubble Sort - O(n^2) ----
    public static void bubbleSort(Order[] orders) {
        int n = orders.length;
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (orders[j].totalPrice > orders[j + 1].totalPrice) {
                    // Swap
                    Order temp = orders[j];
                    orders[j] = orders[j + 1];
                    orders[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break; // Already sorted early exit
        }
    }

    // ---- Quick Sort - O(n log n) average ----
    public static void quickSort(Order[] orders, int low, int high) {
        if (low < high) {
            int pi = partition(orders, low, high);
            quickSort(orders, low, pi - 1);
            quickSort(orders, pi + 1, high);
        }
    }

    private static int partition(Order[] orders, int low, int high) {
        double pivot = orders[high].totalPrice;
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (orders[j].totalPrice <= pivot) {
                i++;
                Order temp = orders[i];
                orders[i] = orders[j];
                orders[j] = temp;
            }
        }
        // Place pivot in correct position
        Order temp = orders[i + 1];
        orders[i + 1] = orders[high];
        orders[high] = temp;
        return i + 1;
    }

    public static void printOrders(Order[] orders) {
        for (Order o : orders) System.out.println("  " + o);
    }

    public static void main(String[] args) {
        System.out.println("=== Exercise 3: Sorting Customer Orders ===\n");

        Order[] original = {
            new Order(1, "Alice", 250.00),
            new Order(2, "Bob", 89.50),
            new Order(3, "Charlie", 499.99),
            new Order(4, "Diana", 150.75),
            new Order(5, "Eve", 320.00)
        };

        System.out.println("Original Orders:");
        printOrders(original);

        // Bubble Sort on a copy
        Order[] bubbleCopy = Arrays.copyOf(original, original.length);
        long startB = System.nanoTime();
        bubbleSort(bubbleCopy);
        long endB = System.nanoTime();
        System.out.println("\nAfter Bubble Sort (by totalPrice ascending):");
        printOrders(bubbleCopy);
        System.out.println("Bubble Sort Time: " + (endB - startB) + " ns");

        // Quick Sort on a copy
        Order[] quickCopy = Arrays.copyOf(original, original.length);
        long startQ = System.nanoTime();
        quickSort(quickCopy, 0, quickCopy.length - 1);
        long endQ = System.nanoTime();
        System.out.println("\nAfter Quick Sort (by totalPrice ascending):");
        printOrders(quickCopy);
        System.out.println("Quick Sort Time: " + (endQ - startQ) + " ns");

        System.out.println("\n--- Time Complexity Analysis ---");
        System.out.println("Bubble Sort : Best O(n) | Average O(n^2) | Worst O(n^2)");
        System.out.println("Quick Sort  : Best O(n log n) | Average O(n log n) | Worst O(n^2)");
        System.out.println("Quick Sort is generally preferred due to better average performance.");
    }
}
