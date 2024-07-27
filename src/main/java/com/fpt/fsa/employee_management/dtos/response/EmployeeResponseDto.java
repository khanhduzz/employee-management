package com.fpt.fsa.employee_management.dtos.response;

import com.fpt.fsa.employee_management.enums.EGender;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeResponseDto {

    Long id;

    String firstName;

    String lastName;

    LocalDate dateOfBirth;

    String address;

    String phone;

    String departmentName;

    @Enumerated(EnumType.STRING)
    EGender gender;

    String remark;

    AccountResponseDto account;
}
