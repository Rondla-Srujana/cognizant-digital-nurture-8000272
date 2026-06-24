// ============================================================
// Exercise 11: Dependency Injection
// File: Ex11_DependencyInjection_Customer.java
// ============================================================

import java.util.HashMap;
import java.util.Map;

// Repository interface
interface CustomerRepository {
    String findCustomerById(int id);
}

// Concrete repository
class CustomerRepositoryImpl implements CustomerRepository {
    private Map<Integer, String> db = new HashMap<>();

    CustomerRepositoryImpl() {
        db.put(1, "Alice");
        db.put(2, "Bob");
        db.put(3, "Charlie");
    }

    public String findCustomerById(int id) {
        return db.getOrDefault(id, "Customer not found");
    }
}

// Service class – dependency injected via constructor
class CustomerService {
    private CustomerRepository repository;

    // Constructor Injection
    CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public String getCustomer(int id) {
        return repository.findCustomerById(id);
    }
}

public class Ex11_DependencyInjection_Customer {
    public static void main(String[] args) {
        CustomerRepository repo    = new CustomerRepositoryImpl();
        CustomerService    service = new CustomerService(repo); // inject

        System.out.println("Customer 1: " + service.getCustomer(1));
        System.out.println("Customer 2: " + service.getCustomer(2));
        System.out.println("Customer 5: " + service.getCustomer(5));
    }
}
