package com.fpt.fsa.employee_management.services.impl;

import com.fpt.fsa.employee_management.entities.Account;
import com.fpt.fsa.employee_management.entities.Employee;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

public final class EmployeeSpecification {

    private EmployeeSpecification() {
    }

    public static Specification<Employee> hasFirstName (String firstName) {
        String lowerCaseName = firstName == null ? "": firstName.trim().toLowerCase();
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.like(criteriaBuilder.lower(root.get("firstName")), "%" + lowerCaseName + "%");
    }

    public static Specification<Employee> hasLastName (String lastName) {
        String lowerCaseName = lastName == null ? "": lastName.trim().toLowerCase();
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.like(criteriaBuilder.lower(root.get("lastName")), "%" + lowerCaseName + "%");
    }

    public static Specification<Employee> hasEmail (String email) {
        String lowerCaseName = email == null ? "": email.trim().toLowerCase();
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.like(criteriaBuilder.lower(root.get("email")), "%" + lowerCaseName + "%");
    }

    public static Specification<Employee> hasPhoneNumber (String phoneNumber) {
        String lowerCaseName = phoneNumber == null ? "": phoneNumber.trim().toLowerCase();
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.like(criteriaBuilder.lower(root.get("phone")), "%" + lowerCaseName + "%");
    }

    public static Specification<Employee> hasAccount (String accountName) {
        String lowerCaseName = accountName == null ? "": accountName.trim().toLowerCase();
        return (root, query, criteriaBuilder) -> {
            Join<Employee, Account> accountJoin = root.join("account");
            return criteriaBuilder.like(criteriaBuilder.lower(accountJoin.get("accountName")), "%" + lowerCaseName + "%");
        };
    }
}
