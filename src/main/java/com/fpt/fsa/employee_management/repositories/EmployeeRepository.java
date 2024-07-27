package com.fpt.fsa.employee_management.repositories;

import com.fpt.fsa.employee_management.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
