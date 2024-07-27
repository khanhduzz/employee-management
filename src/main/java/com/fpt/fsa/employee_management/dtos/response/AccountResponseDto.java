package com.fpt.fsa.employee_management.dtos.response;

import com.fpt.fsa.employee_management.enums.EStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountResponseDto {

    Long id;

    String accountName;

    String email;

    String password;

    @Enumerated(EnumType.STRING)
    EStatus status;
}
