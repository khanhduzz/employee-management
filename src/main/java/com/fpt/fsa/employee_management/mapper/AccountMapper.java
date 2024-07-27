package com.fpt.fsa.employee_management.mapper;

import com.fpt.fsa.employee_management.dtos.request.EmployeeCreateDto;
import com.fpt.fsa.employee_management.dtos.request.EmployeeUpdateDto;
import com.fpt.fsa.employee_management.dtos.response.AccountResponseDto;
import com.fpt.fsa.employee_management.entities.Account;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    Account toAccount (EmployeeCreateDto employeeCreateDto);

    AccountResponseDto toAccountResponseDto (Account account);

    Account updateAccount (@MappingTarget Account account, EmployeeUpdateDto employeeUpdateDto);

}
