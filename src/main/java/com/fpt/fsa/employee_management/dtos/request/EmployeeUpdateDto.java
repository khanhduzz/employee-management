package com.fpt.fsa.employee_management.dtos.request;

import com.fpt.fsa.employee_management.enums.EGender;
import com.fpt.fsa.employee_management.enums.EStatus;
import com.fpt.fsa.employee_management.validators.echeck.EnumSubset;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeUpdateDto {

    @NotBlank(message = "Cannot blank")
    @NotEmpty(message = "Cannot empty")
    @NotNull(message = "Cannot null")
    @Size(max = 255, message = "Max length is 255")
    String firstName;

    @NotBlank(message = "Cannot blank")
    @NotEmpty(message = "Cannot empty")
    @NotNull(message = "Cannot null")
    @Size(max = 255, message = "Max length is 255")
    String lastName;

    @NotNull(message = "Cannot null")
    @Enumerated(EnumType.STRING)
    @EnumSubset(enumClass = EGender.class, anyOf = {"MALE", "FEMALE", "OTHER"})
    EGender gender;

    @NotNull(message = "Cannot null")
    LocalDate dateOfBirth;

    @NotBlank(message = "Cannot blank")
    @NotEmpty(message = "Cannot empty")
    @NotNull(message = "Cannot null")
    @Size(min = 10, max = 11, message = "Length is 10-11")
    String phone;

    @NotBlank(message = "Cannot blank")
    @NotEmpty(message = "Cannot empty")
    @NotNull(message = "Cannot null")
    @Size(max = 255, message = "Maximum length is 255")
    String address;

    @NotBlank(message = "Cannot blank")
    @NotEmpty(message = "Cannot empty")
    @NotNull(message = "Cannot null")
    @Size(max = 255, message = "Maximum length is 255")
    String departmentName;

    @Size(max = 1000, message = "Maximum length is 1000")
    String remark;

    @NotBlank(message = "Cannot blank")
    @NotEmpty(message = "Cannot empty")
    @NotNull(message = "Cannot null")
    @Size(max = 255, message = "Maximum length is 255")
    String accountName;

    @Email(message = "Wrong email format")
    @NotBlank(message = "Cannot blank")
    @NotEmpty(message = "Cannot empty")
    @NotNull(message = "Cannot null")
    @Size(max = 255, message = "Maximum length is 255")
    String email;

    @NotBlank(message = "Cannot blank")
    @NotEmpty(message = "Cannot empty")
    @NotNull(message = "Cannot null")
    @Size(max = 255, message = "Maximum length is 255")
    String password;

    @NotNull(message = "Cannot null")
    @Enumerated(EnumType.STRING)
    @EnumSubset(enumClass = EStatus.class, anyOf = {"ACTIVE", "NOT_ACTIVE"})
    EStatus status;

}
