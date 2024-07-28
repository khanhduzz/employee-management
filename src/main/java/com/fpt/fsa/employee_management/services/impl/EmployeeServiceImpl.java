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
        employee.getAccount().setPassword(passwordEncoder.encode(employeeUpdateDto.getPassword()));

        employee = employeeRepository.save(employee);

        return employeeMapper.toEmployeeResponseDto(employee);
    }

    @Override
    public void deleteEmployee(Long id) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        employeeRepository.findById(id)
                .ifPresentOrElse(employee -> {
                    if (employee.getAccount().getAccountName().equals(authentication.getName())) {
                        throw new DeleteNotAllowException();
                    }
                }, () -> employeeRepository.deleteById(id));
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

}
