package com.fpt.fsa.employee_management.config.security;

import com.fpt.fsa.employee_management.entities.Account;
import com.fpt.fsa.employee_management.exceptions.AccountNotFoundException;
import com.fpt.fsa.employee_management.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername (String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByAccountName(username);
        if (account == null) {
            throw new AccountNotFoundException();
        }
        return User.withUsername(account.getAccountName())
                .password(account.getPassword())
                .build();
    }
}
