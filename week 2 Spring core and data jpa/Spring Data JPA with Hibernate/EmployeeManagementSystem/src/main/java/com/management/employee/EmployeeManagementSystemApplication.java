package com.management.employee;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import com.management.employee.model.Department;
import com.management.employee.model.Employee;
import com.management.employee.service.EmployeeService;
import java.util.List;

@SpringBootApplication
@EnableJpaAuditing
public class EmployeeManagementSystemApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeManagementSystemApplication.class);

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(EmployeeManagementSystemApplication.class, args);
        EmployeeService service = context.getBean(EmployeeService.class);

        LOGGER.info("----- Starting System Verification Verification -----");

        // 1. Setup Sample Departments
        Department engineering = service.saveDepartment(new Department("Engineering"));
        Department humanResources = service.saveDepartment(new Department("Human Resources"));

        // 2. Setup and Add Audited Employees
        Employee emp1 = new Employee("Alice Developer", "alice@company.com", engineering);
        Employee emp2 = new Employee("Bob Architect", "bob@company.com", engineering);
        Employee emp3 = new Employee("Charlie Recruiter", "charlie@company.com", humanResources);

        service.saveEmployee(emp1);
        service.saveEmployee(emp2);
        service.saveEmployee(emp3);

        // 3. Verify Auditing Records & Output Results
        List<Employee> allEmployees = service.getAllEmployees();
        for (Employee emp : allEmployees) {
            LOGGER.info("Employee Loaded: Name={}, Department={}, CreatedDate={}", 
                    emp.getName(), emp.getDepartment().getName(), emp.getCreatedDate());
        }

        // 4. Test Relationship Query Filtering
        List<Employee> engTeam = service.getEmployeesByDepartment("Engineering");
        LOGGER.info("Total Engineering Personnel Count: {}", engTeam.size());
        
        LOGGER.info("----- System Verification Verification Completed Successfully -----");
    }
}
