package com.fpt.fsa.employee_management.dtos.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Builder
@Getter
@Setter
public class AuthResponseDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String accountName;
    private String password;
}
