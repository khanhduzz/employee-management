package com.fpt.fsa.employee_management.services.impl;

import com.fpt.fsa.employee_management.entities.Account;
import com.fpt.fsa.employee_management.entities.Employee;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

public final class EmployeeSpecification {

    private EmployeeSpecification() {
    }

    public static Specification<Employee> hasFirstName (String firstName) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("firstName"), firstName);
    }

    public static Specification<Employee> hasLastName (String lastName) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("lastName"), lastName);
    }

    public static Specification<Employee> hasEmail (String email) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("email"), email);
    }

    public static Specification<Employee> hasPhoneNumber (String phoneNumber) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("phone"), phoneNumber);
    }

    public static Specification<Employee> hasAccount (String accountName) {
        return (root, query, criteriaBuilder) -> {
            Join<Employee, Account> categoriesJoin = root.join("account");
            return criteriaBuilder.equal(categoriesJoin.get("accountName"), accountName);
        };
    }
}
