import java.util.Arrays;
import java.util.Comparator;

// Exercise 2: E-commerce Platform Search Function

class ProductSearch {
    int productId;
    String productName;
    String category;

    public ProductSearch(int productId, String productName, String category) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{ID=" + productId + ", Name='" + productName + "', Category='" + category + "'}";
    }
}

public class Exercise2_EcommerceSearch {

    // Linear Search - O(n)
    public static int linearSearch(ProductSearch[] products, String targetName) {
        for (int i = 0; i < products.length; i++) {
            if (products[i].productName.equalsIgnoreCase(targetName)) {
                return i; // returns index
            }
        }
        return -1; // not found
    }

    // Binary Search - O(log n) — array must be sorted by productName
    public static int binarySearch(ProductSearch[] sortedProducts, String targetName) {
        int left = 0;
        int right = sortedProducts.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int cmp = sortedProducts[mid].productName.compareToIgnoreCase(targetName);

            if (cmp == 0) {
                return mid; // found
            } else if (cmp < 0) {
                left = mid + 1; // search right half
            } else {
                right = mid - 1; // search left half
            }
        }
        return -1; // not found
    }

    public static void main(String[] args) {
        System.out.println("=== Exercise 2: E-commerce Platform Search ===\n");

        // Unsorted array for linear search
        ProductSearch[] products = {
            new ProductSearch(1, "Tablet", "Electronics"),
            new ProductSearch(2, "Shoes", "Footwear"),
            new ProductSearch(3, "Laptop", "Electronics"),
            new ProductSearch(4, "Book", "Education"),
            new ProductSearch(5, "Watch", "Accessories")
        };

        // --- Linear Search ---
        System.out.println("--- Linear Search (unsorted array) ---");
        String target1 = "Laptop";
        int idx1 = linearSearch(products, target1);
        if (idx1 != -1) {
            System.out.println("Found '" + target1 + "' at index " + idx1 + ": " + products[idx1]);
        } else {
            System.out.println("'" + target1 + "' not found.");
        }

        String target2 = "Phone";
        int idx2 = linearSearch(products, target2);
        System.out.println("Search '" + target2 + "': " + (idx2 != -1 ? "Found at index " + idx2 : "Not found"));

        // --- Binary Search (sorted array) ---
        System.out.println("\n--- Binary Search (sorted by name) ---");

        // Sort a copy for binary search
        ProductSearch[] sortedProducts = products.clone();
        Arrays.sort(sortedProducts, Comparator.comparing(p -> p.productName.toLowerCase()));

        System.out.println("Sorted Products:");
        for (ProductSearch p : sortedProducts) System.out.println("  " + p);

        System.out.println();
        String target3 = "Tablet";
        int idx3 = binarySearch(sortedProducts, target3);
        if (idx3 != -1) {
            System.out.println("Found '" + target3 + "' at index " + idx3 + ": " + sortedProducts[idx3]);
        } else {
            System.out.println("'" + target3 + "' not found.");
        }

        String target4 = "Phone";
        int idx4 = binarySearch(sortedProducts, target4);
        System.out.println("Search '" + target4 + "': " + (idx4 != -1 ? "Found at index " + idx4 : "Not found"));

        System.out.println("\n--- Time Complexity Analysis ---");
        System.out.println("Linear Search : Best O(1) | Average O(n) | Worst O(n)");
        System.out.println("Binary Search : Best O(1) | Average O(log n) | Worst O(log n)");
        System.out.println("Binary Search is more suitable for large, sorted datasets.");
    }
}
