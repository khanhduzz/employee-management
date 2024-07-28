package com.fpt.fsa.employee_management.services;

import com.fpt.fsa.employee_management.dtos.request.EmployeeCreateDto;
import com.fpt.fsa.employee_management.dtos.request.EmployeeUpdateDto;
import com.fpt.fsa.employee_management.dtos.response.EmployeeResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeService {

    EmployeeResponseDto createEmployee (EmployeeCreateDto employeeCreateDto);

    EmployeeResponseDto updateEmployee (Long id, EmployeeUpdateDto employeeUpdateDto);

    void deleteEmployee (Long id);

    EmployeeResponseDto getEmployee (Long id);

    List<EmployeeResponseDto> getAllEmployees ();

    Page<EmployeeResponseDto> getEmployeePageable (String firstName, String lastName, String email, String phoneNumber, String accountName ,Pageable pageable);
}
