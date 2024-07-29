package com.fpt.fsa.employee_management.services.impl;

import com.fpt.fsa.employee_management.dtos.request.EmployeeCreateDto;
import com.fpt.fsa.employee_management.dtos.request.EmployeeUpdateDto;
import com.fpt.fsa.employee_management.dtos.response.EmployeeResponseDto;
import com.fpt.fsa.employee_management.entities.Account;
import com.fpt.fsa.employee_management.entities.Employee;
import com.fpt.fsa.employee_management.exceptions.AccountAlreadyExistException;
import com.fpt.fsa.employee_management.exceptions.DeleteNotAllowException;
import com.fpt.fsa.employee_management.exceptions.EmployeeNotFoundException;
import com.fpt.fsa.employee_management.mapper.AccountMapper;
import com.fpt.fsa.employee_management.mapper.EmployeeMapper;
import com.fpt.fsa.employee_management.repositories.AccountRepository;
import com.fpt.fsa.employee_management.repositories.EmployeeRepository;
import com.fpt.fsa.employee_management.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final AccountRepository accountRepository;
    private final EmployeeMapper employeeMapper;
    private final AccountMapper accountMapper;

    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public EmployeeResponseDto createEmployee(EmployeeCreateDto employeeCreateDto) {
        Account account = accountRepository.findByAccountName(employeeCreateDto.getAccountName());
        if (account != null) {
            throw new AccountAlreadyExistException();
        }

        Employee employee = employeeMapper.toEmployee(employeeCreateDto);
        account = accountMapper.toAccount(employeeCreateDto);
        account.setPassword(passwordEncoder.encode(employeeCreateDto.getPassword()));

        employee.addAccount(account);
        employee = employeeRepository.save(employee);

        return employeeMapper.toEmployeeResponseDto(employee);
    }

    @Override
    @Transactional
    public EmployeeResponseDto updateEmployee(Long id, EmployeeUpdateDto employeeUpdateDto) {
        Employee employee = employeeRepository.findById(id).orElseThrow(EmployeeNotFoundException::new);

        employee.setAccount(accountMapper.updateAccount(employee.getAccount(), employeeUpdateDto));
        employee = employeeMapper.updateEmployee(employee, employeeUpdateDto);

        employee = employeeRepository.save(employee);

        return employeeMapper.toEmployeeResponseDto(employee);
    }

    @Override
    @Transactional
    public void deleteEmployee(Long id) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        employeeRepository.findById(id)
                .ifPresentOrElse(employee -> {
                        if (employee.getAccount().getAccountName().equalsIgnoreCase(authentication.getName())) {
                            throw new DeleteNotAllowException();
                        } else {
                            employeeRepository.delete(employee);
                        }
                    }
                , DeleteNotAllowException::new);
    }

    @Override
    public EmployeeResponseDto getEmployee(Long id) {
        return employeeRepository.findById(id)
                .map(employeeMapper::toEmployeeResponseDto)
                .orElseThrow(EmployeeNotFoundException::new);
    }

    @Override
    public List<EmployeeResponseDto> getAllEmployees() {
        return employeeRepository.findAll()
                .stream().map(employeeMapper::toEmployeeResponseDto)
                .toList();
    }

    @Override
    public Page<EmployeeResponseDto> getEmployeePageable(String firstName, String lastName, String email, String phoneNumber, String accountName, Pageable pageable) {
        return employeeRepository.findAll(
                Specification.where(EmployeeSpecification.hasFirstName(firstName))
                        .and(EmployeeSpecification.hasLastName(lastName))
                        .and(EmployeeSpecification.hasEmail(email))
                        .and(EmployeeSpecification.hasPhoneNumber(phoneNumber))
                        .and(EmployeeSpecification.hasAccount(accountName)),
                pageable)
                .map(employeeMapper::toEmployeeResponseDto);
    }

}
