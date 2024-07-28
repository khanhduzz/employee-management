package com.fpt.fsa.employee_management.services;

import com.fpt.fsa.employee_management.dtos.response.AuthResponseDto;

public interface AuthService {
    AuthResponseDto login (String accountName, String password);
}
