import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Exercise 1: Inventory Management System

class Product {
    int productId;
    String productName;
    int quantity;
    double price;

    public Product(int productId, String productName, int quantity, double price) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{ID=" + productId + ", Name='" + productName + "', Qty=" + quantity + ", Price=$" + price + "}";
    }
}

class Inventory {
    // Using HashMap for O(1) average lookup by productId
    private Map<Integer, Product> productMap = new HashMap<>();

    // Add product - O(1) average
    public void addProduct(Product p) {
        if (productMap.containsKey(p.productId)) {
            System.out.println("Product ID " + p.productId + " already exists. Use update.");
        } else {
            productMap.put(p.productId, p);
            System.out.println("Added: " + p);
        }
    }

    // Update product - O(1) average
    public void updateProduct(int productId, int newQuantity, double newPrice) {
        Product p = productMap.get(productId);
        if (p != null) {
            p.quantity = newQuantity;
            p.price = newPrice;
            System.out.println("Updated: " + p);
        } else {
            System.out.println("Product ID " + productId + " not found.");
        }
    }

    // Delete product - O(1) average
    public void deleteProduct(int productId) {
        Product removed = productMap.remove(productId);
        if (removed != null) {
            System.out.println("Deleted: " + removed);
        } else {
            System.out.println("Product ID " + productId + " not found.");
        }
    }

    // Display all - O(n)
    public void displayAll() {
        System.out.println("--- Inventory ---");
        if (productMap.isEmpty()) {
            System.out.println("Inventory is empty.");
        } else {
            for (Product p : productMap.values()) {
                System.out.println(p);
            }
        }
    }
}

public class Exercise1_InventoryManagement {
    public static void main(String[] args) {
        System.out.println("=== Exercise 1: Inventory Management System ===\n");

        Inventory inv = new Inventory();

        // Add products
        inv.addProduct(new Product(101, "Laptop", 50, 999.99));
        inv.addProduct(new Product(102, "Mouse", 200, 29.99));
        inv.addProduct(new Product(103, "Keyboard", 150, 49.99));
        inv.addProduct(new Product(101, "Tablet", 30, 499.99)); // Duplicate ID test

        System.out.println();
        inv.displayAll();

        // Update
        System.out.println();
        inv.updateProduct(102, 180, 24.99);
        inv.updateProduct(999, 10, 5.00); // Not found test

        // Delete
        System.out.println();
        inv.deleteProduct(103);
        inv.deleteProduct(103); // Already deleted test

        System.out.println();
        inv.displayAll();

        System.out.println("\n--- Time Complexity Analysis ---");
        System.out.println("Add    : O(1) average (HashMap put)");
        System.out.println("Update : O(1) average (HashMap get)");
        System.out.println("Delete : O(1) average (HashMap remove)");
        System.out.println("Display: O(n) (iterate all entries)");
    }
}
