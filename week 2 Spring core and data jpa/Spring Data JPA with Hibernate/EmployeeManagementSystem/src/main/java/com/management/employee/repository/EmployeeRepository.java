package com.management.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.management.employee.model.Employee;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByDepartmentName(String deptName);
}
