package com.fpt.fsa.employee_management.mapper;

import com.fpt.fsa.employee_management.dtos.request.EmployeeCreateDto;
import com.fpt.fsa.employee_management.dtos.request.EmployeeUpdateDto;
import com.fpt.fsa.employee_management.dtos.response.EmployeeResponseDto;
import com.fpt.fsa.employee_management.entities.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = AccountMapper.class)
public interface EmployeeMapper {

    Employee toEmployee (EmployeeCreateDto addEmployeeDto);

    EmployeeResponseDto toEmployeeResponseDto (Employee employee);

    Employee updateEmployee (@MappingTarget Employee employee, EmployeeUpdateDto employeeUpdateDto);
}
