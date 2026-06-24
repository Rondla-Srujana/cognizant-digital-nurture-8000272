// Exercise 4: Employee Management System using Array

class Employee {
    int employeeId;
    String name;
    String position;
    double salary;

    public Employee(int employeeId, String name, String position, double salary) {
        this.employeeId = employeeId;
        this.name = name;
        this.position = position;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{ID=" + employeeId + ", Name='" + name + "', Position='" + position + "', Salary=$" + salary + "}";
    }
}

class EmployeeArray {
    private Employee[] employees;
    private int size;
    private int capacity;

    public EmployeeArray(int capacity) {
        this.capacity = capacity;
        employees = new Employee[capacity];
        size = 0;
    }

    // Add - O(1) if space available
    public void addEmployee(Employee e) {
        if (size >= capacity) {
            System.out.println("Array is full. Cannot add: " + e.name);
            return;
        }
        employees[size++] = e;
        System.out.println("Added: " + e);
    }

    // Search by employeeId - O(n)
    public Employee searchEmployee(int id) {
        for (int i = 0; i < size; i++) {
            if (employees[i].employeeId == id) {
                return employees[i];
            }
        }
        return null;
    }

    // Traverse - O(n)
    public void traverseEmployees() {
        System.out.println("--- All Employees (" + size + " records) ---");
        if (size == 0) {
            System.out.println("No employees found.");
            return;
        }
        for (int i = 0; i < size; i++) {
            System.out.println("  [" + i + "] " + employees[i]);
        }
    }

    // Delete by employeeId - O(n)
    public boolean deleteEmployee(int id) {
        for (int i = 0; i < size; i++) {
            if (employees[i].employeeId == id) {
                System.out.println("Deleted: " + employees[i]);
                // Shift elements left
                for (int j = i; j < size - 1; j++) {
                    employees[j] = employees[j + 1];
                }
                employees[--size] = null;
                return true;
            }
        }
        System.out.println("Employee ID " + id + " not found.");
        return false;
    }
}

public class Exercise4_EmployeeManagement {
    public static void main(String[] args) {
        System.out.println("=== Exercise 4: Employee Management System ===\n");

        EmployeeArray empArray = new EmployeeArray(10);

        // Add employees
        empArray.addEmployee(new Employee(1001, "Alice", "Manager", 85000.00));
        empArray.addEmployee(new Employee(1002, "Bob", "Developer", 72000.00));
        empArray.addEmployee(new Employee(1003, "Charlie", "Designer", 65000.00));
        empArray.addEmployee(new Employee(1004, "Diana", "QA Engineer", 60000.00));

        // Traverse
        System.out.println();
        empArray.traverseEmployees();

        // Search
        System.out.println();
        Employee found = empArray.searchEmployee(1002);
        System.out.println("Search ID 1002: " + (found != null ? found : "Not found"));
        Employee notFound = empArray.searchEmployee(9999);
        System.out.println("Search ID 9999: " + (notFound != null ? notFound : "Not found"));

        // Delete
        System.out.println();
        empArray.deleteEmployee(1003);
        empArray.deleteEmployee(9999);

        System.out.println();
        empArray.traverseEmployees();

        System.out.println("\n--- Time Complexity Analysis ---");
        System.out.println("Add (at end)  : O(1)");
        System.out.println("Search        : O(n)");
        System.out.println("Traverse      : O(n)");
        System.out.println("Delete        : O(n) — due to element shifting");
        System.out.println("Limitation    : Fixed size; insertion/deletion costly for middle elements.");
    }
}
