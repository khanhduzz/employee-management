package com.fpt.fsa.employee_management;

import com.fpt.fsa.employee_management.entities.Account;
import com.fpt.fsa.employee_management.entities.Employee;
import com.fpt.fsa.employee_management.enums.EGender;
import com.fpt.fsa.employee_management.enums.EStatus;
import com.fpt.fsa.employee_management.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
@RequiredArgsConstructor
public class EmployeeManagementApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(EmployeeManagementApplication.class, args);
    }

    private final EmployeeRepository employeeRepository;

    @Value("${application.admin.default.username}")
    private String accountName;

    @Value("${application.admin.default.password}")
    private String password;

    @Override
    public void run(String... args) throws Exception {
        Account account = Account.builder()
                .email("james@gmail.com")
                .accountName(accountName)
                .status(EStatus.ACTIVE)
                .password(password)
                .build();
        Employee employee = Employee.builder()
                .firstName("James")
                .lastName("Bond")
                .phone("0123456789")
                .gender(EGender.MALE)
                .address("137 Boston")
                .dateOfBirth(LocalDate.of(1990,1,1))
                .departmentName("CIA")
                .remark("Sky fall")
                .build();
        employee.addAccount(account);
        employeeRepository.save(employee);
    }
}
