package com.fpt.fsa.employee_management.services.impl;

import com.fpt.fsa.employee_management.dtos.response.AuthResponseDto;

import com.fpt.fsa.employee_management.entities.Account;
import com.fpt.fsa.employee_management.exceptions.AccountNotFoundException;
import com.fpt.fsa.employee_management.repositories.AccountRepository;
import com.fpt.fsa.employee_management.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AccountRepository accountRepository;

    @Override
    public AuthResponseDto login (String accountName, String password) {
        Account account = accountRepository.findByAccountNameAndPassword(accountName, password);
        if (account == null) {
            throw new AccountNotFoundException();
        }
        return AuthResponseDto.builder()
                .accountName(account.getAccountName())
                .password(account.getPassword())
                .build();
    }
}
