package com.fpt.fsa.employee_management.services;

import com.fpt.fsa.employee_management.dtos.request.EmployeeCreateDto;
import com.fpt.fsa.employee_management.dtos.request.EmployeeUpdateDto;
import com.fpt.fsa.employee_management.dtos.response.EmployeeResponseDto;

import java.util.List;

public interface EmployeeService {

    EmployeeResponseDto createEmployee (EmployeeCreateDto employeeCreateDto);

    EmployeeResponseDto updateEmployee (Long id, EmployeeUpdateDto employeeUpdateDto);

    void deleteEmployee (Long id);

    EmployeeResponseDto getEmployee (Long id);

    List<EmployeeResponseDto> getAllEmployees ();
}
