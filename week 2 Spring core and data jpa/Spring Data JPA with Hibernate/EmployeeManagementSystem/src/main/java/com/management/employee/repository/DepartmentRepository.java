package com.management.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.management.employee.model.Department;
import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Optional<Department> findByName(String name);
}
